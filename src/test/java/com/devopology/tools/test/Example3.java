package com.devopology.tools.test;

import com.devopology.tools.HttpToolset;

/**
 * Created by Doug on 10/22/2016.
 */
public class Example3 extends HttpToolset {
    public static void main(String [] args) throws Exception {
        new Example3().run(args);
    }

    public void run(String [] args) throws Exception {
        println("Let's go ...");
        println(pwd());
        cd("development");
        cd("C:/");
        println(pwd());
        cd("C:/development/git");
        println(pwd());
    }
}
