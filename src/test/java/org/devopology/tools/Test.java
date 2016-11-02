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

import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;

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
        banner("TESTING");

        String rootPath = "/tmp/";

        if (SystemUtils.IS_OS_WINDOWS) {
            rootPath = "C:/tmp/";
        }

        changeDirectory(rootPath);

        String path = rootPath + "temp/TEST";

        if (getFileUtils().exists(path)) {
            getFileUtils().cleanDirectory(path);
            getFileUtils().deleteDirectory(path);
        }

        Assert.assertFalse(getFileUtils().exists(path));

        getFileUtils().forceMkdir(path);
        Assert.assertTrue(getFileUtils().exists(path));

        changeDirectory(path);
        Assert.assertEquals(rootPath + "temp/TEST", toPlatform(pwd()));

        String testFile = "test.txt";
        String string = "This is a test string";
        getFileUtils().write(testFile, string);

        Assert.assertTrue(getFileUtils().exists(testFile));

        String expectedMD5 = "c639efc1e98762233743a75e7798dd9c";
        String md5 = getFileUtils().md5Sum(testFile);
        Assert.assertEquals(expectedMD5, md5);

        changeDirectory("..");
        Assert.assertTrue(getFileUtils().exists("TEST/test.txt"));

        String username = getSystemUtils().whoami();
        info("username = [" + username + "]");

        String ls = getSystemUtils().resolve("ls", org.devopology.tools.SystemUtils.DEFAULT_UNIX_SEARCH_PATHS);
        String output = getExecUtils().execute(ls, null, 0).getOutput();
        info("ls = [" + output + "]");

        info("I am root = [" + getSystemUtils().isUser("root") + "]");

        String filename = "www.devopology.org_index_html";
        getNetworkUtils().downloadFileViaHTTP("https://raw.githubusercontent.com/devopology/tools/master/README.md", filename);

        //String downloadedFileContents = getFileUtils().readFileToString(filename);
        //info(downloadedFileContents);

        banner("TESTING DONE", true);
    }
}
