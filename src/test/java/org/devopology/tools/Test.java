package org.devopology.tools;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;

import java.io.File;

public class Test extends Toolset {

    public static void main(String [] args) throws Exception {
        new Test().test();
    }

    private static String toPlatform(String path) {
        char [] characters = path.toCharArray();
        for (int i=0; i<characters.length; i++) {
            if (characters[i] == '\\') {
                characters[i] = '/';
            }
        }

        return new String(characters);
    }

    public void test() throws Exception {
        info("Let's go ...");

        String rootPath = "/";

        if (SystemUtils.IS_OS_WINDOWS) {
            rootPath = "C:/";
        }

        changeDirectory(rootPath);

        File file = new File(rootPath + "temp/TEST");

        if (getFileUtils().exists(file)) {
            getFileUtils().forceDelete(file);
        }

        Assert.assertFalse(getFileUtils().exists(file));

        getFileUtils().forceMkdir(file);
        Assert.assertTrue(getFileUtils().exists(file));

        changeDirectory(file);
        Assert.assertEquals(rootPath + "temp/TEST", toPlatform(pwd().getAbsolutePath()));

        File testFile = new File("test.txt");
        String string = "This is a test string";
        getFileUtils().write(testFile, string);

        Assert.assertTrue(getFileUtils().exists(testFile));

        String expectedMD5 = "c639efc1e98762233743a75e7798dd9c";
        String md5 = getFileUtils().md5Sum(testFile);
        Assert.assertEquals(expectedMD5, md5);

        changeDirectory(new File(".."));
        Assert.assertTrue(getFileUtils().exists(new File("TEST/test.txt")));

        info("Done.");
    }
}
