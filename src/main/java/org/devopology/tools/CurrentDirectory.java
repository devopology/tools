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

    private File file = null;

    /**
     * Constructor using the current working directory
     */
    public CurrentDirectory() {
        this.file = new File(".").getAbsoluteFile();
    }

    /**
     * Constructor using a directory.  If the File is
     * relative, it will be relative to the current
     * working directory
     *
     * @param file
     */
    public CurrentDirectory(File file) throws IOException {
        if (file.isAbsolute()) {
            this.file = file;
        }
        else {
            this.file = file.getAbsoluteFile();
        }

        if (!file.exists() || !file.isDirectory()) {
            throw new IOException(file.getAbsolutePath() + " doesn't exist or isn't a directory");
        }
    }

    /**
     * Constructor using a directory.  If the File is
     * relative, it will be relative to the current
     * working directory
     *
     * @param path
     */
    public CurrentDirectory(String path) throws IOException {
        this(new File(path));
    }

    /**
     * Method to convert the object to a String, which will be the absolute path
     *
     * @return
     */
    public String toString() {
        try {
            return file.getCanonicalPath();
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    /**
     * Method to get the current directory
     *
     * @return
     * @throws IOException
     */
    public File getFile() throws IOException {
        return file.getCanonicalFile();
    }

    /**
     * Method to get an absolute File based on the current working directory
     *
     * @param file
     * @return File
     * @throws IOException
     */
    public File absoluteFile(File file) throws IOException {
        if (file.isAbsolute()) {
            return file;
        }
        else {
            return new File(this.file.getCanonicalPath() + File.separator + file.getPath()).getCanonicalFile();
        }
    }

    /**
     * Method to get an absolute File based on the current working directory
     *
     * @param path
     * @return
     * @throws IOException
     */
    public File absoluteFile(String path) throws IOException {
        return absoluteFile(new File(path));
    }

    /**
     * Method to get an absolute path based on the current working directory
     *
     * @param file
     * @return String
     * @throws IOException
     */
    public String absolutePath(File file) throws IOException {
        return absoluteFile(file).getAbsolutePath();
    }

    /**
     * Method to get an absolute path based on the current working directory
     *
     * @param path
     * @return String
     * @throws IOException
     */
    public String absolutePath(String path) throws IOException {
        return absoluteFile(path).getCanonicalPath();
    }

    /**
     * Method to change the current directory
     *
     * @param path
     * @return
     * @throws IOException
     */
    public File changeDirectory(String path) throws IOException {
        return changeDirectory(absoluteFile(path));
    }

    /**
     * Method to change the current directory
     *
     * @param file
     * @return File
     * @throws IOException
     */
    public File changeDirectory(File file) throws IOException {
        file = absoluteFile(file);
        if (file.isAbsolute()) {
            file = file.getCanonicalFile();
        }
        else {
            file = new File(this.file.getCanonicalFile() + File.separator + file.getPath());
        }

        if (file.exists() == false) {
            throw new IOException("changeDirectory() Exception : " + this.file.getCanonicalPath() + " doesn't exist");
        }

        if (file.isDirectory() == false) {
            throw new IOException("changeDirectory() Exception : " + this.file.getCanonicalPath() + " is not a directory");
        }

        this.file = file;

        return this.file.getCanonicalFile();
    }
}
