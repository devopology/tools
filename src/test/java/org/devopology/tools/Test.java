package org.devopology.tools;

import java.io.File;

public class Test extends Toolset {

    public static void main(String [] args) throws Exception {
        new Test().run(args);
    }

    public void run(String [] args) throws Exception {
        info("Let's go ...");
        info("pwd = [" + pwd() + "]");

        changeDirectory("C:/development/git/");
        getFileUtils().copyDirectory(new File("org"), new File("C:/temp/package/org"));
        getZipUtils().zip("../../temp/package/", "C:/temp/test.zip");

        info("Done.");
    }
}
