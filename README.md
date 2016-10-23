![devOPology](https://raw.githubusercontent.com/devopology/tools/develop/devopology.png)

master  
[![Build Status](https://travis-ci.org/devopology/tools.svg?branch=master)](https://travis-ci.org/devopology/tools)

develop  
[![Build Status](https://travis-ci.org/devopology/tools.svg?branch=develop)](https://travis-ci.org/devopology/tools)

# Background

Over the course of ~20 years, dealing with various Java software applications, Java application server platforms, Linux, Windows, BASH shell scripts, batch files, Jython, JACL, XML, Ant, etc. I came to the realization that ...

1. Dev-ops scripts should be easy to implement and provide extensible functionality
2. Typical scripting languages used in dev-ops just obfuscate a lot of functionality
3. Typical scripting languages used in dev-ops require yet more knowledge
3. Java code (for a Java shop) is easy to develop, debug, deploy, etc.

With those "truths" (... at least in my mind ...) I set out to develop code to wrap a lot of common
functionality that would allow easy dev-op script functions.  For example, the devopology toolset allows easy use of
file operations (cp, mv, rmdir), JSON based processing, Properties based processing, Apache Commons Lang, Apache HttpClient, Apache Commons IO, and Apache Commons Exec ... plus any other Java functionality you require because you can easily write it in Java.  Need custom code to call a REST API ? ... easy ... just use your existing client API / implement it in Java.

One unique feature is that the Toolset class has a concept of a "current directory" which is based on the Java concept of current directory when the class is executed.  This allows functionality such as

    package com.devopology.tools.test;
    
    import com.devopology.tools.ExecutionResult;
    import com.devopology.tools.Toolset;
    
    import java.util.ArrayList;
    import java.util.List;
    
    public class Example extends Toolset {
        public static void main(String [] args) throws Exception {
            new Example().run(args);
        }
    
        public void run(String [] args) throws Exception {
            println("Let's go ...");
            
            // Java's current directory
            println("currentDirectory is " + pwd());
            
            cd("/");
            
            // The root directory
            println("currentDirectory is " + pwd());
        }

# Building

To build ..

1. Setup a Java 1.8 JDK
2. Setup Maven
3. Clone the GIT respository
4. Build the code using "mvn install"

# Using

Copy the combined output jar (which contains the devopology Toolset class along with the other dependencies)
located at ${project.root}/target/org.devopology.tools-toolset-1.0.0.jar to the location of where your Java "script" source file will be located.

Create a new Java class called MyScript in MyScript.java that extends Toolset ...

    import com.devopology.tools.*;
    // other imports depending on what you need

    public class MyScript extends Toolset {
        public static void main(String [] args) throws Exception {
            new MyScript().run(args);
        }
    
        public void run(String [] args) throws Exception {
            // Your code here
        }
    }

Compile the class using "javac -cp org.devopology.tools-toolset-1.0.0.jar MyScript.java"

Run the MyScript class using "java -cp org.devopology.tools-toolset-1.0.0.jar;. MyScript"

# Beyond

Want more functionality than what's included ? ... Add new depdencies jars to the pom.xml file and then extend the Toolset class to add the new functionality ...

    import com.devopology.tools.*;
    // other imports depending on what you need

    public class MyToolset extends Toolset {
        public void scp(String path, String remotePath) throws Exception {
            // Add code to perform SCP
    }

