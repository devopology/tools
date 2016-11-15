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

package org.devopology.tools.impl.networkutils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.devopology.tools.SFTPUploader;
import org.devopology.tools.Toolset;

import java.io.IOException;

public class SFTPUploaderImpl implements SFTPUploader {

    private Toolset toolset = null;

    public SFTPUploaderImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    public void uploadFileViaSFTP(String hostname, int port, String username, String password, String sourceFilename, String destinationFilename) throws IOException {
        JSch ssh = null;
        Session session = null;
        Channel channel = null;

        try {
            ssh = new JSch();

            session = ssh.getSession(username, hostname, port);
            session.setPassword(password);
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.put(toolset.absolutePath(sourceFilename), destinationFilename);
        }
        catch (Throwable t) {
            throw new IOException("downloadFileViaSFTP() Exception", t);
        }
        finally {
            if (channel != null) {
                try {
                    channel.disconnect();
                }
                catch (Throwable t) {
                    // DO NOTHING
                }
            }

            if (session != null) {
                try {
                    session.disconnect();
                }
                catch (Throwable t) {
                    // DO NOTHING
                }
            }
        }
    }
}
