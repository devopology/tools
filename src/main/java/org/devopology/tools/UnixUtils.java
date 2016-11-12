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

public interface UnixUtils {

    public final static String [] DEFAULT_UNIX_SEARCH_PATHS = new String [] { "/sbin", "/bin", "/usr/sbin", "/usr/bin", "/usr/local/sbin", "/usr/local/bin" };

    /**
     * Method to get the full Linux version String
     *
     * @return String
     * @throws IOException
     */
    String getUnixVersion() throws IOException;

    /**
     * Method to get the full Linux version String
     *
     * @return String
     * @throws IOException
     */
    String getLinuxVersion() throws IOException;
}
