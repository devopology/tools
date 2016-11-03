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

import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Checksum;

public interface FileUtils {

    /**
     * File types
     */
    int NOT_FOUND = -1;
    int DIRECTORY = 0;
    int FILE = 1;

    String getPath(String directory, String... names) throws IOException;

    String getFile(String... names) throws IOException;

    String getTempDirectoryPath() throws IOException;

    String getTempDirectory() throws IOException;

    String getUserDirectoryPath() throws IOException;

    String getUserDirectory() throws IOException;

    FileInputStream openInputStream(String path) throws IOException;

    FileOutputStream openOutputStream(String path) throws IOException;

    FileOutputStream openOutputStream(String path, boolean append) throws IOException;

    String byteCountToDisplaySize(BigInteger size);

    String byteCountToDisplaySize(long size);

    void touch(String path) throws IOException;

    String[] convertFileCollectionToFileArray(Collection<String> paths) throws IOException;

    Collection<String> listFiles(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException;

    Collection<String> listFilesAndDirs(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException;

    Iterator<String> iterateFiles(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException;

    Iterator<String> iterateFilesAndDirs(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException;

    Collection<String> listFiles(String directory, String[] extensions, boolean recursive) throws IOException;

    Iterator<String> iterateFiles(String directory, String[] extensions, boolean recursive) throws IOException;

    boolean contentEquals(String path1, String path2) throws IOException;

    boolean contentEqualsIgnoreEOL(String path1, String path2, String charsetName) throws IOException;

    URL[] toURLs(String[] paths) throws IOException;

    void copyFileToDirectory(String srcFile, String destDir) throws IOException;

    void copyFileToDirectory(String srcFile, String destDir, boolean preserveFileDate) throws IOException;

    void copyFile(String srcFile, String destFile) throws IOException;

    void copyFile(String srcFile, String destFile, boolean preserveFileDate) throws IOException;

    long copyFile(String input, OutputStream output) throws IOException;

    void copyDirectoryToDirectory(String srcDir, String destDir) throws IOException;

    void copyDirectory(String srcDir, String destDir) throws IOException;

    void copyDirectory(String srcDir, String destDir, boolean preserveFileDate) throws IOException;

    void copyDirectory(String srcDir, String destDir, FileFilter filter) throws IOException;

    void copyDirectory(String srcDir, String destDir, FileFilter filter, boolean preserveFileDate) throws IOException;

    void copyURLToFile(URL source, String destination) throws IOException;

    void copyURLToFile(URL source, String destination, int connectionTimeout, int readTimeout) throws IOException;

    void copyInputStreamToFile(InputStream source, String destination) throws IOException;

    void copyToFile(InputStream source, String destination) throws IOException;

    void deleteDirectory(String directory) throws IOException;

    boolean deleteQuietly(String path) throws IOException;

    boolean directoryContains(String directory, String child) throws IOException;

    void cleanDirectory(String directory) throws IOException;

    boolean waitFor(String path, int seconds) throws IOException;

    String readFileToString(String path) throws IOException;

    String readFileToString(String path, Charset encoding) throws IOException;

    String readFileToString(String path, String encoding) throws IOException;

    byte[] readFileToByteArray(String path) throws IOException;

    List<String> readLines(String path, Charset encoding) throws IOException;

    List<String> readLines(String path, String encoding) throws IOException;

    LineIterator lineIterator(String path, String encoding) throws IOException;

    LineIterator lineIterator(String path) throws IOException;

    void writeStringToFile(String path, String data, Charset encoding) throws IOException;

    void writeStringToFile(String path, String data, String encoding) throws IOException;

    void writeStringToFile(String path, String data, Charset encoding, boolean append) throws IOException;

    void writeStringToFile(String path, String data, String encoding, boolean append) throws IOException;

    void writeStringToFile(String path, String data) throws IOException;

    void writeStringToFile(String path, String data, boolean append) throws IOException;

    void write(String path, CharSequence data) throws IOException;

    void write(String path, CharSequence data, boolean append) throws IOException;

    void write(String path, CharSequence data, Charset encoding) throws IOException;

    void write(String path, CharSequence data, String encoding) throws IOException;

    void write(String path, CharSequence data, Charset encoding, boolean append) throws IOException;

    void write(String path, CharSequence data, String encoding, boolean append) throws IOException;

    void writeByteArrayToFile(String path, byte[] data) throws IOException;

    void writeByteArrayToFile(String path, byte[] data, boolean append) throws IOException;

    void writeByteArrayToFile(String path, byte[] data, int off, int len) throws IOException;

    void writeByteArrayToFile(String path, byte[] data, int off, int len, boolean append) throws IOException;

    void writeLines(String path, String encoding, Collection<?> lines) throws IOException;

    void writeLines(String path, String encoding, Collection<?> lines, boolean append) throws IOException;

    void writeLines(String path, Collection<?> lines) throws IOException;

    void writeLines(String path, Collection<?> lines, boolean append) throws IOException;

    void writeLines(String path, String encoding, Collection<?> lines, String lineEnding) throws IOException;

    void writeLines(String path, String encoding, Collection<?> lines, String lineEnding, boolean append) throws IOException;

    void writeLines(String path, Collection<?> lines, String lineEnding) throws IOException;

    void writeLines(String path, Collection<?> lines, String lineEnding, boolean append) throws IOException;

    void forceDelete(String path) throws IOException;

    void forceDeleteOnExit(String path) throws IOException;

    void forceMkdir(String directory) throws IOException;

    void forceMkdirParent(String path) throws IOException;

    long sizeOf(String path) throws IOException;

    BigInteger sizeOfAsBigInteger(String path) throws IOException;

    long sizeOfDirectory(String directory) throws IOException;

    BigInteger sizeOfDirectoryAsBigInteger(String directory) throws IOException;

    boolean isFileNewer(String path, String reference) throws IOException;

    boolean isFileNewer(String path, Date date) throws IOException;

    boolean isFileNewer(String path, long timeMillis) throws IOException;

    boolean isFileOlder(String path, String reference) throws IOException;

    boolean isFileOlder(String path, Date date) throws IOException;

    boolean isFileOlder(String path, long timeMillis) throws IOException;

    long checksumCRC32(String path) throws IOException;

    Checksum checksum(String path, Checksum checksum) throws IOException;

    void moveDirectory(String srcDir, String destDir) throws IOException;

    void moveDirectoryToDirectory(String src, String destDir, boolean createDestDir) throws IOException;

    void moveFile(String srcFile, String destFile) throws IOException;

    void moveFileToDirectory(String srcFile, String destDir, boolean createDestDir) throws IOException;

    void moveToDirectory(String src, String destDir, boolean createDestDir) throws IOException;

    boolean isSymlink(String path) throws IOException;

    int type(String path) throws IOException;

    String typeString(String path) throws IOException;

    boolean exists(String path) throws IOException;

    boolean isDirectory(String path) throws IOException;

    boolean isFile(String path) throws IOException;

    String md5Sum(String path) throws IOException;

    String sha1Sum(String path) throws IOException;

    String sha256Sum(String path) throws IOException;
}