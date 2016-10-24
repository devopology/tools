package com.devopology.tools.test;

import com.devopology.tools.ExecutionResult;
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

        ExecutionResult executionResult = execute(LS, "-1");
        println(executionResult.getContent());

        println("Done.");
    }
}
