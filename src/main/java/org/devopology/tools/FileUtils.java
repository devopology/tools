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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;

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
public class FileUtils {

    /**
     * File types
     */
    public final static int NOT_FOUND = -1;
    public final static int DIRECTORY = 0;
    public final static int FILE = 1;

    private CurrentDirectory currentDirectory = null;

    FileUtils(Toolset toolset) {
        this.currentDirectory = toolset.getCurrentDirectory();
    }

    private File absoluteFile(File file) throws IOException {
        return currentDirectory.absoluteFile(file);
    }

    public File getFile(File directory, String... names) throws IOException {
        directory = absoluteFile(directory);
        return absoluteFile(org.apache.commons.io.FileUtils.getFile(directory, names));
    }

    public File getFile(String... names) throws IOException {
        return absoluteFile(org.apache.commons.io.FileUtils.getFile(names));
    }

    public String getTempDirectoryPath() throws IOException {
        return getTempDirectory().getCanonicalPath();
    }

    public File getTempDirectory() throws IOException {
        return absoluteFile(org.apache.commons.io.FileUtils.getTempDirectory());
    }

    public String getUserDirectoryPath() throws IOException {
        return getUserDirectory().getCanonicalPath();
    }

    public File getUserDirectory() throws IOException {
        return absoluteFile(org.apache.commons.io.FileUtils.getUserDirectory());
    }

    public FileInputStream openInputStream(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.openInputStream(absoluteFile(file));
    }

    public FileOutputStream openOutputStream(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.openOutputStream(file);
    }

