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
import org.devopology.tools.impl.*;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class to implement common methods
 */
public class Toolset {

    private static final String CLASS_NAME = Toolset.class.getName();
    public static final String BANNER_LINE = "------------------------------------------------------------------------";

    private static SimpleLogger logger = null; //new SimpleLogger(CLASS_NAME);

    /**
     * Track the current working directory
     */
    private CurrentDirectory currentDirectory = null;
    private ExecUtilsImpl execUtils = null;
    private FileUtils fileUtils = null;
    private NetworkUtils networkUtils = null;
    private StringUtils stringUtils = null;
    private ZipUtilsUtilsImpl zipUtils = null;
    private SystemUtils systemUtils = null;
    private UnixUtils unixUtils = null;
    private JSONUtils jsonUtils = null;

    private Properties properties = null;

    /**
     * Constructor
     */
    public Toolset() {
        this.properties = new Properties();

        this.currentDirectory = new CurrentDirectory();
        this.execUtils = new ExecUtilsImpl(this);
        this.fileUtils = new FileUtilsImpl(this);
        this.jsonUtils = new JSONUtilsImpl(this);
        this.networkUtils = new NetworkUtilsImpl(this);
        this.stringUtils = new StringUtilsImpl();
        this.systemUtils = new SystemUtilsImpl(this);
        this.unixUtils = new UnixUtilsImpl(this);
        this.zipUtils = new ZipUtilsUtilsImpl(this);

        getProperties().setProperty("org.slf4j.simpleLogger.defaultLogLevel", "info");
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
        } else {
            return null;
        }
    }

    private static String arrayToString(String[] array) {
        StringBuilder stringBuilder = null;

        if (null != array) {
            stringBuilder = new StringBuilder();
            ;

            for (int i = 0; i < array.length; i++) {
                if (i > 0) {
                    stringBuilder.append(" ");
                }

                stringBuilder.append(array[i]);
            }
        }

        if (null != stringBuilder) {
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Toolset.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                return ste.getClassName();
            }
        }

        return getClass().getName();
    }

    private File absoluteFile(String path) throws IOException {
        return absoluteFile(new File(path));
    }

    private File absoluteFile(File path) throws IOException {
        if (path.isAbsolute()) {
            return path.getAbsoluteFile();
        } else {
            return new File(getCurrentDirectory().getPath() + File.separator + path.getPath());
        }
    }

    private String absolutePath(File path) throws IOException {
        return absoluteFile(path).getAbsolutePath();
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * Method to get the slf4j Logger
     *
     * @return Logger
     */
    public Logger getLogger() {
        return logger;
    }

    public CurrentDirectory getCurrentDirectory() {
        return currentDirectory;
    }

    public ExecUtilsImpl getExecUtils() {
        return execUtils;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public JSONUtils getJsonUtils() {
        return jsonUtils;
    }

    public NetworkUtils getNetworkUtils() {
        return networkUtils;
    }

    public StringUtils getStringUtils() {
        return stringUtils;
    }

    public SystemUtils getSystemUtils() {
        return systemUtils;
    }

    public UnixUtils getUnixUtils() {
        return unixUtils;
    }

    public ZipUtilsUtilsImpl getZipUtils() {
        return zipUtils;
    }

    /**
     * Method to convert a path into an absolute path based on the current working directory
     *
     * @param path
     * @return String
     * @throws IOException
     */
    public String absolutePath(String path) throws IOException {
        return absoluteFile(new File(path)).getAbsolutePath();
    }

    /**
     * Method to convert a path into a canonical path based on the current working directory
     *
     * @param path
     * @return String
     * @throws IOException
     */
    public String canonicalPath(String path) throws IOException {
        return absoluteFile(path).getCanonicalPath();
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
    public String[] stringToArray(String string) throws IOException {
        List<String> result = new ArrayList<String>();

        if (null != string) {
            String line = null;
            BufferedReader bufferedReader = new BufferedReader(new StringReader(string));

            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        }

        return result.toArray(new String[0]);
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
        return absoluteFile(path).getName();
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

    public String[] arguments(String... arguments) {
        return arguments;
    }

    /**
     * Method to log an info message
     *
     * @param message
     */
    public void info(String message) {
        getLogger().info(message);
    }

    /**
     * Method to log a warning warning
     *
     * @param message
     */
    public void warn(String message) {
        getLogger().warn(message);
    }

    /**
     * Method to log an error message
     *
     * @param message
     */
    public void error(String message) {
        getLogger().error(message);
    }

    public void error(String message, Object object) {
        getLogger().error(message, object);
    }

    /**
     * Method to change the current working directory
     *
     * @param path
     */
    public String changeDirectory(String path) throws IOException {
        return getCurrentDirectory().changeDirectory(path);
    }

    /**
     * Method to get the current working directory
     *
     * @return String
     */
    public String pwd() throws IOException {
        return getCurrentDirectory().getPath();
    }

    /**
     * Method to log a banner
     *
     * @param message
     */
    public void banner(String message) {
        info(BANNER_LINE);
        info(message);
        info(BANNER_LINE);
    }
}
