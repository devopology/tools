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
        info("pwd = [" + pwd() + "]");
        getFileUtils().forceMkdir(new File("../../temp/package/org"));
        changeDirectory("../../temp/package/org");
        info("pwd = [" + pwd() + "]");
        //getFileUtils().copyDirectory(new File("C:/development/git/org"), new File("."));
        //getZipUtils().zip("../package/", "C:/temp/test.zip");

        changeDirectory("C:/");
        changeDirectory("./development");
        changeDirectory("git");
        getZipUtils().zip(".", "C:/temp/git.zip");

        info("Done.");
    }
}
