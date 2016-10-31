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

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class to implement common methods
 */
public class Toolset {

    private final static String CLASS_NAME = Toolset.class.getName();
    private static SimpleLogger logger = null;
    private final static JSONParser jsonParser = new JSONParser();
    private final static SimpleDateFormat ISO8601DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    private final static DecimalFormat secondsFormat = new DecimalFormat("#.000");

    /**
     * Track the current working directory
     */
    private CurrentDirectory currentDirectory = null;
    private ExecUtils execUtils = null;
    private FileUtils fileUtils = null;
    private NetworkUtils networkUtils = null;
    private StringUtils stringUtils = null;
    private ZipUtils zipUtils = null;

    private Properties properties = null;

    /**
     * Constructor
     */
    public Toolset() {
        this.properties = new Properties();
        this.currentDirectory = new CurrentDirectory();
        this.execUtils = new ExecUtils(this);
        this.fileUtils = new FileUtils(this);
        this.networkUtils = new NetworkUtils(this);
        this.stringUtils = new StringUtils();
        this.zipUtils = new ZipUtils(this);

        getProperties().setProperty("org.slf4j.simpleLogger.defaultLogLevel", "info");
        //getProperties().setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");
        getProperties().setProperty("org.slf4j.simpleLogger.showDateTime", "false");
        getProperties().setProperty("org.slf4j.simpleLogger.showThreadName", "false");
        getProperties().setProperty("org.slf4j.simpleLogger.showLogName", "false");
        getProperties().setProperty("org.slf4j.simpleLogger.logFile", "System.out");
        getProperties().setProperty("org.slf4j.simpleLogger.levelInBrackets", "true");
        getProperties().setProperty("org.slf4j.simpleLogger.log.Sisu", "info");
        getProperties().setProperty("org.slf4j.simpleLogger.warnLevelString", "WARNING");

        logger = new SimpleLogger(CLASS_NAME, getProperties());
        logger.init(getProperties());
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

    public ExecUtils getExecUtils() {
        return execUtils;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public NetworkUtils getNetworkUtils() {
        return networkUtils;
    }

    public StringUtils getStringUtils() {
        return stringUtils;
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
     * Method to log an error message
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
     * Method to log an error message
     *
     * @param message
     * @param object
     */
    public void error(String message, Object object) {
        getLogger().error(message, object);
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
     * Method to change the current working directory
     *
     * @param file
     */
    public void changeDirectory(File file) throws IOException {
        getCurrentDirectory().changeDirectory(file);
    }

    /**
     * Method to get the current working directory
     *
     * @return String
     */
    public File pwd() throws IOException {
        return getCurrentDirectory().getFile().getCanonicalFile();
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

    /**
     * Method to get a String representing the current date / time in ISO8601 fornmat
     *
     * @return String
     */
    public String nowAs8601DateTime() {
        return ISO8601DateFormat.format(new Date());
    }
}
