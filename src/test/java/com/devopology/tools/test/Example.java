package com.devopology.tools.test;

import com.devopology.tools.BaseToolset;

import java.util.ArrayList;
import java.util.List;

public class Example extends BaseToolset {
    public static void main(String [] args) throws Exception {
        new Example().run(args);
    }

    public void run(String [] args) throws Exception {
        println("Let's go ...");

        //println("C:/temp/ROOT/opt/company" + " type = " + type("C:/temp/ROOT/opt/company"));

        if (TYPE_DIRECTORY == type("C:/temp/ROOT/opt/company")) {
            renameDirectory("C:/temp/ROOT/opt/company", "C:/temp/ROOT/opt/company" + "-" + System.currentTimeMillis());
        }

        deleteDirectory("C:/temp/ROOT/opt/company");
        createDirectory("C:/temp/ROOT/opt/company");
        //unzipFile("C:/temp/somezipfile.zip", "C:/temp/ROOT/opt/company");

        //Properties properties = loadProperties("C:/temp/ROOT/opt/company/project/configuration.properties");
        //replaceProperties(properties, "C:/temp/ROOT/opt/company/project/document.template", "C:/temp/ROOT/opt/company/project/document.sample");

        createDirectory("C:/temp/ROOT/lib/systemd/system");
        //copyFile("C:/temp/ROOT/opt/company/project/document.sample", "C:/temp/ROOT/lib/systemd/system/document.sample");

        List<String> argumentList = new ArrayList();
        argumentList.add("-h");
        String output = execute("C:/ffmpeg.exe", argumentList);
        //println(output);

        output = execute("C:/ffmpeg.exe", new String [] { "-h" });
        println(output);
        println("Done.");
    }
}
