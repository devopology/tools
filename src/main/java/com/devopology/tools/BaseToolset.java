package com.devopology.tools;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BaseToolset {

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final static JSONParser jsonParser = new JSONParser();

    protected String className = null;
    protected File currentDirectory = null;

    public static int EXIT_CODE = 0;
    public final static int NOT_FOUND = -1;
    public final static int DIRECTORY = 0;
    public final static int FILE = 1;

    protected Map<String, String> configurationHashMap = null;

    public BaseToolset() {
        this.configurationHashMap = new HashMap<String, String>();
        System.setErr(System.out);
        this.className = getCallerClassName();

        /*
        if (SystemUtils.IS_OS_WINDOWS) {
            // Assume there is a C:
            this.currentDirectory = new File("C:\\");
        }
        else {
            // Assume a Unix based system
            this.currentDirectory = new File("/");
        }
        */
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
            if (!ste.getClassName().equals(BaseToolset.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") !=0 ) {
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

    public void println(String message) {
        if ((null != message) && ((message.indexOf("\r") != -1) || (message.indexOf("\n") != -1))) {
            String line = null;
            BufferedReader reader = new BufferedReader(new StringReader(message));

            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(simpleDateFormat.format(new Date()) + " : " + this.className + ".println( " + line + " )");
                }
            }
            catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        else {
            System.out.println(simpleDateFormat.format(new Date()) + " : " + this.className + ".println( " + message + " )");
        }
    }

    public void cd(String path) throws Exception {
        output(this.className + ".cd( " + path + " )");

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

        output(this.className + ".pwd() = " + path);
        return path;
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
            output(this.className + ".type( " + file.getAbsolutePath() + " ) = NOT_FOUND");
            return NOT_FOUND;
        }

        if (file.isDirectory()) {
            output(this.className + ".type( " + file.getAbsolutePath() + " ) = DIRECTORY");
            return DIRECTORY;
        }
        else {
            output(this.className + ".type( " + file.getAbsolutePath() + " ) = FILE");
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

        output(this.className + ".typeString( " + file.getAbsolutePath() + " ) = " + result);
        return result;
    }

    public void mv(String oldPath, String newPath) throws Exception {
        mv(new File(oldPath), new File(newPath));
    }

    public void mv(File oldFile, File newFile) throws Exception {
        output(this.className + ".mv( " + oldFile.getCanonicalPath() + ", " + newFile.getCanonicalPath() + " )");
        if (!oldFile.exists()) {
            throw new Exception(oldFile.getCanonicalPath() + " doesn't exist");
        }

        if (!oldFile.isDirectory()) {
            throw new Exception(oldFile.getCanonicalPath() + " is not a directory");
        }

        if (newFile.exists()) {
            throw new Exception(newFile.getCanonicalPath() + " already exists");
        }

        oldFile.renameTo(newFile);
    }

    public void mkdir(String path) throws Exception {
        mkdir(new File(path));
    }

    public void mkdir(File file) throws Exception {
        output(this.className + ".mkdir( " + file.getCanonicalPath() + " )");
        file.mkdir();
    }

    public void mkdirs(String path) throws Exception {
        mkdirs(new File(path));
    }

    public void mkdirs(File file) throws Exception {
        output(this.className + ".mkdirs( " + file.getCanonicalPath() + " )");
        file.mkdirs();
    }

    public void rm(String path, boolean force) throws Exception {
        rm(new File(path), force);
    }

    public void rm(File file, boolean force) throws Exception {
        output(this.className + ".rm( " + file.getCanonicalPath() + ", " + force + " )");

        if (file.exists()) {
            if (file.isFile()) {
                if (false == file.delete()) {
                    throw new IOException("Unable to delete " + file.getAbsolutePath());
                }
            }
            else {
                if (force) {
                    rmRecursive(file.listFiles());
                }

                if (false == file.delete()) {
                    if ((false == force) && (file.isDirectory() && (null != file.listFiles()))) {
                        throw new IOException("Unable to delete " + file.getAbsolutePath() + " because it is not empty");
                    }
                    else {
                        throw new IOException("Unable to delete " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private void rmRecursive(File [] files) throws Exception {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    rmRecursive(file.listFiles());
                    if (false == file.delete()) {
                        throw new IOException("Unable to delete " + file.getAbsolutePath());
                    }
                }
                else {
                    if (false == file.delete()) {
                        throw new IOException("Unable to delete " + file.getAbsolutePath());
                    }
                }
            }
        }
    }

    public void rmdir(String path) throws Exception {
        rmdir(new File(path));
    }

    public void rmdir(File file) throws Exception {
        output(this.className + ".rmdir( " + file.getCanonicalPath() + " )");

        if (file.exists()) {
            if (file.isDirectory()) {
                if (false == file.delete()) {
                    throw new IOException("Unable to delete " + file.getAbsolutePath());
                }
            }
            else {
                throw new IOException(file.getAbsolutePath() + " is not a directory");
            }
        }
    }

    public void cp(String sourcePath, String destinationPath) throws Exception {
        cp(new File(sourcePath), new File(destinationPath));
    }

    public void cp(File sourceFile, File destinationFile) throws Exception {
        output(this.className + ".copyFile( " + sourceFile.getCanonicalPath() +", " + destinationFile.getCanonicalPath() + " )");
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try {
            inputChannel = new FileInputStream(sourceFile).getChannel();
            outputChannel = new FileOutputStream(destinationFile).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    public void unzipFile(String zipFilePath, String outputFolderPath) throws Exception {
        unzipFile(new File(zipFilePath), new File(outputFolderPath));
    }

    public void unzipFile(File zipFile, File outputFolder) throws Exception {
        output(this.className + ".unzipFile( " + zipFile.getCanonicalPath() + ", " + outputFolder.getCanonicalPath() + " )");
        byte [] buffer = new byte[1024];
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry ze = zis.getNextEntry();

        while (ze != null) {
            String fileName = ze.getName();

            File newFile = new File(outputFolder + File.separator + fileName);
            new File(newFile.getParent()).mkdirs();

            if (ze.isDirectory()) {
                newFile.mkdirs();
            }
            else {
                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }

            ze = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }

    public Properties loadProperties(String path) throws Exception {
        return loadProperties(new File(path));
    }

    public Properties loadProperties(File file) throws Exception {
        output(this.className + ".loadProperties( " + file.getCanonicalPath() + " )");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties;
    }

    public void replaceProperties(Properties properties, String inputFilePath, String outputFilePath) throws Exception {
        replaceProperties(properties, new File(inputFilePath), new File(outputFilePath));
    }

    public void replaceProperties(Properties properties, File inputFile, File outputFile) throws Exception {
        output(this.className + ".replaceProperties( [properties], " + inputFile.getCanonicalPath() + ", " + outputFile.getCanonicalPath() + " )");
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
        output(this.className + ".readFile( " + file.getCanonicalPath() + " )");
        byte [] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public void writeFile(String path, String content) throws Exception {
        writeFile(new File(path), content);
    }

    public void writeFile(File file, String content) throws Exception {
        output(this.className + ".writeFile( " + file.getCanonicalPath() + ", [content])");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(content);
        printWriter.close();
    }

    public JSONObject loadJSONObject(String path) throws Exception {
        return loadJSONObject(new File(path));
    }

    public JSONObject loadJSONObject(File file) throws Exception {
        output(this.className + ".loadJSONObject( " + file.getCanonicalPath() + " )");
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
        output(this.className + ".loadJSONArray( " + file.getCanonicalPath() + " )");
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

    public String execute(String executable, String [] argumentList) throws Exception {
        return execute(new File(executable), argumentList);
    }

    public String execute(File executable, String [] argumentList) throws Exception {
        List<String> argumentList2 = null;
        if (null != argumentList) {
            argumentList2 = Arrays.asList(argumentList);
        }

        return execute(executable, argumentList2);
    }

    public String execute(String executable, List<String> argumentList) throws Exception {
        return execute(new File(executable), argumentList);
    }

    public String execute(File executable, List<String> argumentList) throws Exception {
        BaseToolset.EXIT_CODE = 0;
        output(this.className + ".execute( " + executable.getCanonicalPath() + listToString(argumentList) + " )");

        CommandLine commandLine = new CommandLine(executable.getAbsolutePath());
        if (null != argumentList) {
            for (String argument : argumentList) {
                commandLine.addArgument(argument);
            }
        }

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

        ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
        Executor executor = new DefaultExecutor();
        executor.setWatchdog(watchdog);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(streamHandler);

        executor.execute(commandLine, resultHandler);
        resultHandler.waitFor();
        BaseToolset.EXIT_CODE = resultHandler.getExitValue();

        return outputStream.toString();
    }
}
