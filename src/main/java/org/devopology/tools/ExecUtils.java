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

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ExecUtils {

    /**
     * Method to convert a variable argument list into a an array
     *
     * @param arguments
     * @return String []
     */
    String [] arguments(String... arguments);

    /* Method to convert a list of Strings into an array
     *
     * @param arguments
     * @return String []
     */
    String [] arguments(List<String> argumentList);

    /**
     * Mehthod to execute an executable
     *
     * @param executable
     * @return ExecResult
     * @throws IOException
     */
    ExecResult execute(String executable) throws IOException;

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return ExecResult
     */
    ExecResult execute(String executable, String [] arguments) throws IOException;

    /**
     * Method to execute an executable
     *
     * @param executable
     * @param arguments
     * @return ExecResult
     */
    ExecResult execute(String executable, String [] arguments, Map<String, String> environmentVariableMap) throws IOException;

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
    ExecResult execute(String executable, String [] arguments, int expectedExitCode) throws IOException;

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
    ExecResult execute(String executable, String [] arguments, int expectedExitCode, Map<String, String> environmentVariableMap) throws IOException;

    /**
     * Method to get the exit code of the last executed command
     *
     * @return int
     */
    int exitCode();

    /**
     * Method to check the exit code of the last executed command against an expected value
     *
     * @param expectedExitCode
     * @throws IOException
     */
    void checkExitCode(int expectedExitCode) throws IOException;

    /**
     * Method to get a copy of the System environment variable Map
     *
     * @return Map<String, String>
     */
    Map<String, String> getEnvironmentVariableMap();
}
