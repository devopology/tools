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

public interface ZipUtils {

    /**
     * Method to zip a directory
     *
     * @param sourcePath
     * @param zipFilename
     */
    void zip(String sourcePath, String zipFilename) throws IOException;

    /**
     * Method to unzip a file into a directory
     *
     * @param zipFilename
     * @param destinationPath
     */
    void unzip(String zipFilename, String destinationPath) throws IOException;

    /**
     * Method to unzip a file into a directory
     *
     * @param zipFilename
     * @param destinationPath
     * @param overwrite
     */
    void unzip(String zipFilename, String destinationPath, boolean overwrite) throws IOException;
}
