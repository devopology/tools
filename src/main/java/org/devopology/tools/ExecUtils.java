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

import org.apache.commons.exec.*;
import org.devopology.tools.impl.ExecResultImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class to implement ExectUtils
 */
public class ExecUtils {

    protected CurrentDirectory currentDirectory = null;

    /**
     * Exit code of the last executable execution
     */
    protected static int EXIT_CODE = 0;

    ExecUtils(Toolset toolset) {
        this.currentDirectory = toolset.getCurrentDirectory();
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
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return ExecResult
     */
    public ExecResult execute(String executable, String [] arguments) throws IOException {
        return execute(currentDirectory.absolutePath(executable), arguments, 0);
    }

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @param expectedExitCode
     * @return ExecResult
     */
    public ExecResult execute(String executable, String [] arguments, int expectedExitCode) throws IOException {
        EXIT_CODE = 0;

        try {
            executable = currentDirectory.absolutePath(executable);

            File file = new File(executable);

            if (!file.exists()) {
                throw new IOException("execute() Exception : Command " + executable + " not found !");
            }

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

            ExecResultImpl result = new ExecResultImpl();
            result.setExitCode(resultHandler.getExitValue());
            result.setContent(outputStream.toString());

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
    public int exitCode() {
        return EXIT_CODE;
    }

    /**
     * Method to check the exit code of the last execute command
     *
     * @param expectedExitCode
     */
    public void checkExitCode(int expectedExitCode) throws IOException {
        if (EXIT_CODE != expectedExitCode) {
            throw new IOException("checkExitCode() Exception : Expected exit code of " + expectedExitCode + ", but execution returned " + EXIT_CODE);
        }
    }
}
