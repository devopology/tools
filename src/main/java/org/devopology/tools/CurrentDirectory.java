package org.devopology.tools;

import java.io.File;
import java.io.IOException;

/**
 * Created by Doug on 10/28/2016.
 */
public class CurrentDirectory {

    private File file = null;

    public CurrentDirectory() {
        this(new File(".").getAbsoluteFile());
    }

    public CurrentDirectory(File file) {
        if (file.isAbsolute()) {
            this.file = file;
        }
        else {
            this.file = file.getAbsoluteFile();
        }
    }

    public File getCurrentDirectory() throws IOException {
        return file.getCanonicalFile();
    }

    public File absoluteFile(File file) throws IOException {
        if (file.isAbsolute()) {
            return file;
        }
        else {
            return new File(this.file.getCanonicalPath() + File.separator + file.getPath()).getCanonicalFile();
        }
    }

    public File absoluteFile(String path) throws IOException {
        return absoluteFile(new File(path));
    }

    public String absolutePath(File file) throws IOException {
        return absoluteFile(file).getAbsolutePath();
    }

    public String absolutePath(String path) throws IOException {
        return absoluteFile(path).getCanonicalPath();
    }

    public File changeDirectory(String path) throws IOException {
        //System.out.println("cwd = [" + this.file.getCanonicalFile() + "]");
        File file = new File(path);
        //System.out.println("file = [" + file.getPath() + "]");

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
