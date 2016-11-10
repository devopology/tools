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

public interface NetworkUtils {

    /**
     * Method to test a basic TCP/IP socket connection
     *
     * @param hostname
     * @param port
     * @return boolean
     * @throws IOException
     */
    boolean canConnect(String hostname, int port) throws IOException;

    /**
     * Method to download a file via HTTP/HTTPS
     * @param url
     * @param filename
     * @throws IOException
     */
    void downloadFileViaHTTP(String url, String filename) throws IOException;

    /**
     * Method to upload a file via SFTP
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param sourceFilename
     * @param destinationFilename
     * @throws IOException
     */
    void uploadFileViaSFTP(String hostname, int port, String username, String password, String sourceFilename, String destinationFilename) throws IOException;

    /**
     * Method to download a file via SFTP
     * s
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param filename
     * @param destinationFilename
     * @throws IOException
     */
    void downloadFileViaSFTP(String hostname, int port, String username, String password, String filename, String destinationFilename) throws IOException;

    /**
     * Method to download a file via SFTP
     * s
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param filename
     * @param destinationFilename
     * @param overwrite
     * @throws IOException
     */
    void downloadFileViaSFTP(String hostname, int port, String username, String password, String filename, String destinationFilename, boolean overwrite) throws IOException;
}
