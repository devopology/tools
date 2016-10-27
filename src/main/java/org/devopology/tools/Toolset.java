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

package org.devopology.tools;

import org.devopology.tools.impl.ExecutionResultImpl;
import org.devopology.tools.impl.ZipUtils;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.exec.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class to implement common methods
 */
public class Toolset {

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final static JSONParser jsonParser = new JSONParser();
    private final static String TIMESTAMP_MESSAGE_SEPARATOR = " | ";

    protected File currentDirectory = null;

    public final static String EXECUTE_SHOW_CONTENT = "toolset.execute.showContent";

    public final static String SYSTEMD_SERVICE_ROOT = "/lib/systemd/system";

    /**
     * Common system executables
     */
    public final static String CP = "/bin/cp";
    public final static String FIND = "/bin/find";
    public final static String GREP = "/bin/grep";
    public final static String GZIP = "/bin/gzip";
    public final static String KILL = "/bin/kill";
    public final static String LS = "/bin/ls";
    public final static String MKDIR = "/bin/mkdir";
    public final static String PS = "/bin/ps";
    public final static String RM = "/bin/rm";
    public final static String TAR = "/bin/tar";
    public final static String UNZIP = "/bin/unzip";
    public final static String ZIP = "/bin/zip";

    /**
     * Systemdctl executable
     */
    public final static String SYSTEMCTL = "/bin/systemctl";

    /**
     * Exit code of the last executable execution
     */
    private static int EXIT_CODE = 0;

    /**
     * File types
     */
    public final static int NOT_FOUND = -1;
    public final static int DIRECTORY = 0;
    public final static int FILE = 1;

