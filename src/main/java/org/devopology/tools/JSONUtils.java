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
import org.json.simple.parser.ContainerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Doug on 10/31/2016.
 */
public interface JSONUtils {

    /**
     * LinkedHashMap based ConfainerFactory ... the default choice
     */
    public static final ContainerFactory LINKED_HASHMAP_CONTAINER_FACTORY = new ContainerFactory() {
        @Override
        public Map createObjectContainer() {
            return new LinkedHashMap();

        }

        @Override
        public List creatArrayContainer() {
            return new ArrayList();
        }
    };

    /**
     * TreeMap base ContainerFactory
     */
    public static final ContainerFactory TREEMAP_HASHMAP_CONTAINER_FACTORY = new ContainerFactory() {
        @Override
        public Map createObjectContainer() {
            return new TreeMap();

        }

        @Override
        public List creatArrayContainer() {
            return new ArrayList();
        }
    };

    /**
     * Method to convert a Map to a JSON String
     *
     * @param map
     * @return String
     */
    public String toJSONString(Map map);

    /**
     * Method to convert a List to a JSON String
     *
     * @param list
     * @return String
     */
    public String toJSONString(List list);

    /**
     * Method to parse a JSON String into a JSONObject
     *
     * @param json
     * @return JSONObject
     * @throws IOException
     */
    public JSONObject parseJSONObject(String json) throws IOException;

    /**
     * Method to parse a JSON String as a Map
     * @param json
     * @param containerFactory
     * @return Map
     * @throws IOException
     */
    public Map parseMap(String json, ContainerFactory containerFactory) throws IOException;

    /**
     * Method to load and parse file's content as a JSONObject
     *
     * @param path
     * @return JSONObject
     */
    public JSONObject loadJSONObject(String path) throws IOException;

    /**
     * Method to parse a JSON String as a JSONArray
     *
     * @param json
     * @return JSONArray
     */
    public JSONArray parseJSONArray(String json) throws IOException;

    /**
     * Method to parse a JSON String as a List
     *
     * @param json
     * @param containerFactory
     * @return List
     * @throws IOException
     */
    public List parseList(String json, ContainerFactory containerFactory) throws IOException;

    /**
     * Method to load and parse a file's content as a JSONArray
     *
     * @param path
     * @return JSONArray
     */
    public JSONArray loadJSONArray(String path) throws IOException;
}
