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
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class to implement NetworkUtils
 */
public class NetworkUtils {

    private Toolset toolset = null;

    NetworkUtils(Toolset toolset) {
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
    public boolean canConnect(String hostname, int port) throws IOException {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(hostname, port), 1000);
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }
}
