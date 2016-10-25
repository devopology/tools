/*
 * Copyright 2016 Doug Hoard
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.devopology.tools;

import com.devopology.tools.impl.ExecutionResultImpl;
import org.apache.commons.exec.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.Charset;
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

    public final static String SYSTEMD_SERVICE_ROOT = "/lib/systemd/system";

    public final static String SYSTEMCTL = "/usr/bin/systemctl";
    public final static String CP = "/usr/bin/cp";
    public final static String LS = "/usr/bin/ls";
    public final static String MKDIR = "/usr/bin/mkdir";
    public final static String RM = "/usr/bin/rm";
    public final static String UNZIP = "/usr/bin/unzip";

    private static int EXIT_CODE = 0;

    /**
     * File types
     */
    public final static int NOT_FOUND = -1;
    public final static int DIRECTORY = 0;
    public final static int FILE = 1;

    protected Properties properties = null;

    /**
     * Constructor
     */
    public Toolset() {
        System.setErr(System.out);
        this.properties = new Properties();
        this.currentDirectory = new File(".").getAbsoluteFile();
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

    private String getCallerClassName() {
        StackTraceElement [] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Toolset.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") !=0 ) {
                return ste.getClassName();
            }
        }

        return getClass().getName();
    }

    private File absoluteFile(String path) throws Exception {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + path);
        }

        return file;
    }

    public File absoluteFile(File file) throws Exception {
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + file.getName());
        }
        return file;
    }

    /**
     * Method to set a configuration key / value
     *
     * @param key
     * @param value
     */
    public void setConfiguration(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * Method to set configuration Properties
     *
     * @param properties
     */
    public void setConfiguration(Properties properties) {
        if (null == properties) {
            properties = new Properties();
        }

        this.properties = properties;
    }

    /**
     * Method to merge Properties into the current configuration Properties
     *
     * @param properties
     */
    public void mergeConfiguration(Properties properties) {
        if (null != properties) {
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.setProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    /**
     * Method to clear configuration Properties
     */
    public void clearConfiguration() {
        this.properties.clear();
    }

    /**
     * Method to get a configuration value based on key
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getConfiguration(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Method to convert a string with CRLF to a list
     *
     * @param string
     * @return
     * @throws IOException
     */
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

    /**
     * Method to get a filename without extension
     *
     * @param filename
     * @return
     */
    public String noExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }

    /**
     * Method to print a line
     *
     * @param object
     */
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

    /**
     * Method to change the current working directory
     *
     * @param path
     * @throws Exception
     */
    public void cd(String path) throws Exception {
        File file = absoluteFile(path);
        output("cd( " + file.getCanonicalPath() + " )");

        if (false == file.exists()) {
            throw new IOException(path + " doesn't exist");
        }

        if (false == file.isDirectory()) {
            throw new IOException(path + " isn't a directory");
        }

        this.currentDirectory = file;
    }

    /**
     * Method to get the current working directory
     *
     * @return
     * @throws Exception
     */
    public String pwd() throws Exception {
        String path = this.currentDirectory.getCanonicalPath();
        if (path.endsWith("/.") || path.endsWith("\\.")) {
            path = path.substring(0, path.length() - 2);
        }

        return path;
    }

    /**
     * Method to get an absolute filename
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String absolute(String path) throws Exception {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + path);
        }

        return file.getCanonicalPath();
    }

    /**
     * Method to load properties from a file
     *
     * @param path
     * @return
     * @throws Exception
     */
    public Properties loadProperties(String path) throws Exception {
        return loadProperties(absoluteFile(path));
    }

    /**
     * Method to load Properties from a file
     *
     * @param file
     * @return
     * @throws Exception
     */
    public Properties loadProperties(File file) throws Exception {
        file = absoluteFile(file);
        output("loadProperties( " + file.getCanonicalPath() + " )");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties;
    }

    /**
     * Method to determine if a file exists
     *
     * @param path
     * @return
     * @throws Exception
     */
    public boolean exists(String path) throws Exception {
        return exists(absoluteFile(path));
    }

    /**
     * Method to determine if a file exists
     *
     * @param file
     * @return
     * @throws Exception
     */
    public boolean exists(File file) throws Exception {
        return absoluteFile(file).exists();
    }

    /**
     * Method to get the "type" of a file
     *
     * @param path
     * @return
     * @throws Exception
     */
    public int type(String path) throws Exception {
        return type(absoluteFile(path));
    }

    /**
     * Method to get the "type" of a file
     *
     * @param file
     * @return
     * @throws Exception
     */
    public int type(File file) throws Exception {
        file = absoluteFile(file);
        if (false == file.exists()) {
            output("type( " + file.getCanonicalPath() + " ) = NOT_FOUND");
            return NOT_FOUND;
        }

        if (file.isDirectory()) {
            output("type( " + file.getCanonicalPath() + " ) = DIRECTORY");
            return DIRECTORY;
        }
        else {
            output("type( " + file.getCanonicalPath() + " ) = FILE");
            return FILE;
        }
    }

    /**
     * Method to get the "type" of a file as a String
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String typeString(String path) throws Exception {
        return typeString(absoluteFile(path));
    }

    /**
     * Method to get the "type" of a file as a String
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String typeString(File file) throws Exception {
        file = absoluteFile(file);
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

        output("typeString( " + file.getCanonicalPath() + " ) = " + result);
        return result;
    }

    /**
     * Method to replace Properties in a String
     *
     * @param content
     * @return
     */
    public String replaceProperties(String content) {
        println("replaceProperties( [properties] )");
        Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            content = content.replaceAll(Pattern.quote("${" + key + "}"), value);
        }

        return content;
    }

    /**
     * Method to replace Properties in a file
     *
     * @param properties
     * @param inputFilePath
     * @param outputFilePath
     * @throws Exception
     */
    public void replaceProperties(Properties properties, String inputFilePath, String outputFilePath) throws Exception {
        replaceProperties(properties, absoluteFile(inputFilePath), absoluteFile(outputFilePath));
    }

    /**
     * Method to replace Properties in a file
     *
     * @param properties
     * @param inputFile
     * @param outputFile
     * @throws Exception
     */
    public void replaceProperties(Properties properties, File inputFile, File outputFile) throws Exception {
        inputFile = absoluteFile(inputFile);
        outputFile = absoluteFile(outputFile);
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

    /**
     * Method to read a file's contents into String
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String readFile(String path) throws Exception {
        return FileUtils.readFileToString(absoluteFile(path), StandardCharsets.UTF_8);
    }

    /**
     * Method to read a file's contents into String
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String readFile(File file) throws Exception {
        return FileUtils.readFileToString(absoluteFile(file), StandardCharsets.UTF_8);
    }

    /**
     * Method to write a String to a file
     *
     * @param path
     * @param content
     * @throws Exception
     */
    public void writeFile(String path, String content) throws Exception {
        writeFile(absoluteFile(path), content);
    }

    /**
     * Method to write a String to a file
     *
     * @param file
     * @param content
     * @throws Exception
     */
    public void writeFile(File file, String content) throws Exception {
        file = absoluteFile(file);
        output("writeFile( " + file.getCanonicalPath() + ", [getContent])");

        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(content);
        printWriter.close();
    }

    /**
     * Method to parse a String as a JSONObject
     *
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject parseJSONObject(String json) throws Exception {
        output("parseJSONObject( [json] )");
        return (JSONObject) jsonParser.parse(
                json,
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

    /**
     * Method to load a file's content as a JSONObject
     *
     * @param path
     * @return
     * @throws Exception
     */
    public JSONObject loadJSONObject(String path) throws Exception {
        return loadJSONObject(absoluteFile(path));
    }

    /**
     * Method to load a file's content as a JSONObject
     *
     * @param file
     * @return
     * @throws Exception
     */
    public JSONObject loadJSONObject(File file) throws Exception {
        file = absoluteFile(file);
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

    /**
     * Method to parse a String as a JSONArray
     *
     * @param json
     * @return
     * @throws Exception
     */
    public JSONArray parseJSONArray(String json) throws Exception {
        output("parseJSONArray( [json] )");
        return (JSONArray) jsonParser.parse(
                json,
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

    /**
     * Method to load a file's content as a JSONArray
     *
     * @param path
     * @return
     * @throws Exception
     */
    public JSONArray loadJSONArray(String path) throws Exception {
        return loadJSONArray(absoluteFile(path));
    }

    /**
     * Method to load a file's content as a JSONArray
     *
     * @param file
     * @return
     * @throws Exception
     */
    public JSONArray loadJSONArray(File file) throws Exception {
        file = absoluteFile(file);
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

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return
     * @throws Exception
     */
    public ExecutionResult execute(String executable, String ... arguments) throws Exception {
        return execute(absoluteFile(executable), arguments);
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return
     * @throws Exception
     */
    public ExecutionResult execute(File executable, String ... arguments) throws Exception {
        executable = absoluteFile(executable);
        List<String> argumentList = null;
        if (null != arguments) {
            argumentList = new ArrayList<String>();
            for (String argument : arguments) {
                argumentList.add(argument);
            }
        }

        return execute(executable, argumentList);
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param argumentList
     * @return
     * @throws Exception
     */
    public ExecutionResult execute(String executable, List<String> argumentList) throws Exception {
        return execute(absoluteFile(executable), argumentList);
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param argumentList
     * @return
     * @throws Exception
     */
    public ExecutionResult execute(File executable, List<String> argumentList) throws Exception {
        executable = absoluteFile(executable);
        output("execute( " + executable.getCanonicalPath() + listToString(argumentList) + " )");

        EXIT_CODE = 0;

        CommandLine commandLine = new CommandLine(executable.getCanonicalPath());
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

        EXIT_CODE = result.getExitCode();

        return result;
    }

    /**
     * Method to get the exit code of the last execute command
     *
     * @return
     */
    public int exitCode() {
        return EXIT_CODE;
    }

    /**
     * Method to check the exit code of the last execute command
     *
     * @param expectedExitCode
     * @throws Exception
     */
    public void checkExitCode(int expectedExitCode) throws Exception {
        if (EXIT_CODE != expectedExitCode) {
            throw new Exception("Expected exit code of " + expectedExitCode + " but execution returned " + EXIT_CODE);
        }
    }
}
