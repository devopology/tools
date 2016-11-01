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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by Doug on 10/31/2016.
 */
public interface JSONUtils {

    public JSONObject parseJSONObject(String json) throws IOException;

    /**
     * Method to load a file's content as a JSONObject
     *
     * @param path
     * @return JSONObject
     */
    public JSONObject loadJSONObject(String path) throws IOException;

    /**
     * Method to parse a String as a JSONArray
     *
     * @param json
     * @return JSONArray
     */
    public JSONArray parseJSONArray(String json) throws IOException;

    /**
     * Method to load a file's content as a JSONArray
     *
     * @param path
     * @return JSONArray
     */
    public JSONArray loadJSONArray(String path) throws IOException;
}
