package com.devopology.tools.impl;

import com.devopology.tools.ExecutionResult;

public class ExecutionResultImpl implements ExecutionResult {

    private int exitCode = 0;
    private String content = null;

    public ExecutionResultImpl() {
        // DO NOTHING
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return content;
    }
}
