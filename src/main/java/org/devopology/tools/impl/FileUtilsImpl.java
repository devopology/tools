/*
 * Copyright 2016 Doug Hoard
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this path except in compliance with the License.
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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.devopology.tools.FileUtils;
import org.devopology.tools.Toolset;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.Checksum;

/**
 * Method to implement FileUtils
 */
public class FileUtilsImpl implements FileUtils {

    private Toolset toolset = null;

    public FileUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    @Override
    public String getPath(String directory, String... names) throws IOException {
        return toolset.absolutePath(org.apache.commons.io.FileUtils.getFile(toolset.absoluteFile(directory), names));
    }

    @Override
    public String getFile(String... names) throws IOException {
        return toolset.absolutePath(org.apache.commons.io.FileUtils.getFile(names));
    }

    @Override
    public String getTempDirectoryPath() throws IOException {
        return getTempDirectory();
    }

    @Override
    public String getTempDirectory() throws IOException {
        return toolset.absolutePath(org.apache.commons.io.FileUtils.getTempDirectory());
    }

    @Override
    public String getUserDirectoryPath() throws IOException {
        return getUserDirectory();
    }

    @Override
    public String getUserDirectory() throws IOException {
        return toolset.absolutePath(org.apache.commons.io.FileUtils.getUserDirectory());
    }

    @Override
    public FileInputStream openInputStream(String path) throws IOException {
        return org.apache.commons.io.FileUtils.openInputStream(toolset.absoluteFile(path));
    }

    @Override
    public FileOutputStream openOutputStream(String path) throws IOException {
        return org.apache.commons.io.FileUtils.openOutputStream(toolset.absoluteFile(path));
    }

    @Override
    public FileOutputStream openOutputStream(String path, boolean append) throws IOException {
        return org.apache.commons.io.FileUtils.openOutputStream(toolset.absoluteFile(path), append);
    }

    @Override
    public String byteCountToDisplaySize(BigInteger size) {
        return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
    }

    @Override
    public String byteCountToDisplaySize(long size) {
        return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
    }

    @Override
    public void touch(String path) throws IOException {
        org.apache.commons.io.FileUtils.touch(toolset.absoluteFile(path));
    }

    @Override
    public String [] convertFileCollectionToFileArray(Collection<String> paths) throws IOException {
        String [] result = paths.toArray(new String[0]);
        for (int i=0; i<result.length; i++) {
            result[i] = toolset.absolutePath(result[i]);
        }
        return result;
    }

