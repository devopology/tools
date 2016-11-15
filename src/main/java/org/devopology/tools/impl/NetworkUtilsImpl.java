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

import org.devopology.tools.HTTPDownloader;
import org.devopology.tools.NetworkUtils;
import org.devopology.tools.Toolset;
import org.devopology.tools.impl.networkutils.HTTPDownloaderImpl;
import org.devopology.tools.impl.networkutils.SFTPDownloader;
import org.devopology.tools.impl.networkutils.SFTPUploader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

/**
 * Class to implement NetworkUtils
 */
public class NetworkUtilsImpl implements NetworkUtils {

    private Toolset toolset = null;
    private HTTPDownloaderImpl httpDownloader = null;
    private SFTPDownloader sftpDownloader = null;
    private SFTPUploader sftpUploader = null;

    public NetworkUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
        this.httpDownloader = new HTTPDownloaderImpl(toolset);
        this.sftpDownloader = new SFTPDownloader(toolset);
        this.sftpUploader = new SFTPUploader();
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
     * Method to get the HTTP downloader
     *
     * @return HTTPDownloader
     */
    public HTTPDownloader getHTTPDownloader() {
        return httpDownloader;
    }

    /**
     * Method to downloasd a file via HTTP/HTTPS
     *
     * @param url
     * @param filename
     * @param overwrite
     */
    public void downloadFileViaHTTP(String url, String filename, boolean overwrite) throws IOException {
        filename = toolset.absolutePath(filename);
        if ((false == overwrite) && (toolset.getFileUtils().exists(filename))) {
            throw new IOException("downloadFileViaHTTP() Exception : destination file [" + filename + "] already exists");
        }

        httpDownloader.downloadFileViaHTTP(new URL(url), toolset.absolutePath(filename));
    }

    /**
     * Method to download a file via SFTP
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param filename
     * @param destinationFilename
     * @throws IOException
     */
    public void downloadFileViaSFTP(String hostname, int port, String username, String password, String filename, String destinationFilename) throws IOException {
        downloadFileViaSFTP(hostname, port, username, password, filename, destinationFilename, true);
    }

    /**
     * Method to download a file via SFTP
     * s
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param filename
     * @param destinationFilename
     * @param overwrite
     * @throws IOException
     */
    public void downloadFileViaSFTP(String hostname, int port, String username, String password, String filename, String destinationFilename, boolean overwrite) throws IOException {
        destinationFilename = toolset.absolutePath(destinationFilename);
        if ((false == overwrite) && (toolset.getFileUtils().exists(destinationFilename))) {
            throw new IOException("downloadFileViaHTTP() Exception : destination file [" + destinationFilename + "] already exists");
        }

        sftpDownloader.downloadFileViaSFTP(hostname, port, username, password, filename, destinationFilename);
    }

    /**
     * Method to upload a file via SFTP
     *
     * @param hostname
     * @param port
     * @param username
     * @param password
     * @param filename
     * @param destinationFilename
     * @throws IOException
     */
    public void uploadFileViaSFTP(String hostname, int port, String username, String password, String filename, String destinationFilename) throws IOException {
        filename = toolset.absolutePath(filename);
        sftpUploader.uploadFileViaSFTP(hostname, port, username, password, filename, destinationFilename);
    }
}
