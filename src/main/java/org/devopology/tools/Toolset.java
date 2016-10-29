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

import org.apache.commons.io.FilenameUtils;
import org.devopology.tools.impl.SimpleLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Class to implement common methods
 */
public class Toolset {

    private final static String CLASS_NAME = Toolset.class.getName();
    private final static SimpleLogger logger = new SimpleLogger(CLASS_NAME);
    private final static JSONParser jsonParser = new JSONParser();

    /**
     * File types
     */
    public final static int NOT_FOUND = -1;
    public final static int DIRECTORY = 0;
    public final static int FILE = 1;

    /**
     * Track the current working directory
     */
    protected CurrentDirectory currentDirectory = null;
    protected FileUtils fileUtils = null;
    protected ExecUtils execUtils = null;
    protected NetworkUtils networkUtils = null;
    protected ZipUtils zipUtils = null;

    protected Properties properties = null;

    /**
     * Constructor
     */
    public Toolset() {
        this.properties = new Properties();
        this.currentDirectory = new CurrentDirectory();
        this.fileUtils = new FileUtils(this);
        this.execUtils = new ExecUtils(this);
        this.networkUtils = new NetworkUtils(this);
        this.zipUtils = new ZipUtils(this);

        getProperties().setProperty("org.slf4j.simpleLogger.defaultLogLevel", "info");
        getProperties().setProperty("org.slf4j.simpleLogger.showDateTime", "false");
        getProperties().setProperty("org.slf4j.simpleLogger.showThreadName", "false");
        getProperties().setProperty("org.slf4j.simpleLogger.showLogName", "false");
        getProperties().setProperty("org.slf4j.simpleLogger.logFile", "System.out");
        getProperties().setProperty("org.slf4j.simpleLogger.levelInBrackets", "true");
        getProperties().setProperty("org.slf4j.simpleLogger.log.Sisu", "info");
        getProperties().setProperty("org.slf4j.simpleLogger.warnLevelString", "WARNING");

        logger.init(properties);
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

    public CurrentDirectory getCurrentDirectory() {
        return currentDirectory;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public ExecUtils getExecUtils() {
        return execUtils;
    }

    public NetworkUtils getNetworkUtils() {
        return networkUtils;
    }

    public ZipUtils getZipUtils() { return zipUtils; }

    public Properties getProperties() {
        return properties;
    }

    /**
     * Method to get a property value based on key
     *
     * @param key
     * @return String
     */
    public String getProperty(String key) {
        return properties.getProperty(key, null);
    }

    /**
     * Method to get a property value based on key
     *
     * @param key
     * @param defaultValue
     * @return String
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Method to convert a string with CRLF to a list
     *
     * @param string
     * @return List<String>
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
     * Method to convert a string with CRLF to a list
     *
     * @param string
     * @return String []
     */
    public String [] stringToArray(String string) throws IOException {
        List<String> result = new ArrayList<String>();

        if (null != string) {
            String line = null;
            BufferedReader bufferedReader = new BufferedReader(new StringReader(string));

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }

        return result.toArray(new String [0]);
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
    public String noPath(String path) throws IOException {
        return currentDirectory.absoluteFile(path).getName();
    }

    /**
     * Method to get the slf4j Logger
     *
     * @return Logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Method to log a debug message
     *
     * @param object
     */
    public void debug(Object object) {
        if (null == object) {
            object = "null";
        }
        getLogger().debug(object.toString());
    }

    /**
     * Method to log a trace message
     *
     * @param object
     */
    public void trace(Object object) {
        if (null == object) {
            object = "null";
        }
        getLogger().trace(object.toString());
    }

    /**
     * Method to log an info message
     *
     * @param object
     */
    public void info(Object object) {
        if (null == object) {
            object = "null";
        }
        getLogger().info(object.toString());
    }

    /**
     * Method to log a warning warning
     *
     * @param object
     */
    public void warn(Object object) {
        if (null == object) {
            object = "null";
        }
        getLogger().warn(object.toString());
    }

    /**
     * Method to log an error error message
     *
     * @param object
     */
    public void error(Object object) {
        if (null == object) {
            object = "null";
        }
        getLogger().error(object.toString());
    }

    /**
     * Method to change the current working directory
     *
     * @param path
     */
    public void changeDirectory(String path) throws IOException {
        getCurrentDirectory().changeDirectory(path);
    }

    /**
     * Method to get the current working directory
     *
     * @return String
     */
    public String pwd() throws IOException {
        return getCurrentDirectory().getCurrentDirectory().getAbsolutePath();
    }

    /**
     * Method to get the "type" of a file
     *
     * @param path
     * @return int
     */
    public int type(String path) throws IOException {
        File file = currentDirectory.absoluteFile(path);
        if (!file.exists()) {
            return NOT_FOUND;
        }
        else if (file.isDirectory()) {
            info("type( " + file.getCanonicalPath() + " ) = DIRECTORY");
            return DIRECTORY;
        }
        else {
            info("type( " + file.getCanonicalPath() + " ) = FILE");
            return FILE;
        }
    }

    /**
     * Method to get the "type" of a file as a String
     *
     * @param path
     * @return String
     */
    public String typeString(String path) throws IOException {
        int type = type(path);
        switch(type) {
            case NOT_FOUND:
                return "NOT_FOUND";
            case DIRECTORY:
                return "DIRECTORY";
            case FILE:
                return "FILE";
            default:
                throw new IOException("Unknown file type [" + type + "]");
        }
    }

    /**
     * Method to parse a String as a JSONObject
     *
     * @param json
     * @return JSONOBject
     */
    public JSONObject parseJSONObject(String json) throws IOException {
        try {
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
            throw new IOException("parseJSONObject() Exception ", t);
        }
    }

    /**
     * Method to load a file's content as a JSONObject
     *
     * @param path
     * @return JSONObject
     */
    public JSONObject loadJSONObject(String path) throws IOException {
            File file = currentDirectory.absoluteFile(path);
            try {
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
                throw new IOException("loadJSONObject() Exception ", t);
            }
    }

    /**
     * Method to parse a String as a JSONArray
     *
     * @param json
     * @return JSONArray
     */
    public JSONArray parseJSONArray(String json) throws IOException {
        try {
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
            throw new IOException("parseJSONArray() Exception ", t);
        }
    }

    /**
     * Method to load a file's content as a JSONArray
     *
     * @param path
     * @return JSONArray
     */
    public JSONArray loadJSONArray(String path) throws IOException {
        try {
            File file = currentDirectory.absoluteFile(path);
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
            throw new IOException("loadJSONArray() Exception ", t);
        }
    }
}