    @Override
    public Collection<String> listFiles(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException {
        Collection<File> temp = org.apache.commons.io.FileUtils.listFiles(toolset.absoluteFile(directory), pathFilter, dirFilter);
        Collection<String> result = new ArrayList<String>();
        for (File path : temp) {
            result.add(toolset.absolutePath(path));
        }
        return result;
    }

    @Override
    public Collection<String> listFilesAndDirs(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException {
        Collection<File> temp = org.apache.commons.io.FileUtils.listFilesAndDirs(toolset.absoluteFile(directory), pathFilter, dirFilter);
        Collection<String> result = new ArrayList<String>();
        for (File path : temp) {
            result.add(toolset.absolutePath(path));
        }
        return result;
    }

    @Override
    public Iterator<String> iterateFiles(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException {
        return listFiles(directory, pathFilter, dirFilter).iterator();
    }

    @Override
    public Iterator<String> iterateFilesAndDirs(String directory, IOFileFilter pathFilter, IOFileFilter dirFilter) throws IOException {
        return listFilesAndDirs(directory, pathFilter, dirFilter).iterator();
    }

    @Override
    public Collection<String> listFiles(String directory, String[] extensions, boolean recursive) throws IOException {
        Collection<File> temp = org.apache.commons.io.FileUtils.listFiles(toolset.absoluteFile(directory), extensions, recursive);
        Collection<String> result = new ArrayList<String>();
        for (File file : temp) {
            result.add(toolset.absolutePath(file));
        }
        return result;
    }

    @Override
    public Iterator<String> iterateFiles(String directory, String[] extensions, boolean recursive) throws IOException {
        return listFiles(directory, extensions, recursive).iterator();
    }

    @Override
    public boolean contentEquals(String path1, String path2) throws IOException {
        return org.apache.commons.io.FileUtils.contentEquals(toolset.absoluteFile(path1), toolset.absoluteFile(path2));
    }

    @Override
    public boolean contentEqualsIgnoreEOL(String path1, String path2, String charsetName) throws IOException {
        return org.apache.commons.io.FileUtils.contentEqualsIgnoreEOL(toolset.absoluteFile(path1), toolset.absoluteFile(path2), charsetName);
    }

    @Override
    public URL [] toURLs(String [] paths) throws IOException {
        File [] files = new File[paths.length];
        for (int i=0; i<paths.length; i++) {
            files[i] = toolset.absoluteFile(paths[i]);
        }
        return org.apache.commons.io.FileUtils.toURLs(files);
    }

    @Override
    public void copyFileToDirectory(String srcFile, String destDir) throws IOException {
        org.apache.commons.io.FileUtils.copyFileToDirectory(toolset.absoluteFile(srcFile), toolset.absoluteFile(destDir));
    }

    @Override
    public void copyFileToDirectory(String srcFile, String destDir, boolean preserveFileDate) throws IOException {
        org.apache.commons.io.FileUtils.copyFileToDirectory(toolset.absoluteFile(srcFile), toolset.absoluteFile(destDir), preserveFileDate);
    }

    @Override
    public void copyFile(String srcFile, String destFile) throws IOException {

        org.apache.commons.io.FileUtils.copyFile(toolset.absoluteFile(srcFile), toolset.absoluteFile(destFile));
    }

    @Override
    public void copyFile(String srcFile, String destFile, boolean preserveFileDate) throws IOException {
        org.apache.commons.io.FileUtils.copyFile(toolset.absoluteFile(srcFile), toolset.absoluteFile(destFile), preserveFileDate);
    }

    @Override
    public long copyFile(String input, OutputStream output) throws IOException {
        return org.apache.commons.io.FileUtils.copyFile(toolset.absoluteFile(input), output);
    }

    @Override
    public void copyDirectoryToDirectory(String srcDir, String destDir) throws IOException {
        org.apache.commons.io.FileUtils.copyDirectoryToDirectory(toolset.absoluteFile(srcDir), toolset.absoluteFile(destDir));
    }

    @Override
    public void copyDirectory(String srcDir, String destDir) throws IOException {
        org.apache.commons.io.FileUtils.copyDirectory(toolset.absoluteFile(srcDir), toolset.absoluteFile(destDir));
    }

    @Override
    public void copyDirectory(String srcDir, String destDir, boolean preserveFileDate) throws IOException {
        org.apache.commons.io.FileUtils.copyDirectory(toolset.absoluteFile(srcDir), toolset.absoluteFile(destDir), preserveFileDate);
    }

    @Override
    public void copyDirectory(String srcDir, String destDir, FileFilter filter) throws IOException {
        org.apache.commons.io.FileUtils.copyDirectory(toolset.absoluteFile(srcDir), toolset.absoluteFile(destDir), filter);
    }

    @Override
    public void copyDirectory(String srcDir, String destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
        org.apache.commons.io.FileUtils.copyDirectory(toolset.absoluteFile(srcDir), toolset.absoluteFile(destDir), filter, preserveFileDate);
    }

    @Override
    public void copyURLToFile(URL source, String destination) throws IOException {
        org.apache.commons.io.FileUtils.copyURLToFile(source, toolset.absoluteFile(destination));
    }

    @Override
    public void copyURLToFile(URL source, String destination, int connectionTimeout, int readTimeout) throws IOException {
        org.apache.commons.io.FileUtils.copyURLToFile(source, toolset.absoluteFile(destination), connectionTimeout, readTimeout);
    }

    @Override
    public void copyInputStreamToFile(InputStream source, String destination) throws IOException {
        org.apache.commons.io.FileUtils.copyInputStreamToFile(source, toolset.absoluteFile(destination));
    }

    @Override
    public void copyToFile(InputStream source, String destination) throws IOException {
        org.apache.commons.io.FileUtils.copyToFile(source, toolset.absoluteFile(destination));
    }

    @Override
    public void deleteDirectory(String directory) throws IOException {
        org.apache.commons.io.FileUtils.deleteDirectory(toolset.absoluteFile(directory));
    }

    @Override
    public boolean deleteQuietly(String path) throws IOException {
        return org.apache.commons.io.FileUtils.deleteQuietly(toolset.absoluteFile(path));
    }

    @Override
    public boolean directoryContains(String directory, String child) throws IOException {
        return org.apache.commons.io.FileUtils.directoryContains(toolset.absoluteFile(directory), toolset.absoluteFile(child));
    }

    @Override
    public void cleanDirectory(String directory) throws IOException {
        org.apache.commons.io.FileUtils.cleanDirectory(toolset.absoluteFile(directory));
    }

    @Override
    public boolean waitFor(String path, int seconds) throws IOException {
        return org.apache.commons.io.FileUtils.waitFor(toolset.absoluteFile(path), seconds);
    }

    @Override
    public String readFileToString(String path) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToString(toolset.absoluteFile(path), StandardCharsets.UTF_8);
    }

    @Override
    public String readFileToString(String path, Charset encoding) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToString(toolset.absoluteFile(path), encoding);
    }

    @Override
    public String readFileToString(String path, String encoding) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToString(toolset.absoluteFile(path), encoding);
    }

