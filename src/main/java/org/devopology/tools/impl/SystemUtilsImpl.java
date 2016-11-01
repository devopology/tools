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

import org.apache.commons.lang3.JavaVersion;
import org.devopology.tools.ExecResult;
import org.devopology.tools.SystemUtils;
import org.devopology.tools.Toolset;

import java.io.File;
import java.io.IOException;

/**
 * Class to implement SystemUtils
 */
public class SystemUtilsImpl implements SystemUtils {

    private Toolset toolset = null;

    public SystemUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    private File absoluteFile(String path) throws IOException {
        return absoluteFile(new File(path));
    }

    private File absoluteFile(File path) throws IOException {
        if (path.isAbsolute()) {
            return path.getAbsoluteFile();
        }
        else {
            return new File(toolset.getCurrentDirectory().getPath() + File.separator + path.getPath());
        }
    }

    private String absolutePath(String path) throws IOException {
        return absoluteFile(new File(path)).getAbsolutePath();
    }

    private String absolutePath(File path) throws IOException {
        return absoluteFile(path).getAbsolutePath();
    }

    @Override
    public String getJavaHome() throws IOException {
        return absoluteFile(org.apache.commons.lang3.SystemUtils.getJavaHome()).getAbsolutePath();
    }

    @Override
    public String getJavaIoTmpDir() throws IOException {
        return absoluteFile(org.apache.commons.lang3.SystemUtils.getJavaIoTmpDir()).getAbsolutePath();
    }

    @Override
    public String getUserDir() throws IOException {
        return absoluteFile(org.apache.commons.lang3.SystemUtils.getUserDir()).getAbsolutePath();
    }

    @Override
    public String getUserHome() throws IOException {
        return absoluteFile(org.apache.commons.lang3.SystemUtils.getUserHome()).getAbsolutePath();
    }

    @Override
    public boolean isJavaAwtHeadless() {
        return org.apache.commons.lang3.SystemUtils.isJavaAwtHeadless();
    }

    @Override
    public boolean isJavaVersionAtLeast(JavaVersion requiredVersion) {
        return org.apache.commons.lang3.SystemUtils.isJavaVersionAtLeast(requiredVersion);
    }

    @Override
    public String whoami() throws IOException {
        String whoamiPath = resolve("whoami", "/bin", "/sbin", "/usr/bin", "/usr/sbin", "/usr/local/bin", "/usr/local/sbin");

        if (null == whoamiPath) {
            throw new IOException("whoami() Exception : can't find whoami");
        }

        ExecResult execResult = toolset.getExecUtils().execute(whoamiPath, null, 0);
        return execResult.getContent().trim();
    }

    @Override
    public boolean isUser(String username) throws IOException {
        return (username.equals(whoami()));
    }

    @Override
    public String resolve(String executable, String... paths) throws IOException {
        for (int i=0; i<paths.length; i++)
        {
            File file = absoluteFile(paths[i] + File.separator + executable);

            if (file.exists() && file.isFile()) {
                return file.getAbsolutePath();
            }

        }
        return null;
    }
}
