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

import org.devopology.tools.ExecResult;
import org.devopology.tools.SystemUtils;
import org.devopology.tools.Toolset;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class to implement NetworkUtils
 */
public class NetworkUtilsImpl implements org.devopology.tools.NetworkUtils {

    private Toolset toolset = null;

    public NetworkUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    /**
     * Method to determine if a TCP/IP connection to a socket can connect
     *
     * @param hostname
     * @param port
     * @return boolean
     * @throws IOException
     */
    @Override
    public boolean canConnect(String hostname, int port) throws IOException {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(hostname, port), 1000);
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    /**
     * Method to downloasd a file via HTTP/HTTPS, overwriting any existing file
     *
     * @param url
     * @param filename
     */
    public void downloadFileViaHTTP(String url, String filename) throws IOException {
        downloadFileViaHTTP(url, filename, true);
    }

    /**
     * Method to downloasd a file via HTTP/HTTPS
     *
     * @param url
     * @param filename
     * @param overwrite
     */
    public void downloadFileViaHTTP(String url, String filename, boolean overwrite) throws IOException {
        if ((false == overwrite) && (toolset.getFileUtils().exists(filename))) {
            throw new IOException("downloadFileViaHTTP() Exception : destination file [" + filename + "] already exists");
        }

        String curl = toolset.getSystemUtils().resolve("curl", SystemUtils.DEFAULT_UNIX_SEARCH_PATHS);
        if (null == curl) {
            throw new IOException("downloadFileViaHTTP() Exception : curl is required for file downloads, but was not found");
        }

        ExecResult execResult = toolset.getExecUtils().execute(curl, toolset.arguments("-s", "-o", toolset.absolutePath(filename), url));
        if (0 != execResult.getExitCode()) {
            if (toolset.getFileUtils().exists(filename)) {
                toolset.getFileUtils().deleteQuietly(filename);
            }

            throw new IOException("downloadFileViaHTTP() Exception : error [" + execResult.getExitCode() + "] downloading");
        }
    }
}
