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

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;

/**
 * Class to implement ZipUtils
 */
public class ZipUtils {

    protected Toolset toolset = null;
    protected CurrentDirectory currentDirectory = null;

    ZipUtils(Toolset toolset) {
        this.toolset = toolset;
        this.currentDirectory = toolset.getCurrentDirectory();

        org.devopology.tools.impl.ZipUtils.toolset = toolset;
    }

    /**
     * Method to zip a directory
     *
     * @param sourcePath
     * @param zipFilename
     */
    public void zip(String sourcePath, String zipFilename) throws IOException {
        File sourceFile = currentDirectory.absoluteFile(sourcePath);
        File zipFile = currentDirectory.absoluteFile(zipFilename);
        if (!zipFile.getParentFile().exists()) {
            if (!zipFile.getParentFile().mkdirs()) {
                throw new IOException("zip() Exception : Can't create output directory path");
            }
        }

        org.devopology.tools.impl.ZipUtils.zipFolder(sourceFile.getCanonicalFile(), zipFile.getCanonicalFile());
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
        zipFilename = currentDirectory.absolutePath(zipFilename);
        File destinationFile = currentDirectory.absoluteFile(destinationPath);
        if (overwrite) {
            if (destinationFile.exists()) {
                toolset.getFileUtils().deleteDirectory(destinationFile);
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
