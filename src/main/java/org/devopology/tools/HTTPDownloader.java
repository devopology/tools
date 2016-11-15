package org.devopology.tools;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public interface HTTPDownloader {

    public void setUserAgent(String userAgent);

    public String getUserAgent();

    public void setHeader(String key, String value);

    public String getHeader(String key);

    public Map<String, String> getHeaderMap();

    public void downloadFileViaHTTP(URL url, String filename) throws IOException;
}
