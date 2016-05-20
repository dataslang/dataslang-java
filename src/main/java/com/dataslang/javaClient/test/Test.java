package com.dataslang.javaClient.test;

import com.dataslang.javaClient.JavaClient;

import java.io.FileNotFoundException;

public class Test {

    public static void main(String[] args){
        try {
            JavaClient.xmlValidate("src/main/resources/xml/output.xml", "src/main/resources/xml/fatturapa_v1.1.xsd");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
