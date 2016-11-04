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
import org.devopology.tools.impl.DigestUtilsImpl;
import org.devopology.tools.impl.ExecUtilsImpl;
import org.devopology.tools.impl.FileUtilsImpl;
import org.devopology.tools.impl.JSONUtilsImpl;
import org.devopology.tools.impl.NetworkUtilsImpl;
import org.devopology.tools.impl.SSHUtilsImpl;
import org.devopology.tools.impl.SimpleLogger;
import org.devopology.tools.impl.StringUtilsImpl;
import org.devopology.tools.impl.SystemUtilsImpl;
import org.devopology.tools.impl.UnixUtilsImpl;
import org.devopology.tools.impl.ZipUtilsImpl;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Class to implement common methods
 */
public class Toolset {

    private static final String CLASS_NAME = Toolset.class.getName();
    private static final SimpleDateFormat ISO8601DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    private static final DecimalFormat secondsFormat = new DecimalFormat("#0.000");

    public static final String BANNER_LINE = "------------------------------------------------------------------------";

    private static SimpleLogger logger = null; //new SimpleLogger(CLASS_NAME);

    private Properties properties = null;

    private Long startTimestamp = null;
    private Long endTimestamp = null;

    /**
     * Track the current working directory
     */
    private CurrentDirectory currentDirectory = null;
    private DigestUtils digestUtils = null;
    private ExecUtilsImpl execUtils = null;
    private FileUtils fileUtils = null;
    private NetworkUtils networkUtils = null;
    private SSHUtils sshUtils = null;
    private StringUtils stringUtils = null;
    private ZipUtilsImpl zipUtils = null;
    private SystemUtils systemUtils = null;
    private UnixUtils unixUtils = null;
    private JSONUtils jsonUtils = null;

    /**
     * Constructor
     */
    public Toolset() {
        this.properties = new Properties();

        this.currentDirectory = new CurrentDirectory();
        this.digestUtils = new DigestUtilsImpl();
        this.execUtils = new ExecUtilsImpl(this);
        this.fileUtils = new FileUtilsImpl(this);
        this.jsonUtils = new JSONUtilsImpl(this);
        this.networkUtils = new NetworkUtilsImpl(this);
        this.sshUtils = new SSHUtilsImpl(this);
        this.stringUtils = new StringUtilsImpl();
        this.systemUtils = new SystemUtilsImpl(this);
        this.unixUtils = new UnixUtilsImpl(this);
        this.zipUtils = new ZipUtilsImpl(this);

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

        markStartTimestamp();
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

    /**
     * Method to get the CurrentDirectory
     *
     * @return the CurrentDirectory
     */
    public CurrentDirectory getCurrentDirectory() {
        return currentDirectory;
    }

    /**
     * Method to get a DigestUtils implementation
     *
     * @return a DigestUtils implementation
     */
    public DigestUtils getDigestUtils() {
        return this.digestUtils;
    }

    /**
     * Method to get an ExecUtils implementation
     *
     * @return an ExecUtils implementation
     */
    public ExecUtils getExecUtils() {
        return execUtils;
    }

    /**
     * Method to get a FileUtils implementation
     *
     * @return a FileUtils implementation
     */
    public FileUtils getFileUtils() {
        return fileUtils;
    }

    /**
     * Method to get a JSONUtils implementation
     *
     * @return a JSONUtils implementation
     */
    public JSONUtils getJsonUtils() {
        return jsonUtils;
    }

    /**
     * Method to get a NetworkUtils implementation
     *
     * @return a NetworkUtils implementation
     */
    public NetworkUtils getNetworkUtils() {
        return networkUtils;
    }

    /**
     * Method to get a StringUtils implementation
     *
     * @return a StringUtils implementation
     */
    public StringUtils getStringUtils() {
        return stringUtils;
    }

    /**
     * Method to get an SSHUtils implementation
     * s
     * @return an SSHUtils implementation
     */
    public SSHUtils getSSHUtils() {
        return sshUtils;
    }

    /**
     * Method to get a SystemUtils implementation
     *
     * @return a SystemUtils implementation
     */
    public SystemUtils getSystemUtils() {
        return systemUtils;
    }

    /**
     * Method to get a UnixUtils implementation
     *
     * @return a UnixUtils implementation
     */
    public UnixUtils getUnixUtils() {
        return unixUtils;
    }

    /**
     * Method to get a ZipUtils implementation
     *
     * @return a ZipUtils implementation
     */
    public ZipUtils getZipUtils() {
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
        return currentDirectory.absoluteFile(new File(path)).getAbsolutePath();
    }

    /**
     * Method convert a File into an absolute path based on the current working directory
     *
     * @param file
     * @return String
     * @throws IOException
     */
    public String absolutePath(File file) throws IOException {
        return currentDirectory.absoluteFile(file).getAbsolutePath();
    }

    /**
     * Method to convert a File into an absolute File based on the current working directory
     *
     * @param path
     * @return File
     * @throws IOException
     */
    public File absoluteFile(String path) throws IOException {
        return currentDirectory.absoluteFile(new File(path));
    }

    /**
     * Method to convert a File into an absolute File based on the current working directory
     *
     * @param file
     * @return File
     * @throws IOException
     */
    public File absoluteFile(File file) throws IOException {
        return currentDirectory.absoluteFile(file);
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

    /**
     * Method to convert a variable argument list into a an array
     *
     * @param arguments
     * @return String []
     */
    public String [] arguments(String... arguments) {
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

    /**
     * Method to log an error mesage and associated Throwable
     *
     * @param message
     * @param t
     */
    public void error(String message, Throwable t) {
        getLogger().error(message, t);
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

    public void markStartTimestamp() {
        this.startTimestamp = System.currentTimeMillis();
    }

    public void markEndTimestamp() {
        this.endTimestamp = System.currentTimeMillis();
    }

    /**
     * Method to log a banner
     *
     * @param message
     */
    public void banner(String message) {
        if (null == startTimestamp) {
            this.startTimestamp = System.currentTimeMillis();
        }

        info(BANNER_LINE);
        info(message);
        info(BANNER_LINE);
    }

    /**
     * Method to log a banner with extended data
     *
     * @param message
     * @param extended
     */
    public void banner(String message, boolean extended) {
        if (null == endTimestamp) {
            markEndTimestamp();
        }

        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        String totalMemoryString = getFileUtils().byteCountToDisplaySize(totalMemory);
        totalMemoryString = totalMemoryString.replaceAll(" ", "");

        String usedMemoryString = getFileUtils().byteCountToDisplaySize(usedMemory);
        usedMemoryString = usedMemoryString.replaceAll(" ", "");

        info(BANNER_LINE);
        info(message);
        info(BANNER_LINE);

        if (true == extended) {
            info("Total time: " + secondsFormat.format((float) (endTimestamp - startTimestamp) / 1000.0f) + " s");
            info("Finished at: " + ISO8601DateFormat.format(new Date()));
            info("Final memory: " + usedMemoryString + "/" + totalMemoryString);
            info(BANNER_LINE);
        }
    }
}
