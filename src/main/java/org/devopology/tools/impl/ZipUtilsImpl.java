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

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.devopology.tools.Toolset;
import org.devopology.tools.ZipUtils;

import java.io.File;
import java.io.IOException;

/**
 * Class to implement ZipUtilsImpl
 */
public class ZipUtilsImpl implements ZipUtils {

    protected Toolset toolset = null;

    public ZipUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    /**
     * Method to zip a directory
     *
     * @param sourcePath
     * @param zipFilename
     */
    public void zip(String sourcePath, String zipFilename) throws IOException {
        File sourceFile = toolset.absoluteFile(sourcePath);
        File zipFile = toolset.absoluteFile(zipFilename);
        if (!zipFile.getParentFile().exists()) {
            if (!zipFile.getParentFile().mkdirs()) {
                throw new IOException("zip() Exception : Can't create output directory path");
            }
        }

        ZipUtils2 zipUtils2 = new ZipUtils2(toolset);
        zipUtils2.zipFolder(sourceFile.getAbsoluteFile(), zipFile.getAbsoluteFile());
    }

    /**
     * Method to unzip a file into a directory
     *
     * @param zipFilename
     * @param destinationPath
     */
    public void unzip(String zipFilename, String destinationPath) throws IOException {
        unzip(zipFilename, destinationPath, false);
    }

    /**
     * Method to unzip a file into a directory
     *
     * @param zipFilename
     * @param destinationPath
     * @param overwrite
     */
    public void unzip(String zipFilename, String destinationPath, boolean overwrite) throws IOException {
        zipFilename = toolset.absolutePath(zipFilename);
        File destinationFile = toolset.absoluteFile(destinationPath);
        if (overwrite) {
            if (destinationFile.exists()) {
                toolset.getFileUtils().deleteDirectory(destinationPath);
            }
        }

        try {
            ZipFile zipFile = new ZipFile(zipFilename);
            zipFile.extractAll(destinationFile.getCanonicalPath());
        }
        catch (ZipException ze) {
            throw new IOException("unzip() Exception ", ze);
        }
    }
}