    /**
     * Configuration
     */
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
        System.out.println(simpleDateFormat.format(new Date()) + TIMESTAMP_MESSAGE_SEPARATOR + message);
    }

    private static String listToString(List<String> list) {
        StringBuilder stringBuilder = null;

        if (null != list) {
            stringBuilder = new StringBuilder();
            for (String string : list) {
                stringBuilder.append(" ");
                stringBuilder.append(string);
            }
        }

        if (null != stringBuilder) {
            return stringBuilder.toString();
        }
        else {
            return null;
        }
    }

    private static String arrayToString(String [] array) {
        StringBuilder stringBuilder = null;

        if (null != array) {
            stringBuilder = new StringBuilder();;

            for (int i=0; i<array.length; i++) {
                if (i > 0) {
                    stringBuilder.append(" ");
                }

                stringBuilder.append(array[i]);
            }
        }

        if (null != stringBuilder) {
            return stringBuilder.toString();
        }
        else {
            return null;
        }
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
     * @return String
     */
    public String getConfiguration(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Method to convert a string with CRLF to a list
     *
     * @param string
     * @return List<String>
     */
    public List<String> stringToList(String string) {
        try {
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
        catch (Throwable t) {
            throw new ToolsetException("stringToList() Exception ", t);
        }
    }

    /**
     * Method to get a filename without extension
     *
     * @param filename
     * @return String
     */
    public String noExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }

    /**
     * Method to a filename from an absolute path
     *
     * @param path
     * @return String
     */
    public String noPath(String path) {
        return absoluteFile(path).getName();
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
                    System.out.println(simpleDateFormat.format(new Date()) + TIMESTAMP_MESSAGE_SEPARATOR + "println( " + line + " )");
                }
            }
            catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        else {
            System.out.println(simpleDateFormat.format(new Date()) + TIMESTAMP_MESSAGE_SEPARATOR + "println( " + message + " )");
        }
    }

    /**
     * Method to change the current working directory
     *
     * @param path
     */
    public void cd(String path) {
        try {
            File file = absoluteFile(path);
            output("cd( " + file.getCanonicalPath() + " )");

            if (false == file.exists()) {
                throw new IOException(path + " doesn't exist");
            }

            if (false == file.isDirectory()) {
                throw new IOException(path + " isn't a directory");
            }

            this.currentDirectory = file.getAbsoluteFile();
        }
        catch (Throwable t) {
            throw new ToolsetException("cd() Exception ", t);
        }
    }

    /**
     * Method to get the current working directory
     *
     * @return String
     */
    public String pwd() {
        try {
            return this.currentDirectory.getCanonicalPath();
        }
        catch (Throwable t) {
            throw new ToolsetException("pwd() Exception ", t);
        }
    }

    /**
     * Method to get an absolute path
     *
     * @param path
     * @return String
     */
    public String absolutePath(String path) {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + path);
        }

        return file.getAbsolutePath();
    }

    /**
     * Method to get an absolute path
     *
     * @param file
     * @return String
     */
    public String absolutePath(File file) {
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + file.getName());
        }
        
        return file.getAbsolutePath();
    }


    /**
     * Method to get an absolute File
     *
     * @param path
     * @return File
     */
    public File absoluteFile(String path) {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(pwd() + File.separator + path);
        }

        return file;
    }

    /**
     * Method to convert a variable argument list into a an array
     *
     * @param arguments
     * @return String []
     */
    public String [] arguments(String ... arguments) {
        return arguments;
    }

    /* Method to convert a list of Strings into an array
     *
     * @param arguments
     * @return String []
     */
    public String [] arguments(List<String> argumentList) {
        return argumentList.toArray(new String[argumentList.size()]);
    }

    /**
     * Method to create a directory, creating any necassary parent directories
     *
     * @param path
     */
    public void mkdirs(String path) {
        try {
            File file = absoluteFile(path);
            output("mkdir( " + file.getCanonicalPath() + " )");
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    throw new ToolsetException("mkdirs() Exception : can't create output directory");
                }
            }
        }
        catch (ToolsetException te) {
            throw te;
        }
        catch (Throwable t) {
            throw new ToolsetException("mkdirs() Exception ", t);
        }
    }

    public void rm(String path) {
        try {
            File file = absoluteFile(path);
            output("rm( " + file.getCanonicalPath() + ")");

            if (file.exists()) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        throw new ToolsetException("rm() Exception : can't delete file");
                    }
                } else {
                    throw new ToolsetException("rm() Exception : not a file");
                }
            }
        }
        catch (ToolsetException te) {
            throw te;
        }
        catch (Throwable t) {
            throw new ToolsetException("mkdirs() Exception ", t);
        }
    }

    /**
     * Method to remove a directory, recursively deleting any children
     *
     * @param path
     */
    public void rmdir(String path) {
        try {
            File file = absoluteFile(path);
            output("rmdir( " + file.getCanonicalPath() + " )");
            FileUtils.deleteDirectory(file);
        }
        catch (ToolsetException te) {
            throw te;
        }
        catch (Throwable t) {
            throw new ToolsetException("rmdir() Exception ", t);
        }
    }

    /**
     * Method to load Properties from a file
     *
     * @param path
     * @return Properties
     */
    public Properties loadProperties(String path) {
        try {
            File file = absoluteFile(path);
            output("loadProperties( " + file.getCanonicalPath() + " )");
            Properties properties = new Properties();
            properties.load(new FileReader(path));
            return properties;
        }
        catch (Throwable t) {
            throw new ToolsetException("loadProperties() Exception ", t);
        }
    }

    /**
     * Method to determine if a file exists
     *
     * @param path
     * @return boolean
     */
    public boolean exists(String path) {
        return new File(absolutePath(path)).exists();
    }

    /**
     * Method to get the "type" of a file
     *
     * @param path
     * @return int
     */
    public int type(String path) {
        try {
            File file = new File(absolutePath(path));
            if (!file.exists()) {
                output("type( " + path + " ) = NOT_FOUND");
                return NOT_FOUND;
            }
            else if (file.isDirectory()) {
                output("type( " + file.getCanonicalPath() + " ) = DIRECTORY");
                return DIRECTORY;
            } else {
                output("type( " + file.getCanonicalPath() + " ) = FILE");
                return FILE;
            }
        }
        catch (Throwable t) {
            throw new ToolsetException("type() Exception ", t);
        }
    }

    /**
     * Method to rename a file
     *
     * @param oldPath
     * @param newPath
     */
    public void rename(String oldPath, String newPath) {
        try {
            File oldFile = absoluteFile(oldPath);
            File newFile = absoluteFile(newPath);
            output("rename( " + oldFile.getCanonicalPath() + ", " + newFile.getCanonicalPath() + " )");
            oldFile.renameTo(newFile);
        }
        catch (Throwable t) {
            throw new ToolsetException("cd() Exception ", t);
        }
    }

    public List<String> listFiles(String path) {
        try {
            List<String> result = new ArrayList<String>();
            File file = absoluteFile(path);
            output("listFiles( " + file.getCanonicalPath() + " )");

            File [] files = file.listFiles();
            if (null != files) {
                result = new ArrayList<String>();
                for (File child : files) {
                    result.add(absolutePath(child));
                }
            }

            return result;
        }
        catch (Throwable t) {
            throw new ToolsetException("cd() Exception ", t);
        }
    }

    /**
     * Method to recursive list all children, grandchildren, etc.
     *
     * @param path
     * @return List<String>
     */
    public List<String> recursivelyListFiles(String path) {
        try {
            List<String> result = new ArrayList<String>();
            File file = absoluteFile(path);
            output("recursivelyListFiles( " + file.getCanonicalPath() + " )");

            if (file.isDirectory()) {
                File [] children = file.listFiles();
                if (null != children) {
                    for (File child : children) {
                        walkTree(file.getCanonicalFile(), result);
                    }
                }
            }

            return result;
        }
        catch (Throwable t) {
            throw new ToolsetException("cd() Exception ", t);
        }
    }

    private void walkTree(File file, List<String> list) throws IOException {
        if (file.isFile()) {
            list.add(absolutePath(file));
        }
        else {
            File [] children = file.listFiles();
            if (null != children) {
                for (File child : children) {
                    walkTree(child.getCanonicalFile(), list);
                }
            }
        }
    }

    /**
     * Method to get the "type" of a file as a String
     *
     * @param path
     * @return String
     */
    public String typeString(String path) {
        try {
            File file = absoluteFile(path);
            String result = null;
            if (false == file.exists()) {
                result = "NOT FOUND";
            } else if (file.isDirectory()) {
                result = "DIRECTORY";
            } else if (file.isFile()) {
                result = "FILE";
            } else {
                throw new RuntimeException("Developer error!!!");
            }

            output("typeString( " + file.getCanonicalPath() + " ) = " + result);

            return result;
        }
        catch (Throwable t) {
            throw new ToolsetException("typeString() Exception ", t);
        }
    }

    /**
     * Method to replace Properties in a String
     *
     * @param content
     * @return String
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
     * @param path
     */
    public void replaceProperties(Properties properties, String path) {
        replaceProperties(properties, path, path);
    }

    /**
     * Method to replace Properties in a file
     *
     * @param properties
     * @param inputFilePath
     * @param outputFilePath
     */
    public void replaceProperties(Properties properties, String inputFilePath, String outputFilePath) {
        try {
            File inputFile = absoluteFile(inputFilePath);
            File outputFile = absoluteFile(outputFilePath);
            output("replaceProperties( [properties], " + inputFile.getCanonicalPath() + ", " + outputFile.getCanonicalPath() + " )");
            String content = readFile(inputFile.getCanonicalPath());

            Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> entry = iterator.next();
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();

                content = content.replaceAll(Pattern.quote("${" + key + "}"), value);
            }

            writeFile(outputFile.getCanonicalPath(), content);
        }
        catch (Throwable t) {
            throw new ToolsetException("typeString() Exception ", t);
        }
    }

    /**
     * Method to read a file's contents into String
     *
     * @param path
     * @return String
     */
    public String readFile(String path) {
        try {
            return FileUtils.readFileToString(absoluteFile(path), StandardCharsets.UTF_8);
        }
        catch (Throwable t) {
            throw new ToolsetException("readFile() Exception ", t);
        }
    }

    /**
     * Method to write a String to a file
     *
     * @param path
     * @param content
     */
    public void writeFile(String path, String content) {
        try {
            File file = absoluteFile(path);
            output("writeFile( " + file.getCanonicalPath() + ", [getContent])");

            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(content);
            printWriter.close();
        }
        catch (Throwable t) {
            throw new ToolsetException("writeFile() Exception ", t);
        }
    }

    /**
     * Method to parse a String as a JSONObject
     *
     * @param json
     * @return JSONOBject
     */
    public JSONObject parseJSONObject(String json) {
        try {
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
        catch (Throwable t) {
            throw new ToolsetException("parseJSONObject() Exception ", t);
        }
    }

    /**
     * Method to load a file's content as a JSONObject
     *
     * @param path
     * @return JSONObject
     */
    public JSONObject loadJSONObject(String path) {
        try {
            File file = absoluteFile(path);
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
        catch (Throwable t) {
            throw new ToolsetException("loadJSONObject() Exception ", t);
        }
    }

    /**
     * Method to parse a String as a JSONArray
     *
     * @param json
     * @return JSONArray
     */
    public JSONArray parseJSONArray(String json) {
        try {
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
        catch (Throwable t) {
            throw new ToolsetException("parseJSONArray() Exception ", t);
        }
    }

    /**
     * Method to load a file's content as a JSONArray
     *
     * @param path
     * @return JSONArray
     */
    public JSONArray loadJSONArray(String path) {
        try {
            File file = absoluteFile(path);
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
        catch (Throwable t) {
            throw new ToolsetException("loadJSONArray() Exception ", t);
        }
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return ExecutionResult
     */
    public ExecutionResult execute(String executable, String [] arguments) {
        return execute(absolutePath(executable), arguments, 0);
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @param expectedExitCode
     * @return ExecutionResult
     */
    public ExecutionResult execute(String executable, String [] arguments, int expectedExitCode) {
        try {
            executable = absolutePath(executable);

            // Platform specific : Ubuntu Linux / RedHat Linux
            //
            // The follow code checks to see if the executable can be found.
            //
            // If it isn't found, we check the executable path.
            //
            // If it starts with /bin, then we prefix the executable path with /usr
            // since some Linux variants have alternate locations for
            // system executables

            if (!exists(executable)) {
                if (executable.startsWith("/bin/")) {
                    String alternateExecutable = "/usr" + executable;
                    if (exists(alternateExecutable)) {
                        executable = alternateExecutable;
                    }
                    else {
                        throw new Exception(executable + " doesn't exist !");
                    }
                }
                else {
                    throw new Exception(executable + " doesn't exist !");
                }
            }

            output("execute( " + executable + ", " + arrayToString(arguments) + " )");

            EXIT_CODE = 0;

            CommandLine commandLine = new CommandLine(executable);
            if (null != arguments) {
                for (String argument : arguments) {
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

            if ("true".equals(getConfiguration(EXECUTE_SHOW_CONTENT, "false"))) {
                String content = result.getContent();
                content = content.trim();
                if (content.length() > 0) {
                    println(result.getContent());
                }
            }

            return result;
        }
        catch (ToolsetException te) {
            throw te;
        }
        catch (Throwable t) {
            throw new ToolsetException("execute() Exception ", t);
        }
    }

    /**
     * Method to get the exit code of the last execute command
     *
     * @return int
     */
    public int exitCode() {
        return EXIT_CODE;
    }

    /**
     * Method to check the exit code of the last execute command
     *
     * @param expectedExitCode
     */
    public void checkExitCode(int expectedExitCode) {
        if (EXIT_CODE != expectedExitCode) {
            throw new ToolsetException("checkExitCode() Exception : Expected exit code of " + expectedExitCode + ", but execution returned " + EXIT_CODE);
        }
    }

    /**
     * Method to zip a directory
     *
     * @param sourcePath
     * @param zipFilename
     */
    public void zip(String sourcePath, String zipFilename) {
        try {
            File sourceFile = absoluteFile(sourcePath);
            File zipFile = absoluteFile(zipFilename);

            if (!zipFile.getParentFile().exists()) {
                if (!zipFile.getParentFile().mkdirs()) {
                    throw new ToolsetException("zip() Exception : can't create output directory path");
                }
            }

            output("zip( " + sourceFile.getCanonicalFile() + ", " + zipFile.getCanonicalPath() + " )");
            ZipUtils.zipFolder(sourceFile.getCanonicalFile(), zipFile.getCanonicalFile());
        }
        catch (ToolsetException te) {
            throw te;
        }
        catch (Throwable t) {
            throw new ToolsetException("zip() Exception ", t);
        }
    }

    /**
     * Method to unzip a file into a directory
     *
     * @param zipFilename
     * @param destinationPath
     */
    public void unzip(String zipFilename, String destinationPath) {
        unzip(zipFilename, destinationPath, false);
    }

    /**
     * Method to unzip a file into a directory
     *
     * @param zipFilename
     * @param destinationPath
     * @param overwrite
     */
    public void unzip(String zipFilename, String destinationPath, boolean overwrite) {
        try {
            zipFilename = absolutePath(zipFilename);
            File destinationFile = absoluteFile(destinationPath);
            output("unzip( " + zipFilename + ", " + destinationFile.getCanonicalPath() + " )");

            if (overwrite) {
                if (exists(destinationPath)) {
                    rmdir(destinationPath);
                }
            }

            ZipFile zipFile = new ZipFile(zipFilename);
            zipFile.extractAll(destinationFile.getCanonicalPath());
        }
        catch (Throwable t) {
            throw new ToolsetException("zip() Exception ", t);
        }
    }
}
