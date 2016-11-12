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

package org.devopology.tools.impl;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.devopology.tools.ExecResult;
import org.devopology.tools.ExecUtils;
import org.devopology.tools.Toolset;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to implement ExectUtils
 */
public class ExecUtilsImpl implements ExecUtils {

    protected Toolset toolset = null;

    /**
     * Exit code of the last executable execution
     */
    protected static int EXIT_CODE = 0;

    public ExecUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    /**
     * Method to convert a variable argument list into a an array
     *
     * @param arguments
     * @return String []
     */
    @Override
    public String [] arguments(String... arguments) {
        return arguments;
    }

    /* Method to convert a list of Strings into an array
     *
     * @param arguments
     * @return String []
     */
    @Override
    public String [] arguments(List<String> argumentList) {
        return argumentList.toArray(new String[argumentList.size()]);
    }

    /**
     * Mehthod to execute an executable
     *
     * @param executable
     * @return ExecResult
     * @throws IOException
     */
    public ExecResult execute(String executable) throws IOException {
        return execute(executable, null);
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return ExecResult
     */
    @Override
    public ExecResult execute(String executable, String [] arguments) throws IOException {
        return execute(executable, arguments, getEnvironmentVariableMap());
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return ExecResult
     */
    @Override
    public ExecResult execute(String executable, String [] arguments, Map<String, String> environmentVariableMap) throws IOException {
        EXIT_CODE = 0;

        if (null == executable) {
            throw new IllegalArgumentException("executable is null");
        }

        executable = executable.trim();

        if (executable.length() == 0) {
            throw new IllegalArgumentException("executable is empty");
        }

        try {
            executable = toolset.getCurrentDirectory().absolutePath(executable);

            File file = new File(executable);

            if (!file.exists()) {
                throw new IOException("execute() Exception : Command " + executable + " not found !");
            }

            if (!file.isFile()) {
                throw new IOException("execute() Exception : " + executable + " is not a file");
            }

            CommandLine commandLine = new CommandLine(executable);
            if (null != arguments) {
                for (String argument : arguments) {
                    commandLine.addArgument(argument);
                }
            }

            //toolset.info("execute() " + commandLine.toString());

            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

            ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
            DefaultExecutor defaultExecutor = new DefaultExecutor();
            defaultExecutor.setWorkingDirectory(new File(toolset.getCurrentDirectory().getPath()));
            defaultExecutor.setWatchdog(watchdog);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            defaultExecutor.setStreamHandler(streamHandler);


            defaultExecutor.execute(commandLine, environmentVariableMap, resultHandler);
            resultHandler.waitFor();

            ExecResultImpl result = new ExecResultImpl();
            result.setExitCode(resultHandler.getExitValue());
            result.setOutput(outputStream.toString());

            EXIT_CODE = resultHandler.getExitValue();

            return result;
        }
        catch (IOException ioe) {
            throw ioe;
        }
        catch (Throwable t) {
            throw new IOException("execute() Exception ", t);
        }
    }

    /**
     * Method to execute an executable with an expected exit code
     * If the exit code doesn't match the exepectedExitcode then
     * and IOException is thrown
     *
     * @param executable
     * @param arguments
     * @param expectedExitCode
     * @return ExecResult
     */
    @Override
    public ExecResult execute(String executable, String [] arguments, int expectedExitCode) throws IOException {
        return execute(executable, arguments, expectedExitCode, getEnvironmentVariableMap());
    }

    /**
     * Method to execute an executable with an expected exit code
     * If the exit code doesn't match the exepectedExitcode then
     * and IOException is thrown
     *
     * @param executable
     * @param arguments
     * @param expectedExitCode
     * @param environmentVariableMap
     * @return ExecResult
     */
    @Override
    public ExecResult execute(String executable, String [] arguments, int expectedExitCode, Map<String, String> environmentVariableMap) throws IOException {
        EXIT_CODE = 0;

        if (null == executable) {
            throw new IllegalArgumentException("executable is null");
        }

        executable = executable.trim();

        if (executable.length() == 0) {
            throw new IllegalArgumentException("executable is empty");
        }

        try {
            executable = toolset.getCurrentDirectory().absolutePath(executable);

            File file = new File(executable);

            if (!file.exists()) {
                throw new IOException("execute() Exception : " + executable + " not found");
            }

            if (!file.isFile()) {
                throw new IOException("execute() Exception : " + executable + " is not a file");
            }

            CommandLine commandLine = new CommandLine(executable);
            if (null != arguments) {
                for (String argument : arguments) {
                    commandLine.addArgument(argument);
                }
            }

            //toolset.info("execute() " + commandLine.toString());

            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

            ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
            DefaultExecutor defaultExecutor = new DefaultExecutor();
            defaultExecutor.setWorkingDirectory(new File(toolset.getCurrentDirectory().getPath()));
            defaultExecutor.setWatchdog(watchdog);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            defaultExecutor.setStreamHandler(streamHandler);

            defaultExecutor.execute(commandLine, environmentVariableMap, resultHandler);
            resultHandler.waitFor();

            ExecResultImpl result = new ExecResultImpl();
            result.setExitCode(resultHandler.getExitValue());
            result.setOutput(outputStream.toString());
            EXIT_CODE = resultHandler.getExitValue();

            if (EXIT_CODE != expectedExitCode) {
                throw new IOException("execute() Exception : Expected exit code of " + expectedExitCode + ", but execution returned " + EXIT_CODE);
            }

            return result;
        }
        catch (IOException ioe) {
            throw ioe;
        }
        catch (Throwable t) {
            throw new IOException("execute() Exception ", t);
        }
    }

    /**
     * Method to get the exit code of the last execute command
     *
     * @return int
     */
    @Override
    public int exitCode() {
        return EXIT_CODE;
    }

    /**
     * Method to check the exit code of the last execute command
     *
     * @param expectedExitCode
     */
    @Override
    public void checkExitCode(int expectedExitCode) throws IOException {
        if (EXIT_CODE != expectedExitCode) {
            throw new IOException("checkExitCode() Exception : Expected exit code of " + expectedExitCode + ", but execution returned " + EXIT_CODE);
        }
    }

    /**
     * Method to get a copy of the System environment variable Map
     *
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getEnvironmentVariableMap() {
        return new HashMap<String, String>(System.getenv());
    }
}
