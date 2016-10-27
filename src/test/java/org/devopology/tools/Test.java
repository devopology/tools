package org.devopology.tools;

public class Test extends Toolset {

    public static void main(String [] args) throws Exception {
        new Test().run(args);
    }

    public void run(String [] args) throws Exception {
        println("Let's go ...");
        println("pwd = [" + pwd() + "]");
        println("Done.");
    }
}