    public FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.openOutputStream(file, append);
    }

    public String byteCountToDisplaySize(BigInteger size) {
        return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
    }

    public String byteCountToDisplaySize(long size) {
        return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
    }

    public void touch(File file) throws IOException {
        org.apache.commons.io.FileUtils.touch(file);
    }

    public File [] convertFileCollectionToFileArray(Collection<File> files) throws IOException {
        File [] result = org.apache.commons.io.FileUtils.convertFileCollectionToFileArray(files);
        for (int i=0; i<result.length; i++) {
            result[i] = absoluteFile(result[i]);
        }

        return result;
    }

    public Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) throws IOException {
        directory = absoluteFile(directory);
        Collection<File> temp = org.apache.commons.io.FileUtils.listFiles(directory, fileFilter, dirFilter);
        Collection<File> result = new ArrayList<File>();
        for (File file : temp) {
            result.add(absoluteFile(file));
        }
        return result;
    }

    public Collection<File> listFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) throws IOException {
        directory = absoluteFile(directory);
        Collection<File> temp = org.apache.commons.io.FileUtils.listFilesAndDirs(directory, fileFilter, dirFilter);
        Collection<File> result = new ArrayList<File>();
        for (File file : temp) {
            result.add(absoluteFile(file));
        }
        return result;
    }

    public Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) throws IOException {
        return listFiles(directory, fileFilter, dirFilter).iterator();
    }

    public Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) throws IOException {
        return listFilesAndDirs(directory, fileFilter, dirFilter).iterator();
    }

    public Collection<File> listFiles(File directory, String [] extensions, boolean recursive) throws IOException {
        directory = absoluteFile(directory);
        Collection<File> temp = org.apache.commons.io.FileUtils.listFiles(directory, extensions, recursive);
        Collection<File> result = new ArrayList<File>();
        for (File file : temp) {
            result.add(absoluteFile(file));
        }
        return result;
    }

    public Iterator<File> iterateFiles(File directory, String [] extensions, boolean recursive) throws IOException {
        return listFiles(directory, extensions, recursive).iterator();
    }

    public boolean contentEquals(File file1, File file2) throws IOException {
        file1 = absoluteFile(file1);
        file2 = absoluteFile(file2);
        return org.apache.commons.io.FileUtils.contentEquals(file1, file2);
    }

    public boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName) throws IOException {
        file1 = absoluteFile(file1);
        file2 = absoluteFile(file2);
        return org.apache.commons.io.FileUtils.contentEqualsIgnoreEOL(file1, file2, charsetName);
    }

    public File toFile(URL url) throws IOException {
        return absoluteFile(org.apache.commons.io.FileUtils.toFile(url));
    }

    public File [] toFiles(URL [] urls) throws IOException {
        File [] result = org.apache.commons.io.FileUtils.toFiles(urls);
        for (int i=0; i<result.length; i++) {
            result[i] = absoluteFile(result[i]);
        }
        return result;
    }

    public URL [] toURLs(File [] files) throws IOException {
        for (int i=0; i<files.length; i++) {
            files[i] = absoluteFile(files[i]);
        }
        return org.apache.commons.io.FileUtils.toURLs(files);
    }

    public void copyFileToDirectory(File srcFile, File destDir) throws IOException {
        srcFile = absoluteFile(srcFile);
        org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, destDir);
    }

    public void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) throws IOException {
        srcFile = absoluteFile(srcFile);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
    }

    public void copyFile(File srcFile, File destFile) throws IOException {
        srcFile = absoluteFile(srcFile);
        destFile = absoluteFile(destFile);
        org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
    }

    public void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        srcFile = absoluteFile(srcFile);
        destFile = absoluteFile(destFile);
        org.apache.commons.io.FileUtils.copyFile(srcFile, destFile, preserveFileDate);
    }

    public long copyFile(File input, OutputStream output) throws IOException {
        input = absoluteFile(input);
        return org.apache.commons.io.FileUtils.copyFile(input, output);
    }

    public void copyDirectoryToDirectory(File srcDir, File destDir) throws IOException {
        srcDir = absoluteFile(srcDir);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.copyDirectoryToDirectory(srcDir, destDir);
    }

    public void copyDirectory(File srcDir, File destDir) throws IOException {
        srcDir = absoluteFile(srcDir);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir);
    }

    public void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
        srcDir = absoluteFile(srcDir);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
    }

    public void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
        srcDir = absoluteFile(srcDir);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, filter);
    }

    public void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
        srcDir = absoluteFile(srcDir);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.copyDirectory(srcDir, destDir, filter, preserveFileDate);
    }

    public void copyURLToFile(URL source, File destination) throws IOException {
        destination = absoluteFile(destination);
        org.apache.commons.io.FileUtils.copyURLToFile(source, destination);
    }

    public void copyURLToFile(URL source, File destination, int connectionTimeout, int readTimeout) throws IOException {
        destination = absoluteFile(destination);
        org.apache.commons.io.FileUtils.copyURLToFile(source, destination, connectionTimeout, readTimeout);
    }

    public void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        destination = absoluteFile(destination);
        org.apache.commons.io.FileUtils.copyInputStreamToFile(source, destination);
    }

    public void copyToFile(InputStream source, File destination) throws IOException {
        destination = absoluteFile(destination);
        org.apache.commons.io.FileUtils.copyToFile(source, destination);
    }

    public void deleteDirectory(File directory) throws IOException {
        directory = absoluteFile(directory);
        org.apache.commons.io.FileUtils.deleteDirectory(directory);
    }

    public boolean deleteQuietly(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.deleteQuietly(file);
    }

    public boolean directoryContains(File directory, File child) throws IOException {
        directory = absoluteFile(directory);
        child = absoluteFile(child);
        return org.apache.commons.io.FileUtils.directoryContains(directory, child);
    }

    public void cleanDirectory(File directory) throws IOException {
        directory = absoluteFile(directory);
        org.apache.commons.io.FileUtils.cleanDirectory(directory);
    }

    public boolean waitFor(File file, int seconds) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.waitFor(file, seconds);
    }

    public String readFileToString(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public String readFileToString(File file, Charset encoding) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
    }

    public String readFileToString(File file, String encoding) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
    }

    public byte [] readFileToByteArray(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.readFileToByteArray(file);
    }

    public List<String> readLines(File file, Charset encoding) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.readLines(file, encoding);
    }

    public List<String> readLines(File file, String encoding) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.readLines(file, encoding);
    }

    public LineIterator lineIterator(File file, String encoding) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.lineIterator(file, encoding);
    }

    public LineIterator lineIterator(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.lineIterator(file);
    }

    public void writeStringToFile(File file, String data, Charset encoding) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding);
    }

    public void writeStringToFile(File file, String data, String encoding) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding);
    }

    public void writeStringToFile(File file, String data, Charset encoding, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding, append);
    }

    public void writeStringToFile(File file, String data, String encoding, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding, append);
    }

    public void writeStringToFile(File file, String data) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8);
    }

    public void writeStringToFile(File file, String data, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8, append);
    }

    public void write(File file, CharSequence data) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.write(file, data, StandardCharsets.UTF_8);
    }

    public void write(File file, CharSequence data, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.write(file, data, StandardCharsets.UTF_8, append);
    }

    public void write(File file, CharSequence data, Charset encoding) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.write(file, data, encoding);
    }

    public void write(File file, CharSequence data, String encoding) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.write(file, data, encoding);
    }

    public void write(File file, CharSequence data, Charset encoding, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.write(file, data, encoding, append);
    }

    public void write(File file, CharSequence data, String encoding, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.write(file, data, encoding, append);
    }

    public void writeByteArrayToFile(File file, byte [] data) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
    }

    public void writeByteArrayToFile(File file, byte [] data, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data, append);
    }

    public void writeByteArrayToFile(File file, byte [] data, int off, int len) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data, off, len);
    }

    public void writeByteArrayToFile(File file, byte [] data, int off, int len, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data, off, len, append);
    }

    public void writeLines(File file, String encoding, Collection<?> lines) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, encoding, lines);
    }

    public void writeLines(File file, String encoding, Collection<?> lines, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, encoding, lines, append);
    }

    public void writeLines(File file, Collection<?> lines) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, lines);
    }

    public void writeLines(File file, Collection<?> lines, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, lines, append);
    }

    public void writeLines(File file, String encoding, Collection<?> lines, String lineEnding) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, encoding, lines, lineEnding);
    }

    public void writeLines(File file, String encoding, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, encoding, lines, lineEnding, append);
    }

    public void writeLines(File file, Collection<?> lines, String lineEnding) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, lines, lineEnding);
    }

    public void writeLines(File file, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.writeLines(file, lines, lineEnding, append);
    }

    public void forceDelete(File file) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.forceDelete(file);
    }

    public void forceDeleteOnExit(File file) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
    }

    public void forceMkdir(File directory) throws IOException {
        directory = absoluteFile(directory);
        org.apache.commons.io.FileUtils.forceMkdir(directory);
    }

    public void forceMkdirParent(File file) throws IOException {
        file = absoluteFile(file);
        org.apache.commons.io.FileUtils.forceMkdirParent(file);
    }

    public long sizeOf(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.sizeOf(file);
    }

    public BigInteger sizeOfAsBigInteger(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.sizeOfAsBigInteger(file);
    }

    public long sizeOfDirectory(File directory) throws IOException {
        directory = absoluteFile(directory);
        return org.apache.commons.io.FileUtils.sizeOfDirectory(directory);
    }

    public BigInteger sizeOfDirectoryAsBigInteger(File directory) throws IOException {
        directory = absoluteFile(directory);
        return org.apache.commons.io.FileUtils.sizeOfDirectoryAsBigInteger(directory);
    }

    public boolean isFileNewer(File file, File reference) throws IOException {
        file = absoluteFile(file);
        reference = absoluteFile(reference);
        return org.apache.commons.io.FileUtils.isFileNewer(file, reference);
    }

    public boolean isFileNewer(File file, Date date) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.isFileNewer(file, date);
    }

    public boolean isFileNewer(File file, long timeMillis) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.isFileNewer(file, timeMillis);
    }

    public boolean isFileOlder(File file, File reference) throws IOException {
        file = absoluteFile(file);
        reference = absoluteFile(reference);
        return org.apache.commons.io.FileUtils.isFileOlder(file, reference);
    }

    public boolean isFileOlder(File file, Date date) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.isFileOlder(file, date);
    }

    public boolean isFileOlder(File file, long timeMillis) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.isFileOlder(file, timeMillis);
    }

    public long checksumCRC32(File file) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.checksumCRC32(file);
    }

    public Checksum checksum(File file, Checksum checksum) throws IOException {
        file = absoluteFile(file);
        return org.apache.commons.io.FileUtils.checksum(file, checksum);
    }

    public void moveDirectory(File srcDir, File destDir) throws IOException {
        srcDir = absoluteFile(srcDir);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.moveDirectory(srcDir, destDir);
    }

    public void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
        src = absoluteFile(src);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.moveDirectoryToDirectory(src, destDir, createDestDir);
    }

    public void moveFile(File srcFile, File destFile) throws IOException {
        srcFile = absoluteFile(srcFile);
        destFile = absoluteFile(destFile);
        org.apache.commons.io.FileUtils.moveFile(srcFile, destFile);
    }

    public void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws IOException {
        srcFile = absoluteFile(srcFile);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
    }

    public void moveToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
        src = absoluteFile(src);
        destDir = absoluteFile(destDir);
        org.apache.commons.io.FileUtils.moveToDirectory(src, destDir, createDestDir);
    }

    public boolean isSymlink(File file) throws IOException {
        return org.apache.commons.io.FileUtils.isSymlink(file);
    }

    //

    /**
     * Method to get the "type" of a file
     *
     * @param file
     * @return int
     */
    public int type(File file) throws IOException {
        file = currentDirectory.absoluteFile(file);
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
     * Method to get the "type" of a file as a String
     *
     * @param file
     * @return String
     */
    public String typeString(File file) throws IOException {
        int type = type(file);
        switch(type) {
            case NOT_FOUND:
                return "NOT_FOUND";
            case DIRECTORY:
                return "DIRECTORY";
            case FILE:
                return "FILE";
            default:
                throw new IOException("Unknown file type [" + type + "]");
        }
    }

    public boolean exists(File file) throws IOException {
        return (NOT_FOUND != type(file));
    }

    public boolean isDirectory(File file) throws IOException {
        return (DIRECTORY == type(file));
    }

    public boolean isFile(File file) throws IOException {
        return (FILE == type(file));
    }

    public String md5Sum(File file) throws IOException {
        String md5Hex = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(absoluteFile(file)));
            md5Hex = DigestUtils.md5Hex(bufferedInputStream);
        }
        finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }

        return md5Hex;
    }

    public String sha1Sum(File file) throws IOException {
        String result = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(absoluteFile(file)));
            result = DigestUtils.sha1Hex(bufferedInputStream);
        }
        finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }

        return result;
    }

    public String sha256Sum(File file) throws IOException {
        String result = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(absoluteFile(file)));
            result = DigestUtils.sha256Hex(bufferedInputStream);
        }
        finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }

        return result;
    }
}
