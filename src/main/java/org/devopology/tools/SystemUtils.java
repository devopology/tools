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

import java.io.File;
import java.io.IOException;

/**
 * Class to implement SystemUtils
 */
public class SystemUtils {

    private Toolset toolset = null;

    SystemUtils(Toolset toolset) {
        this.toolset = toolset;
    }

    public File getJavaHome() throws IOException {
        return toolset.getCurrentDirectory().absoluteFile(org.apache.commons.lang3.SystemUtils.getJavaHome());
    }

    public File getJavaIoTmpDir() throws IOException {
        return toolset.getCurrentDirectory().absoluteFile(org.apache.commons.lang3.SystemUtils.getJavaIoTmpDir());
    }

    public File getUserDir() throws IOException {
        return toolset.getCurrentDirectory().absoluteFile(org.apache.commons.lang3.SystemUtils.getUserDir());
    }

    public  File getUserHome() throws IOException {
        return toolset.getCurrentDirectory().absoluteFile(org.apache.commons.lang3.SystemUtils.getUserHome());
    }

    public boolean isJavaAwtHeadless() {
        return org.apache.commons.lang3.SystemUtils.isJavaAwtHeadless();
    }

    public boolean isJavaVersionAtLeast(JavaVersion requiredVersion) {
        return org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(requiredVersion);
    }
}
