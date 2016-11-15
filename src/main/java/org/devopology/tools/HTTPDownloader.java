package org.devopology.tools;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Interfrace for an HTTP downloader
 */
public interface HTTPDownloader {

    /**
     * Method to set the user agent
     *
     * @param userAgent
     */
    public void setUserAgent(String userAgent);

    /**
     * Method to get the user agent
     *
     * @return String
     */
    public String getUserAgent();

    /**
     * Method to set a request header
     *
     * @param key
     * @param value
     */
    public void setHeader(String key, String value);

    /**
     * Method to get a request header
     *
     * @param key
     * @return String
     */
    public String getHeader(String key);

    /**
     * Method to get the request header map
     *
     * @return Map<String, String>
     */
    public Map<String, String> getHeaderMap();

    /**
     * Method to download a file via HTTP / HTTPS
     *
     * @param url
     * @param filename
     * @throws IOException
     */
    public void downloadFileViaHTTP(URL url, String filename) throws IOException;
}
