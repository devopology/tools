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

import java.io.File;
import java.io.IOException;

/**
 * Class to track the current directory
 */
public class CurrentDirectory {

    private File resetFile = null;
    private File file = null;

    /**
     * Constructor using the current working directory
     */
    public CurrentDirectory() {
        this.file = new File(".").getAbsoluteFile();
        this.resetFile = file;
    }

    /**
     * Constructor using a directory.  If the File is
     * relative, it will be relative to the current
     * working directory
     *
     * @param path
     */
    public CurrentDirectory(String path) throws IOException {
        File file = new File(path);

        if (!file.exists()) {
            throw new IOException(path + " doesn't exist");
        }

        if (!file.isDirectory()) {
            throw new IOException(path + " isn't a directory");
        }

        if (file.isAbsolute()) {
            this.file = file;
            this.resetFile = this.file;
        }
        else {
            this.file = file.getAbsoluteFile();
            this.resetFile = this.file;
        }
    }

    private File absoluteFile(String path) throws IOException {
        return absoluteFile(new File(path));
    }

    public File canonicalFile(String path) throws IOException {
        return absoluteFile(new File(path)).getCanonicalFile();
    }

    public File absoluteFile(File path) throws IOException {
        if (path.isAbsolute()) {
            return path.getAbsoluteFile();
        }
        else {
            return new File(getPath() + File.separator + path.getPath());
        }
    }

    public String absolutePath(File path) throws IOException {
        return absoluteFile(path).getAbsolutePath();
    }

    public String canonicalPath(File path) throws IOException {
        return absoluteFile(path).getCanonicalPath();
    }

    /**
     * Method to reset the current directory to the original directory when the class was created
     */
    public void reset() {
        this.file = this.resetFile;
    }

    /**
     * Method to the absolute path of the current directory
     *
     * @return String
     * @throws IOException
     */
    public String getPath() throws IOException {
        return file.getCanonicalPath();
    }

    /**
     * Method to get an absolute path based on the current working directory
     *
     * @param path
     * @return String
     * @throws IOException
     */
    public String absolutePath(String path) throws IOException {
        File file = new File(path);

        if (file.isAbsolute()) {
            return file.getAbsolutePath();
        }
        else {
            return new File(this.file.getAbsoluteFile() + File.separator + file.getPath()).getAbsolutePath();
        }

    }

    /**
     * Method to change the current directory
     *
     * @param path
     * @return String
     * @throws IOException
     */
    public String changeDirectory(String path) throws IOException {
        file = canonicalFile(path);
        return file.getCanonicalPath();
    }
}
