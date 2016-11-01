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

package org.devopology.tools.impl;

import org.devopology.tools.JSONUtils;
import org.devopology.tools.Toolset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONUtilsImpl implements JSONUtils {

    private final static JSONParser jsonParser = new JSONParser();

    private Toolset toolset = null;

    public JSONUtilsImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    private File absoluteFile(String path) throws IOException {
        return absoluteFile(new File(path));
    }

    private File absoluteFile(File path) throws IOException {
        if (path.isAbsolute()) {
            return path.getAbsoluteFile();
        }
        else {
            return new File(toolset.getCurrentDirectory().getPath() + File.separator + path.getPath());
        }
    }

    private String absolutePath(String path) throws IOException {
        return absoluteFile(new File(path)).getAbsolutePath();
    }

    private String absolutePath(File path) throws IOException {
        return absoluteFile(path).getAbsolutePath();
    }

    /**
     * Method to parse a String as a JSONObject
     *
     * @param json
     * @return JSONOBject
     */
    @Override
    public JSONObject parseJSONObject(String json) throws IOException {
        try {
            return (JSONObject) jsonParser.parse(
                    json,
                    new ContainerFactory() {
                        @Override
                        public Map createObjectContainer() {
                            return null;
                        }

                        @Override
                        public List creatArrayContainer() {
                            return null;
                        }
                    });
        }
        catch (Throwable t) {
            throw new IOException("parseJSONObject() Exception ", t);
        }
    }

    /**
     * Method to load a file's content as a JSONObject
     *
     * @param path
     * @return JSONObject
     */
    @Override
    public JSONObject loadJSONObject(String path) throws IOException {
        File file = absoluteFile(path);
        try {
            return (JSONObject) jsonParser.parse(
                    new FileReader(file),
                    new ContainerFactory() {
                        @Override
                        public Map createObjectContainer() {
                            return null;
                        }

                        @Override
                        public List creatArrayContainer() {
                            return null;
                        }
                    });
        }
        catch (Throwable t) {
            throw new IOException("loadJSONObject() Exception ", t);
        }
    }

    /**
     * Method to parse a String as a JSONArray
     *
     * @param json
     * @return JSONArray
     */
    @Override
    public JSONArray parseJSONArray(String json) throws IOException {
        try {
            return (JSONArray) jsonParser.parse(
                    json,
                    new ContainerFactory() {
                        @Override
                        public Map createObjectContainer() {
                            return null;
                        }

                        @Override
                        public List creatArrayContainer() {
                            return null;
                        }
                    });
        }
        catch (Throwable t) {
            throw new IOException("parseJSONArray() Exception ", t);
        }
    }

    /**
     * Method to load a file's content as a JSONArray
     *
     * @param path
     * @return JSONArray
     */
    @Override
    public JSONArray loadJSONArray(String path) throws IOException {
        try {
            File file = absoluteFile(path);
            return (JSONArray) jsonParser.parse(
                    new FileReader(file),
                    new ContainerFactory() {
                        @Override
                        public Map createObjectContainer() {
                            return null;
                        }

                        @Override
                        public List creatArrayContainer() {
                            return null;
                        }
                    });
        }
        catch (Throwable t) {
            throw new IOException("loadJSONArray() Exception ", t);
        }
    }
}
