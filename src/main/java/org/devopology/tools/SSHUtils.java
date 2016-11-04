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

public interface SSHUtils {

    /**
     * Method to execute a remote command via ssh
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param command
     * @return ExecResults
     * @throws IOException
     */
    ExecResult exec(String hostname, int port, String username, String password, String command) throws IOException;

    /**
     * Method to execute a remote command via ssh
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param commands
     * @return ExecResults
     * @throws IOException
     */
    ExecResult exec(String hostname, int port, String username, String password, String[] commands) throws IOException;
}
