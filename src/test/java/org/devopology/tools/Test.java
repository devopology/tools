package org.devopology.tools;

import java.io.File;

public class Test extends Toolset {

    public static void main(String [] args) throws Exception {
        new Test().run(args);
    }

    public void run(String [] args) throws Exception {
        info("Let's go ...");
        setConfiguration(CONFIGURATION_LOGGER_MUTE, "true");
        info("pwd = [" + pwd() + "]");
        changeDirectory("C:/development");
        File file = absoluteFile();
        info("file = [" + file.getCanonicalPath() + "]");
        changeDirectory("git");
        info("pwd = [" + pwd() + "]");
        file = absoluteFile();
        info("file = [" + file.getCanonicalPath() + "]");
        info("Done.");
    }
}
