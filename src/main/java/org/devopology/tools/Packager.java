package org.devopology.tools;

import java.io.File;
import java.util.List;

public class Packager extends Toolset {

    public static void main(String [] args) throws Exception {
        new Packager().run(args);
    }

    public void run(String [] args) throws Exception {
        String pwd = pwd();
        println("pwd = [" + pwd + "]");

        cd("target");
        if (exists("uber")) {
            rmdir("uber");
        }

        mkdirs("uber");
        mkdirs("uber/classes");
        mkdirs("uber/tmp");

        zip("classes", "uber/tmp/1.zip");
        unzip("uber/tmp/1.zip", "uber/classes");
        rm("uber/tmp/1.zip");

        cd("lib");
        String [] files = absoluteFile(pwd()).list();
        if (null != files) {
            for (String filename : files) {
                File file = absoluteFile(filename);
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    println("filename = [" + file.getCanonicalPath() + "]");
                    unzip(file.getCanonicalPath(), absolutePath("../uber/classes"));
                }
            }
        }

        cd("..");
        rmdir("uber/classes/junit");
        rmdir("uber/classes/org/hamcrest");
        zip("uber/classes", "org.devopology.tools.1.0.0-UBER.jar");

        cd("uber");
        files = listFiles(".").toArray(new String [0]);
        for (String filename : files) {
            if (!noPath(filename).equals("classes")) {
                rmdir(absolutePath(filename));
            }
        }

        println("Done.");
    }
}
