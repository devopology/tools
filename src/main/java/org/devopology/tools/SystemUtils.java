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

import org.apache.commons.lang3.JavaVersion;

import java.io.IOException;

public interface SystemUtils {

    public final static String [] DEFAULT_UNIX_SEARCH_PATHS = UnixUtils.DEFAULT_UNIX_SEARCH_PATHS;

    String getJavaHome() throws IOException;

    String getJavaIoTmpDir() throws IOException;

    String getUserDir() throws IOException;

    String getUserHome() throws IOException;

    boolean isJavaAwtHeadless();

    boolean isJavaVersionAtLeast(JavaVersion requiredVersion);

    String whoami() throws IOException;

    boolean isUser(String username) throws IOException;

    String resolve(String executable, String ... paths) throws IOException;
}
