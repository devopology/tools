package com.devopology.tools.test;

import com.devopology.tools.Toolset;

/**
 * Created by Doug on 10/22/2016.
 */
public class Example2 extends Toolset {
    public static void main(String [] args) throws Exception {
        new Example2().run(args);
    }

    public void run(String [] args) throws Exception {
        println("Let's go ...");
        //String getContent = doGet("https://www.google.com/");
        //println(getContent);
        setConfiguration(ACCEPT_INVALID_SSL_CERTIFICATE, "true");
        //HttpResponse httpResponse = doGet("https://www.selfsignedcertificate.com/");
        //println(httpResponse.toString());
        println("Done.");
    }
}
