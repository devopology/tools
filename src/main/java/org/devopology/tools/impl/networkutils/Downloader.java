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
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Downloader {

    public File download(URL url, File dstFile) throws Exception {
        CloseableHttpClient httpclient = HttpClients.custom()
                .setRedirectStrategy(new LaxRedirectStrategy()) // adds HTTP REDIRECT support to GET and POST methods
                .build();
        try {
            HttpGet get = new HttpGet(url.toURI()); // we're using GET but it could be via POST as well
            File downloaded = httpclient.execute(get, new FileDownloadResponseHandler(dstFile));

            return downloaded;
        }
        finally {
            IOUtils.closeQuietly(httpclient);
        }
    }

    private static class FileDownloadResponseHandler implements ResponseHandler<File> {

        private final File target;

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