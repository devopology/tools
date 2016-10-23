package com.devopology.tools.test;

import com.devopology.tools.HttpToolset;

/**
 * Created by Doug on 10/22/2016.
 */
public class Example2 extends HttpToolset {
    public static void main(String [] args) throws Exception {
        new Example2().run(args);
    }

    public void run(String [] args) throws Exception {
        println("Let's go ...");
        String content = doGet("https://www.google.com/");
        println(content);
        println("Done.");
    }
}
