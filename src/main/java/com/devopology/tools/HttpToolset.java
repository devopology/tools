package com.devopology.tools;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpToolset extends BaseToolset {

    public HttpToolset() {
        super();
        this.className = getCallerClassName();
    }

    protected CloseableHttpClient getHttpClient() throws Exception {
        CloseableHttpClient result = null;

        /*
        if (true == configurationTrustSSLCert) {
            SSLContextBuilder builder = new SSLContextBuilder();

            SSLContext sslContext =
                    builder.loadTrustMaterial(null, new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                            return true;
                        }
                    }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSocketFactory)
                    .build();

            // now, we create connection-manager using our Registry.
            //      -- allows multi-threaded use
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            result = HttpClients.custom().setConnectionManager(connectionManager).setSSLSocketFactory(sslsf).build();
        }
        else {
            result = HttpClients.createDefault();
        }
        */
        result = HttpClients.createDefault();

        return result;
    }

    public String doGet(String url) {
        output(this.className + ".doGet( " + url + " )");

        String result = null;
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;

        try {
            httpclient = getHttpClient();
            HttpGet httpGet = new HttpGet(url);

            response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (200 == statusCode) {
                BufferedReader bufferedReader = null;

                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append("\r\n");
                        }
                        stringBuilder.append(line);
                    }

                    result = stringBuilder.toString();
                }
                finally {
                    if (null != bufferedReader) {
                        try {
                            bufferedReader.close();
                        }
                        catch (Throwable t) {
                            // DO NOTHING
                        }
                    }
                }
            }
            else if (404 == statusCode) {
                // DO NOTHING
            }
            else {
                throw new IOException("Exception in doGet() statusCode = [" + statusCode + "]");
            }
        }
        catch (RuntimeException re) {
            throw re;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (null != response) {
                try {
                    response.close();
                }
                catch (Throwable t) {
                    // DO NOTHING
                }
            }

            if (null != httpclient) {
                try {
                    httpclient.close();
                }
                catch (Throwable t) {
                    // DO NOTHIKNG
                }
            }
        }

        return result;
    }

}
