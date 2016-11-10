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
}
