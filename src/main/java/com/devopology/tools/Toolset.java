package com.devopology.tools;

import com.devopology.tools.impl.ExecutionResultImpl;
import org.apache.commons.exec.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Toolset {

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final static JSONParser jsonParser = new JSONParser();

    protected File currentDirectory = null;

    public final static int NOT_FOUND = -1;
    public final static int DIRECTORY = 0;
    public final static int FILE = 1;

    public final static String ACCEPT_INVALID_SSL_CERTIFICATE = "ACCEPT_INVALID_SSL_CERTIFICATE";

    protected Map<String, String> configurationHashMap = null;

    public Toolset() {
        System.setErr(System.out);
        this.configurationHashMap = new HashMap<String, String>();
        this.currentDirectory = new File(".");
    }

    public void setConfiguration(String key, String value) {
        configurationHashMap.put(key, value);
    }

    protected String getConfiguration(String key, String defaultValue) {
        String value = configurationHashMap.get(key);
        if (null == value) {
            value = defaultValue;
        }
        return value;
    }

    protected String getCallerClassName() {
        StackTraceElement [] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Toolset.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") !=0 ) {
                return ste.getClassName();
            }
        }
        return getClass().getName();
    }

    protected void output(String message) {
        System.out.println(simpleDateFormat.format(new Date()) + " : " + message);
    }

    private static String listToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();

        if (null != list) {
            for (String string : list) {
                stringBuilder.append(" ");
                stringBuilder.append(string);
            }
        }

        if (stringBuilder.length() == 0) {
            stringBuilder.append("");
        }

        return stringBuilder.toString();
    }

    public List<String> stringToList(String string) throws IOException {
        List<String> result = new ArrayList<String>();

        if (null != string) {
            String line = null;
            BufferedReader bufferedReader = new BufferedReader(new StringReader(string));

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }

        return result;
    }

    public String stripExtension(String filename) {
        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }

        return filename;
    }

    public void println(Object object) {
        String message = null;

        if (null != object) {
            message = object.toString();
        }

        if ((null != message) && ((message.indexOf("\r") != -1) || (message.indexOf("\n") != -1))) {
            String line = null;
            BufferedReader reader = new BufferedReader(new StringReader(message));

            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(simpleDateFormat.format(new Date()) + " : " + "println( " + line + " )");
                }
            }
            catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        else {
            System.out.println(simpleDateFormat.format(new Date()) + " : " + "println( " + message + " )");
        }
    }

    public void cd(String path) throws Exception {
        output("cd( " + path + " )");

        File file = new File(path);

        if (false == file.isAbsolute()) {
            file = new File(this.currentDirectory.getAbsolutePath() + File.separator + path);
        }

        if (false == file.exists()) {
            throw new IOException(path + " doesn't exist");
        }

        if (false == file.isDirectory()) {
            throw new IOException(path + " isn't a directory");
        }

        this.currentDirectory = file;
    }

    public String pwd() throws Exception {
        String path = this.currentDirectory.getAbsolutePath();
        if (path.endsWith("/.") || path.endsWith("\\.")) {
            path = path.substring(0, path.length() - 2);
        }

        return path;
    }

    public String absolute(String path) throws Exception {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + path);
        }

        return file.getAbsolutePath();
    }

    public Properties loadProperties(String path) throws Exception {
        return loadProperties(new File(path));
    }

    public Properties loadProperties(File file) throws Exception {
        output("loadProperties( " + file.getCanonicalPath() + " )");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties;
    }

    public boolean exists(String path) throws Exception {
        return exists(new File(path));
    }

    public boolean exists(File file) throws Exception {
        return file.getCanonicalFile().exists();
    }

    public int type(String path) throws Exception {
        return type(new File(path));
    }

    public int type(File file) throws Exception {
        if (false == file.exists()) {
            output("type( " + file.getAbsolutePath() + " ) = NOT_FOUND");
            return NOT_FOUND;
        }

        if (file.isDirectory()) {
            output("type( " + file.getAbsolutePath() + " ) = DIRECTORY");
            return DIRECTORY;
        }
        else {
            output("type( " + file.getAbsolutePath() + " ) = FILE");
            return FILE;
        }
    }

    public String typeString(String path) throws Exception {
        return typeString(new File(path));
    }

    public String typeString(File file) throws Exception {
        String result = null;
        if (false == file.exists()) {
            result = "NOT FOUND";
        }
        else if (file.isDirectory()) {
            result = "DIRECTORY";
        }
        else if (file.isFile()){
           result = "FILE";
        }
        else {
            throw new RuntimeException("Developer error!!!");
        }

        output("typeString( " + file.getAbsolutePath() + " ) = " + result);
        return result;
    }

    public void replaceProperties(Properties properties, String inputFilePath, String outputFilePath) throws Exception {
        replaceProperties(properties, new File(inputFilePath), new File(outputFilePath));
    }

    public void replaceProperties(Properties properties, File inputFile, File outputFile) throws Exception {
        output("replaceProperties( [properties], " + inputFile.getCanonicalPath() + ", " + outputFile.getCanonicalPath() + " )");
        String content = readFile(inputFile);

        Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            content = content.replaceAll(Pattern.quote("${" + key + "}"), value);
        }

        writeFile(outputFile, content);
    }

    public String readFile(String path) throws Exception {
        return readFile(new File(path));
    }

    public String readFile(File file) throws Exception {
        output("readFile( " + file.getCanonicalPath() + " )");
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + file.getName());
        }
        byte [] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public void writeFile(String path, String content) throws Exception {
        writeFile(new File(path), content);
    }

    public void writeFile(File file, String content) throws Exception {
        output("writeFile( " + file.getCanonicalPath() + ", [getContent])");
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + file.getName());
        }
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(content);
        printWriter.close();
    }

    public JSONObject loadJSONObject(String path) throws Exception {
        return loadJSONObject(new File(path));
    }

    public JSONObject loadJSONObject(File file) throws Exception {
        output("loadJSONObject( " + file.getCanonicalPath() + " )");
        return (JSONObject) jsonParser.parse(
                new FileReader(file),
                new ContainerFactory() {
                    @Override
                    public Map createObjectContainer() {
                        return null;
                    }

                    @Override
                    public List creatArrayContainer() {
                        return null;
                    }
                });
    }

    public JSONArray loadJSONArray(String path) throws Exception {
        return loadJSONArray(new File(path));
    }

    public JSONArray loadJSONArray(File file) throws Exception {
        output("loadJSONArray( " + file.getCanonicalPath() + " )");
        return (JSONArray) jsonParser.parse(
                new FileReader(file),
                new ContainerFactory() {
                    @Override
                    public Map createObjectContainer() {
                        return null;
                    }

                    @Override
                    public List creatArrayContainer() {
                        return null;
                    }
                });
    }

    public ExecutionResult execute(String executable, String ... arguments) throws Exception {
        return execute(new File(executable), arguments);
    }

    public ExecutionResult execute(File executable, String ... arguments) throws Exception {
        List<String> argumentList = null;
        if (null != arguments) {
            argumentList = new ArrayList<String>();
            for (String argument : arguments) {
                argumentList.add(argument);
            }
        }

        return execute(executable, argumentList);
    }

    public ExecutionResult execute(String executable, List<String> argumentList) throws Exception {
        return execute(new File(executable), argumentList);
    }

    public ExecutionResult execute(File executable, List<String> argumentList) throws Exception {
        output("execute( " + executable.getCanonicalPath() + listToString(argumentList) + " )");

        CommandLine commandLine = new CommandLine(executable.getAbsolutePath());
        if (null != argumentList) {
            for (String argument : argumentList) {
                commandLine.addArgument(argument);
            }
        }

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

        ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
        Executor executor = new DefaultExecutor();
        executor.setWatchdog(watchdog);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        executor.execute(commandLine, resultHandler);
        resultHandler.waitFor();

        ExecutionResultImpl result = new ExecutionResultImpl();
        result.setExitCode(resultHandler.getExitValue());
        result.setContent(outputStream.toString());

        return result;
    }
}