    @Override
    public byte [] readFileToByteArray(String path) throws IOException {
        return org.apache.commons.io.FileUtils.readFileToByteArray(toolset.absoluteFile(path));
    }

    @Override
    public List<String> readLines(String path, Charset encoding) throws IOException {
        return org.apache.commons.io.FileUtils.readLines(toolset.absoluteFile(path), encoding);
    }

    @Override
    public List<String> readLines(String path, String encoding) throws IOException {
        return org.apache.commons.io.FileUtils.readLines(toolset.absoluteFile(path), encoding);
    }

    @Override
    public LineIterator lineIterator(String path, String encoding) throws IOException {
        return org.apache.commons.io.FileUtils.lineIterator(toolset.absoluteFile(path), encoding);
    }

    @Override
    public LineIterator lineIterator(String path) throws IOException {
        return org.apache.commons.io.FileUtils.lineIterator(toolset.absoluteFile(path));
    }

    @Override
    public void writeStringToFile(String path, String data, Charset encoding) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(toolset.absoluteFile(path), data, encoding);
    }

    @Override
    public void writeStringToFile(String path, String data, String encoding) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(toolset.absoluteFile(path), data, encoding);
    }

    @Override
    public void writeStringToFile(String path, String data, Charset encoding, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(toolset.absoluteFile(path), data, encoding, append);
    }

    @Override
    public void writeStringToFile(String path, String data, String encoding, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(toolset.absoluteFile(path), data, encoding, append);
    }

    @Override
    public void writeStringToFile(String path, String data) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(toolset.absoluteFile(path), data, StandardCharsets.UTF_8);
    }

    @Override
    public void writeStringToFile(String path, String data, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeStringToFile(toolset.absoluteFile(path), data, StandardCharsets.UTF_8, append);
    }

    @Override
    public void write(String path, CharSequence data) throws IOException {
        org.apache.commons.io.FileUtils.write(toolset.absoluteFile(path), data, StandardCharsets.UTF_8);
    }

    @Override
    public void write(String path, CharSequence data, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.write(toolset.absoluteFile(path), data, StandardCharsets.UTF_8, append);
    }

    @Override
    public void write(String path, CharSequence data, Charset encoding) throws IOException {
        org.apache.commons.io.FileUtils.write(toolset.absoluteFile(path), data, encoding);
    }

    @Override
    public void write(String path, CharSequence data, String encoding) throws IOException {
        org.apache.commons.io.FileUtils.write(toolset.absoluteFile(path), data, encoding);
    }

    @Override
    public void write(String path, CharSequence data, Charset encoding, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.write(toolset.absoluteFile(path), data, encoding, append);
    }

    @Override
    public void write(String path, CharSequence data, String encoding, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.write(toolset.absoluteFile(path), data, encoding, append);
    }

    @Override
    public void writeByteArrayToFile(String path, byte[] data) throws IOException {
        org.apache.commons.io.FileUtils.writeByteArrayToFile(toolset.absoluteFile(path), data);
    }

    @Override
    public void writeByteArrayToFile(String path, byte[] data, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeByteArrayToFile(toolset.absoluteFile(path), data, append);
    }

    @Override
    public void writeByteArrayToFile(String path, byte[] data, int off, int len) throws IOException {
        org.apache.commons.io.FileUtils.writeByteArrayToFile(toolset.absoluteFile(path), data, off, len);
    }

    @Override
    public void writeByteArrayToFile(String path, byte[] data, int off, int len, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeByteArrayToFile(toolset.absoluteFile(path), data, off, len, append);
    }

    @Override
    public void writeLines(String path, String encoding, Collection<?> lines) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), encoding, lines);
    }

    @Override
    public void writeLines(String path, String encoding, Collection<?> lines, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), encoding, lines, append);
    }

    @Override
    public void writeLines(String path, Collection<?> lines) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), lines);
    }

    @Override
    public void writeLines(String path, Collection<?> lines, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), lines, append);
    }

    @Override
    public void writeLines(String path, String encoding, Collection<?> lines, String lineEnding) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), encoding, lines, lineEnding);
    }

    @Override
    public void writeLines(String path, String encoding, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), encoding, lines, lineEnding, append);
    }

    @Override
    public void writeLines(String path, Collection<?> lines, String lineEnding) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), lines, lineEnding);
    }

    @Override
    public void writeLines(String path, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        org.apache.commons.io.FileUtils.writeLines(toolset.absoluteFile(path), lines, lineEnding, append);
    }

    @Override
    public void forceDelete(String path) throws IOException {
        org.apache.commons.io.FileUtils.forceDelete(toolset.absoluteFile(path));
    }

    @Override
    public void forceDeleteOnExit(String path) throws IOException {
        org.apache.commons.io.FileUtils.forceDeleteOnExit(toolset.absoluteFile(path));
    }

    @Override
    public void forceMkdir(String directory) throws IOException {
        org.apache.commons.io.FileUtils.forceMkdir(toolset.absoluteFile(directory));
    }

    @Override
    public void forceMkdirParent(String path) throws IOException {
        org.apache.commons.io.FileUtils.forceMkdirParent(toolset.absoluteFile(path));
    }

    @Override
    public long sizeOf(String path) throws IOException {
        return org.apache.commons.io.FileUtils.sizeOf(toolset.absoluteFile(path));
    }

    @Override
    public BigInteger sizeOfAsBigInteger(String path) throws IOException {
        return org.apache.commons.io.FileUtils.sizeOfAsBigInteger(toolset.absoluteFile(path));
    }

    @Override
    public long sizeOfDirectory(String directory) throws IOException {
        return org.apache.commons.io.FileUtils.sizeOfDirectory(toolset.absoluteFile(directory));
    }

    @Override
    public BigInteger sizeOfDirectoryAsBigInteger(String directory) throws IOException {
        return org.apache.commons.io.FileUtils.sizeOfDirectoryAsBigInteger(toolset.absoluteFile(directory));
    }

    @Override
    public boolean isFileNewer(String path, String reference) throws IOException {
        return org.apache.commons.io.FileUtils.isFileNewer(toolset.absoluteFile(path), toolset.absoluteFile(reference));
    }

    @Override
    public boolean isFileNewer(String path, Date date) throws IOException {
        return org.apache.commons.io.FileUtils.isFileNewer(toolset.absoluteFile(path), date);
    }

    @Override
    public boolean isFileNewer(String path, long timeMillis) throws IOException {
        return org.apache.commons.io.FileUtils.isFileNewer(toolset.absoluteFile(path), timeMillis);
    }

    @Override
    public boolean isFileOlder(String path, String reference) throws IOException {
        return org.apache.commons.io.FileUtils.isFileOlder(toolset.absoluteFile(path), toolset.absoluteFile(reference));
    }

    @Override
    public boolean isFileOlder(String path, Date date) throws IOException {
        return org.apache.commons.io.FileUtils.isFileOlder(toolset.absoluteFile(path), date);
    }

    @Override
    public boolean isFileOlder(String path, long timeMillis) throws IOException {
        return org.apache.commons.io.FileUtils.isFileOlder(toolset.absoluteFile(path), timeMillis);
    }

    @Override
    public long checksumCRC32(String path) throws IOException {
        return org.apache.commons.io.FileUtils.checksumCRC32(toolset.absoluteFile(path));
    }

    @Override
    public Checksum checksum(String path, Checksum checksum) throws IOException {
        return org.apache.commons.io.FileUtils.checksum(toolset.absoluteFile(path), checksum);
    }

    @Override
    public void moveDirectory(String srcDir, String destDir) throws IOException {
        org.apache.commons.io.FileUtils.moveDirectory(toolset.absoluteFile(srcDir), toolset.absoluteFile(destDir));
    }

    @Override
    public void moveDirectoryToDirectory(String src, String destDir, boolean createDestDir) throws IOException {
        org.apache.commons.io.FileUtils.moveDirectoryToDirectory(toolset.absoluteFile(src), toolset.absoluteFile(destDir), createDestDir);
    }

    @Override
    public void moveFile(String srcFile, String destFile) throws IOException {
        org.apache.commons.io.FileUtils.moveFile(toolset.absoluteFile(srcFile), toolset.absoluteFile(destFile));
    }

    @Override
    public void moveFileToDirectory(String srcFile, String destDir, boolean createDestDir) throws IOException {
        org.apache.commons.io.FileUtils.moveFileToDirectory(toolset.absoluteFile(srcFile), toolset.absoluteFile(destDir), createDestDir);
    }

    @Override
    public void moveToDirectory(String src, String destDir, boolean createDestDir) throws IOException {
        org.apache.commons.io.FileUtils.moveToDirectory(toolset.absoluteFile(src), toolset.absoluteFile(destDir), createDestDir);
    }

    @Override
    public boolean isSymlink(String path) throws IOException {
        return org.apache.commons.io.FileUtils.isSymlink(toolset.absoluteFile(path));
    }

    //

    /**
     * Method to get the "type" of a path
     *
     * @param path
     * @return int
     */
    @Override
    public int type(String path) throws IOException {
        File file = toolset.absoluteFile(path);
        if (!file.exists()) {
            return NOT_FOUND;
        }
        else if (file.isDirectory()) {
            return DIRECTORY;
        }
        else {
            return FILE;
        }
    }

    /**
     * Method to get the "type" of a path as a String
     *
     * @param path
     * @return String
     */
    @Override
    public String typeString(String path) throws IOException {
        int type = type(path);
        switch(type) {
            case NOT_FOUND:
                return "NOT_FOUND";
            case DIRECTORY:
                return "DIRECTORY";
            case FILE:
                return "FILE";
            default:
                throw new IOException("Unknown path type [" + type + "]");
        }
    }

    @Override
    public boolean exists(String path) throws IOException {
        return (NOT_FOUND != type(path));
    }

    @Override
    public boolean isDirectory(String path) throws IOException {
        return (DIRECTORY == type(path));
    }

    @Override
    public boolean isFile(String path) throws IOException {
        return (FILE == type(path));
    }

    @Override
    public String md5Sum(String path) throws IOException {
        String md5Hex = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(toolset.absoluteFile(path)));
            md5Hex = DigestUtils.md5Hex(bufferedInputStream);
        }
        finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }

        return md5Hex;
    }

    @Override
    public String sha1Sum(String path) throws IOException {
        String result = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(toolset.absoluteFile(path)));
            result = DigestUtils.sha1Hex(bufferedInputStream);
        }
        finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }

        return result;
    }

    @Override
    public String sha256Sum(String path) throws IOException {
        String result = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(toolset.absoluteFile(path)));
            result = DigestUtils.sha256Hex(bufferedInputStream);
        }
        finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }

        return result;
    }
}
