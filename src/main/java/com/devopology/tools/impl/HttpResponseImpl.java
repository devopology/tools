package com.devopology.tools.impl;

import com.devopology.tools.HttpResponse;

public class HttpResponseImpl implements HttpResponse {

    private int statusCode = 200;
    private String content = null;

    public HttpResponseImpl() {
        // DO NOTHING
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        return "HttpResponse(statusCode = [" + statusCode + "] content = [" + content + "])";
    }
}
