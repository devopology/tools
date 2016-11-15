/*
 *  Licensed under CC BY-SA 3.0
 *
 *  License : https://creativecommons.org/licenses/by-sa/3.0/
 *  Code : https://gist.github.com/rponte/09ddc1aa7b9918b52029
 *  Author : Rafael Ponte, Mar 27, '15
 */

package org.devopology.tools.impl.networkutils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.devopology.tools.HTTPDownloader;
import org.devopology.tools.Toolset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPDownloaderImpl implements HTTPDownloader {

    private Toolset toolset = null;
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    private Map<String, String> headerMap = new HashMap<String, String>();

    public HTTPDownloaderImpl(Toolset toolset) {
        this.toolset = toolset;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public void setHeader(String key, String value) {
        headerMap.put(key, value);
    }

    @Override
    public String getHeader(String key) {
        return headerMap.get(key);
    }

    @Override
    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void downloadFileViaHTTP(URL url, String filename) throws IOException {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        try {
            File file = toolset.absoluteFile(filename);
            HttpGet get = new HttpGet(url.toURI());

            Map<String, String> headerMap = getHeaderMap();
            for (String name : headerMap.keySet()) {
                get.setHeader(name, headerMap.get(name));
            }

            get.setHeader(HttpHeaders.USER_AGENT, userAgent);

            httpclient.execute(get, new FileDownloadResponseHandler(file));
        }
        catch (URISyntaxException urise) {
            throw new IOException(urise);
        }
        finally {
            IOUtils.closeQuietly(httpclient);
        }
    }

    private static class FileDownloadResponseHandler implements ResponseHandler<File> {

        private File target = null;

        public FileDownloadResponseHandler(File target) {
            this.target = target;
        }

        @Override
        public File handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            InputStream source = response.getEntity().getContent();
            FileUtils.copyInputStreamToFile(source, this.target);
            return this.target;
        }
    }
}