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
        pwd();

        if (DIRECTORY == type("C:/temp/ROOT/opt/company")) {
            mv("C:/temp/ROOT/opt/company", "C:/temp/ROOT/opt/company" + "-" + System.currentTimeMillis());
        }

        pwd();
        mkdirs("C:/temp/ROOT/opt/company");
        typeString("C:/temp/ROOT/opt/company");
        cd("C:/temp/ROOT/opt/company");

        //unzipFile("C:/temp/somezipfile.zip", "C:/temp/ROOT/opt/company");

        //Properties properties = loadProperties("C:/temp/ROOT/opt/company/project/configuration.properties");
        //replaceProperties(properties, "C:/temp/ROOT/opt/company/project/document.template", "C:/temp/ROOT/opt/company/project/document.sample");

        mkdirs("C:/temp/ROOT/lib/systemd/system");
        //copyFile("C:/temp/ROOT/opt/company/project/document.sample", "C:/temp/ROOT/lib/systemd/system/document.sample");

        List<String> argumentList = new ArrayList();
        argumentList.add("-h");
        String output = execute("C:/ffmpeg.exe", argumentList);
        //println(output);

        rm("C:/temp/ROOT", false);

        output = execute("C:/ffmpeg.exe", new String [] { "-h" });
        println(output);
        println("Done.");
    }
}
