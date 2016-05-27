package com.dataslang.javaClient;

import org.junit.Before;
import org.junit.Test;

public class JavaClientTest {

    @Before
    public void initializeServer(){
        JavaClient.setServer("https://api.echo.nasa.gov/echo-rest/users");
    }

    @Test(expected = RuntimeException.class)
    public void testLaunchFile() throws Exception {
        JavaClient.throwFile("src/main/resources/xml/output.xml");
    }

    @Test(expected = RuntimeException.class)
    public void testThrowFileNullServer() throws Exception{
        JavaClient.setServer(null);
        JavaClient.throwFile("src/main/resources/xml/output.xml");
    }

    @Test(expected = RuntimeException.class)
    public void testThrowFileNullFile() throws Exception{
        JavaClient.throwFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowFileWrongFile() throws Exception{
        JavaClient.throwFile("src/main/resources/");
    }

}