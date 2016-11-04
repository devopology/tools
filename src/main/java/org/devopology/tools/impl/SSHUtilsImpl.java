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

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.StreamGobbler;
import org.devopology.tools.ExecResult;
import org.devopology.tools.Toolset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class to implement SSHUtilss
 */
public class SSHUtilsImpl implements org.devopology.tools.SSHUtils {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Toolset toolset = null;

    public SSHUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

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
    @Override
    public ExecResult sshExec(String hostname, int port, String username, String password, String[] commands) throws IOException {
        Connection connection = null;
        ch.ethz.ssh2.Session session = null;

        try
        {
            connection = new Connection(hostname, port);
            connection.connect();

            boolean isAuthenticated = connection.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new IOException("sshExec() Exception : authentication failed.");
            }

            StringBuilder commandStringBuilder = new StringBuilder(commands[0]);

            for (int i=1; i<commands.length; i++) {
                commandStringBuilder.append(" ").append(commands[i]);
            }

            session = connection.openSession();
            session.execCommand(commandStringBuilder.toString());

            InputStream stdout = new StreamGobbler(session.getStdout());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));

            String line = null;
            StringBuilder outputStringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null)
            {
                if (outputStringBuilder.length() > 0) {
                    outputStringBuilder.append(LINE_SEPARATOR);
                }

                outputStringBuilder.append(line);
            }

            ExecResultImpl execResultImpl = new ExecResultImpl();
            execResultImpl.setOutput(outputStringBuilder.toString());
            execResultImpl.setExitCode(0);

            if (null != session.getExitStatus()) {
                execResultImpl.setExitCode(session.getExitStatus());
            }

            return execResultImpl;
        }
        finally {
            if (null != session) {
                try {
                    session.close();
                }
                catch (Throwable t) {
                    // DO NOTHING
                }
            }

            if (null != connection) {
                try {
                    connection.close();
                }
                catch (Throwable t) {
                    // DO NOTHING
                }
            }
        }
    }
}
