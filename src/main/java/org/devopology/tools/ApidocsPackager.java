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

public class ApidocsPackager extends Toolset {

    public static void main(String [] args) throws Exception {
        new ApidocsPackager().execute(args);
    }

    public void execute(String [] args) throws Exception {
        String status = "FAILURE";

        try {
            banner("PACKAGING");

            String projectBaseDir = args[0];
            info("projectBaseDir : " + projectBaseDir);

            String apidocsPath = getFileUtils().getPath(projectBaseDir, "target", "apidocs");

            if (getFileUtils().exists(apidocsPath)) {
                changeDirectory(apidocsPath);
                changeDirectory("..");

                info("Building " + absolutePath("apidocs.zip"));
                getZipUtils().zip("apidocs", "apidocs.zip");
            }
            else {
                warn("No apidocs to package");
            }

            status = "SUCCESS";
        }
        catch (Throwable t) {
            error("Exception during packaging...", t);
        }
        finally {
            banner("PACKAGING " + status, true);
        }
    }
}
