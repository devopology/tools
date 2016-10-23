package com.devopology.tools;

import org.apache.commons.exec.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.*;
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

    private String simpleClassName = null;

    public static int EXIT_CODE = 0;
    public final static int TYPE_NOT_FOUND = -1;
    public final static int TYPE_DIRECTORY = 0;
    public final static int TYPE_FILE = 0;

    public BaseToolset() {
        this.simpleClassName = getCallerClassName();

//        int index = this.simpleClassName.lastIndexOf(".");
//
//        if (-1 != index) {
//            this.simpleClassName = this.simpleClassName.substring(index + 1);
//        }
    }

    private String getCallerClassName() {
        StackTraceElement [] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(BaseToolset.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") !=0 ) {
                return ste.getClassName();
            }
        }
        return getClass().getSimpleName();
    }

    private void output(String message) {
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
                    System.out.println(simpleDateFormat.format(new Date()) + " : " + this.simpleClassName + ".println( " + line + " )");
                }
            }
            catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        else {
            System.out.println(simpleDateFormat.format(new Date()) + " : " + this.simpleClassName + ".println( " + message + " )");
        }
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
            output(this.simpleClassName + ".type( " + file.getAbsolutePath() + " ) = TYPE_NOT_FOUND");
            return TYPE_NOT_FOUND;
        }

        if (file.isDirectory()) {
            output(this.simpleClassName + ".type( " + file.getAbsolutePath() + " ) = TYPE_DIRECTORY");
            return TYPE_DIRECTORY;
        }
        else {
            output(this.simpleClassName + ".type( " + file.getAbsolutePath() + " ) = TYPE_FILE");
            return TYPE_FILE;
        }
    }

    public void renameDirectory(String oldPath, String newPath) throws Exception {
        renameDirectory(new File(oldPath), new File(newPath));
    }

    public void renameDirectory(File oldFile, File newFile) throws Exception {
        if (!oldFile.exists()) {
            throw new Exception(oldFile.getCanonicalPath() + " doesn't exist");
        }

        if (!oldFile.isDirectory()) {
            throw new Exception(oldFile.getCanonicalPath() + " is not a directory");
        }

        if (newFile.exists()) {
            throw new Exception(newFile.getCanonicalPath() + " already exists");
        }

        output(this.simpleClassName + ".renameDirectory( " + oldFile.getCanonicalPath() + ", " + newFile.getCanonicalPath() + " )");
        oldFile.renameTo(newFile);
    }

    public void createDirectory(String path) throws Exception {
        createDirectory(new File(path));
    }

    public void createDirectory(File file) throws Exception {
        output(this.simpleClassName + ".createDirectory( " + file.getCanonicalPath() + " )");
        file.mkdirs();
    }

    public void delete(String path) throws Exception {
        delete(new File(path));
    }

    public void delete(File file) throws Exception {
        output(this.simpleClassName + ".createDirectory( " + file.getCanonicalPath() + " )");
        if (file.exists()) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            }
            else {
                file.delete();
            }
        }
    }

    public void deleteFile(String path) throws Exception {
        deleteFile(new File(path));
    }

    public void deleteFile(File file) throws Exception {
        output(this.simpleClassName + ".createDirectory( " + file.getCanonicalPath() + " )");
        file.delete();
    }

    public void deleteChildren(String path) throws Exception {
        deleteFiles(new File(path).listFiles());
    }

    public void deleteChildren(File file) throws Exception {
        deleteFiles(file.listFiles());
    }

    public void deleteFiles(File [] files) throws Exception {
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                }
                else {
                    file.delete();
                }
            }
        }
    }

    public void deleteDirectory(String path) throws Exception {
        deleteDirectory(new File(path));
    }

    public void deleteDirectory(File file) throws Exception {
        output(this.simpleClassName + ".deleteDirectory( " + file.getCanonicalPath() + " )");
        if (file.isDirectory()) {
            File [] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    if (f.isFile()) {
                        deleteFile(f);
                    }
                    else {
                        deleteDirectory(f);
                    }
                }
            }

            file.delete();
        }
        else {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public void copyFile(String sourcePath, String destinationPath) throws Exception {
        copyFile(new File(sourcePath), new File(destinationPath));
    }

    public void copyFile(File sourceFile, File destinationFile) throws Exception {
        output(this.simpleClassName + ".copyFile( " + sourceFile.getCanonicalPath() +", " + destinationFile.getCanonicalPath() + " )");
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
        output(this.simpleClassName + ".unzipFile( " + zipFile.getCanonicalPath() + ", " + outputFolder.getCanonicalPath() + " )");
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
        output(this.simpleClassName + ".loadProperties( " + file.getCanonicalPath() + " )");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties;
    }

    public void replaceProperties(Properties properties, String inputFilePath, String outputFilePath) throws Exception {
        replaceProperties(properties, new File(inputFilePath), new File(outputFilePath));
    }

    public void replaceProperties(Properties properties, File inputFile, File outputFile) throws Exception {
        output(this.simpleClassName + ".replaceProperties( [properties], " + inputFile.getCanonicalPath() + ", " + outputFile.getCanonicalPath() + " )");
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
        output(this.simpleClassName + ".readFile( " + file.getCanonicalPath() + " )");
        byte [] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public void writeFile(String path, String content) throws Exception {
        writeFile(new File(path), content);
    }

    public void writeFile(File file, String content) throws Exception {
        output(this.simpleClassName + ".writeFile( " + file.getCanonicalPath() + ", [content])");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(content);
        printWriter.close();
    }

    public JSONObject loadJSONObject(String path) throws Exception {
        return loadJSONObject(new File(path));
    }

    public JSONObject loadJSONObject(File file) throws Exception {
        output(this.simpleClassName + ".loadJSONObject( " + file.getCanonicalPath() + " )");
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
        output(this.simpleClassName + ".loadJSONArray( " + file.getCanonicalPath() + " )");
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
        output(this.simpleClassName + ".execute( " + executable.getCanonicalPath() + listToString(argumentList) + " )");

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
