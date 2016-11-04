/*
 *  Licensed under CC BY-SA 3.0 per stackoverflow.com licensing terms
 *
 *  License : https://creativecommons.org/licenses/by-sa/3.0/
 *  Code : http://stackoverflow.com/questions/15968883/how-to-zip-a-folder-itself-using-java
 *  Author : Tony BenBrahim, Apr 16 '15 at 13:05
 */

package org.devopology.tools.impl.ziputils;

import org.apache.commons.io.IOUtils;
import org.devopology.tools.Toolset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ZipUtils2 {

    public Toolset toolset = null;

    public ZipUtils2(Toolset toolset) {
        this.toolset = toolset;
    }

    public void zipFolder(final File folder, final File zipFile) throws IOException {
        zipFolder(folder, new FileOutputStream(zipFile));
    }

    public void zipFolder(final File folder, final OutputStream outputStream) throws IOException {
        //toolset.getLogger().info("folder = [" + folder.getPath() + "]");
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            if (folder.isAbsolute()) {
                processFolder(folder, zipOutputStream, folder.getPath().length() + 1);
            }
            else {
                processFolder(folder.getParentFile(), zipOutputStream, folder.getParentFile().getPath().length() + 1);
            }
        }
    }

    private void processFolder(final File folder, final ZipOutputStream zipOutputStream, final int prefixLength)
            throws IOException {
        //toolset.getLogger().info("folder = [" + folder.getPath() + "]");
        for (final File file : folder.listFiles()) {
            //toolset.getLogger().info("file = [" + file.getPath() + "]");
            if (file.isFile()) {
                final ZipEntry zipEntry = new ZipEntry(file.getPath().substring(prefixLength));
                zipOutputStream.putNextEntry(zipEntry);
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    IOUtils.copy(inputStream, zipOutputStream);
                }
                zipOutputStream.closeEntry();
            } else if (file.isDirectory()) {
                processFolder(file, zipOutputStream, prefixLength);
            }
        }
    }
}