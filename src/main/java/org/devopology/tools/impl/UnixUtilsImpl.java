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
import org.devopology.tools.Toolset;
import org.devopology.tools.UnixUtils;

import java.io.IOException;

/**
 * Class to implement UnixUtils
 */
public class UnixUtilsImpl implements UnixUtils {

    private Toolset toolset = null;

    public UnixUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    /**
     * Method to get whoami
     *
     * @return String
     * @throws IOException
     */
    public String whoami() throws IOException {
        String whoami = toolset.getSystemUtils().resolve("whoami", DEFAULT_UNIX_SEARCH_PATHS);
        if (null == whoami) {
            throw new IOException("whoami() Exception : can't find whoami");
        }

        ExecResult execResult = toolset.getExecUtils().execute(whoami, null, 0);
        return execResult.getContent().trim();
    }

    /**
     * Method to get the full Linux version String
     *
     * @return String
     * @throws IOException
     */
    public String getUnixVersion() throws IOException {
        return getLinuxVersion();
    }

    /**
     * Method to get the full Linux version String
     *
     * @return String
     * @throws IOException
     */
    public String getLinuxVersion() throws IOException {
        return toolset.getFileUtils().readFileToString("/proc/version").trim();
    }

    /**
     * Method to chmod a file
     *
     * @param path
     * @param permissions
     * @throws IOException
     */
    public void chmod(String path, String permissions) throws IOException {
        String chmod = toolset.getSystemUtils().resolve("chmod", DEFAULT_UNIX_SEARCH_PATHS);
        if (null == chmod) {
            throw new IOException("chmod() Exception : can't find chmod");
        }

        toolset.getExecUtils().execute(chmod, toolset.arguments("u+x", path), 0);
    }

    /**
     * Method to use systemctl
     *
     * @param command
     * @param service
     * @throws IOException
     */
    public void systemctl(String command, String service) throws IOException {
        String systemctl = toolset.getSystemUtils().resolve("systemctl", DEFAULT_UNIX_SEARCH_PATHS);
        if (null == systemctl) {
            throw new IOException("systemctl() Exception : can't find systemctl");
        }

        toolset.getExecUtils().execute(systemctl, toolset.arguments(command, service), 0);
    }
}
