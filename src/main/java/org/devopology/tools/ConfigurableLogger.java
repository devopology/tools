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

import org.slf4j.Logger;
import org.slf4j.spi.LocationAwareLogger;

public interface ConfigurableLogger extends Logger {

    /**
     * TRACE log level
     */
    public static final int LOG_LEVEL_TRACE = LocationAwareLogger.TRACE_INT;

    /**
     * DEBUG log level
     */
    public static final int LOG_LEVEL_DEBUG = LocationAwareLogger.DEBUG_INT;

    /**
     * INFO log level
     */
    public static final int LOG_LEVEL_INFO = LocationAwareLogger.INFO_INT;

    /**
     * WARN log level
     */
    public static final int LOG_LEVEL_WARN = LocationAwareLogger.WARN_INT;

    /**
     * ERROR log level
     */
    public static final int LOG_LEVEL_ERROR = LocationAwareLogger.ERROR_INT;

    /**
     * Method to set the log level
     *
     * @param logLevel
     */
    public void setLogLevel(int logLevel);
}
