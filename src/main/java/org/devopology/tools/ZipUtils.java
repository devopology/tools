package org.devopology.tools;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Doug on 10/28/2016.
 */
public class ZipUtils {

    protected Toolset toolset = null;
    protected CurrentDirectory currentDirectory = null;

    public ZipUtils(Toolset toolset) {
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
