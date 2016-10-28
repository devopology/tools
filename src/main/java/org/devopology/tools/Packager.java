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

import java.io.File;

public class Packager extends Toolset {

    public static void main(String [] args) throws Exception {
        new Packager().run(args);
    }

    public void run(String [] args) throws Exception {
        String projectBaseDirectory = args[0];
        String ubJarFilename = args[1];

        // Mute logging for the initial cd since we
        // need to get to the correct working directory
        // and don't want to log that step
        setConfiguration(CONFIGURATION_LOGGER_MUTE, "true");

        changeDirectory(projectBaseDirectory);

        // Unmute logging
        setConfiguration(CONFIGURATION_LOGGER_MUTE, "false");

        File uberJarFile = absoluteFile(ubJarFilename).getCanonicalFile();

        info("Building UBER jar : " + uberJarFile.getCanonicalPath());
        info("");
        info("This may take a while ... We are merging all the dependencies into an UBER jar ... Please be patient ...");

        setConfiguration(CONFIGURATION_LOGGER_MUTE, "true");

        changeDirectory("target");
        if (exists("uber")) {
            deleteDirectory("uber");
        }

        forceMkdir("uber");
        forceMkdir("uber/classes");
        forceMkdir("uber/tmp");

        zip("classes", "uber/tmp/1.zip");
        unzip("uber/tmp/1.zip", "uber/classes");
        deleteDirectory("uber/tmp/1.zip");

        changeDirectory("lib");
        String [] files = absoluteFile(pwd()).list();
        if (null != files) {
            for (String filename : files) {
                File file = absoluteFile(filename);
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    info("filename = [" + file.getCanonicalPath() + "]");
                    unzip(file.getCanonicalPath(), absolutePath("../uber/classes"));
                }
            }
        }

        changeDirectory("..");
        //rmdir("uber/classes/junit");
        //rmdir("uber/classes/org/hamcrest");
        zip("uber/classes", uberJarFile.getCanonicalPath());

        changeDirectory("uber");
        files = listFiles(".").toArray(new String [0]);
        for (String filename : files) {
            if (!noPath(filename).equals("classes")) {
                deleteDirectory(absolutePath(filename));
            }
        }
    }
}
