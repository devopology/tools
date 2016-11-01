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

import org.devopology.tools.ExecResult;

public class ExecResultImpl implements ExecResult {

    private int exitCode = 0;
    private String output = null;

    public ExecResultImpl() {
        // DO NOTHING
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    @Deprecated
    public void setContent(String content) {
        setOutput(content);
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    @Deprecated
    public String getContent() {
        return getOutput();
    }

    @Override
    public String getOutput() {
        return getOutput(true);
    }

    public String getOutput(boolean trim) {
        String output = this.output;
        if ((null != output) && (true == trim)) {
            output = output.trim();
        }
        return output;
    }

    @Override
    public String toString() {
        return getOutput();
    }
}
