package org.devopology.tools;

import java.io.File;

public class Test extends Toolset {

    public static void main(String [] args) throws Exception {
        new Test().run(args);
    }

    public void run(String [] args) throws Exception {
        info("Let's go ...");
        info("pwd = [" + pwd() + "]");
        changeDirectory("C:/development");
        File file = absoluteFile();
        info("file = [" + file.getCanonicalPath() + "]");
        changeDirectory("git");
        info("pwd = [" + pwd() + "]");
        file = absoluteFile();
        info("file = [" + file.getCanonicalPath() + "]");

        long crc32 = checkSumCRC32("C:/ffmpeg.exe");
        info("crc32 = [" + crc32 + "]");

        String md5 = checkSumMD5("C:/ffmpeg.exe");
        info("md5 = [" + md5 + "]");

        String sha1 = checkSumSHA1("C:/ffmpeg.exe");
        info("sha1 = [" + sha1 + "]");

        info("Done.");
    }
}
