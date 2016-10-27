![devopology](https://raw.githubusercontent.com/devopology/tools/develop/devopology.png)

master  
[![Build Status](https://travis-ci.org/devopology/tools.svg?branch=master)](https://travis-ci.org/devopology/tools)

develop  
[![Build Status](https://travis-ci.org/devopology/tools.svg?branch=develop)](https://travis-ci.org/devopology/tools)

# Background

Over the course of ~20 years, dealing with various Java software applications, Java application server platforms, Linux, Windows, BASH shell scripts, batch files, Jython, JACL, XML, Ant, etc. I came to the realization that ...

1. Dev-ops scripts should be easy to implement and be easily extensible
2. Typical scripting languages used in dev-ops just obfuscate one language into another
3. You must master a new scripting language to become efficient using it
3. Java code (for a Java shop) is easy to develop, debug, deploy, etc.

With those "truths" (... at least our my minds ...) we set out to develop code to wrap a lot of common
functionality that would allow easy dev-op script functions.

For example, the devopology toolset allows easy use of file operations (cp, mv, rmdir), JSON based processing, Properties based processing, Apache Commons Lang, Apache HttpClient, Apache Commons IO, and Apache Commons Exec ... plus any other Java functionality you require because you can easily write it in Java.

Need custom code to call a REST API ? ... easy ... just use your existing client API / implement it in Java.

One unique feature is that the Toolset class has a concept of a "current directory" which is based on the Java concept of current directory when the class is executed.  This allows functionality such as ...

    package org.devopology.tools.test;
    
    import org.devopology.tools.ExecutionResult;
    import org.devopology.tools.Toolset;
    
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

Using Linux ...

1. Setup a Java 1.7 JDK
2. Setup Maven
3. Clone the GIT repository
4. From the project root, build the code using "mvn install"

# UBER jar

The project includes a "packager" that will build an UBER jar (jar with all dependencies - minus those used for testing.) The Packager class is part of the source code, so it's a good example of how to do things.

Using Linux ...

1. Build the project (steps above)
5. From the project root, build the UBER jar using "bash -e package-UBER.sh"

The resulting UBER jar will be located in target directory. 

# Using

You will need the output tools-toolset-1.0.0.jar along with with the other dependency jars in the lib directory.ipt" source file will be located.

Create a new Java class called MyScript in MyScript.java that extends Toolset ...

    import org.devopology.tools.*;
    // other imports depending on what you need

    public class MyScript extends Toolset {
        public static void main(String [] args) throws Exception {
            new MyScript().run(args);
        }
    
        public void run(String [] args) throws Exception {
            // Your code here
        }
    }

Compile the class using  javac -cp "*" MyScript.java

Run the MyScript class using  java -cp "*:." MyScript

# Caveats

The biggest caveat is the lack of of shell wildcard expansion.

For example ... 

    <snip>
    
    execute(CP, "*", "/tmp");
    
    </snip>

Doesn't work because the Linux shell expands the wildcards. (Windows is a different, but we currently aren't targeting Windows.)

Even ...

    <snip>
    
    execute(CP, "/tmp/somefile", ".");
    
    </snip>

... will have problems, because the executable's concept of the current directory isn't the same as the Toolset's.

# Workarounds

You can workaround the lack of shell expansion by using the "find" command ...

        <snip>

        ExecutionResult result = execute("/usr/bin/find", absolutePath("../git"), "-maxdepth", "10", "-name", "'*.java'");
        List<String> list = stringToList(result.getContent());
        for (String string : list) {
            string = absoluteFile(string).getCanonicalPath();
            println("absolutePath -> " + string);
        }
        
        </snip>

Another workaround is to use pure Java code to list files, then convert to them to absolute paths.

For relative directories, without wildcards, the "absolutePath" method can be used

For example ...

    <snip>
    
    // absolutePath with convert the path to absolute
    // based on the Toolset current working directory
    execute(CP, "/tmp/somefile", absolutePath("."));
    
    </snip>

# Beyond

Want more functionality than what's included ? ... Add new dependencies to the pom.xml file and then extend the Toolset class to add the new functionality ...

    import org.devopology.tools.*;
    // other imports depending on what you need

    public class MyToolset extends Toolset {
        public void scp(String path, String remotePath) throws Exception {
            // Add code to perform SCP
    }

