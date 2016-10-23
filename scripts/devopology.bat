@echo off
javac -cp org.devopology.tools-toolset-1.0.0.jar %1.java
java -cp org.devopology.tools-toolset-1.0.0.jar;. %1