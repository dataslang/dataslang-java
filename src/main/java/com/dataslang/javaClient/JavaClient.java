package com.dataslang.javaClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JavaClient {

    private static String SERVER;

    private static void checkFile(String... strings) throws FileNotFoundException {
        for(String s : strings){
            File file = new File(s);
            if(!file.exists() || file.isDirectory())
                throw new RuntimeException(file.getAbsolutePath() + " (no such file)");
        }
    }

    private static void notNull(Object... objects){
        for(Object o : objects)
            if(o == null)
                throw new RuntimeException("Given Objects cannot be null");
    }

    public static String getServer() {
        return SERVER;
    }

    public static void setServer(String server) {
        SERVER = server;
    }

    /**
     * Turns file into Base64 encoded String, then sends it to the specified server
     *
     * @param filePath path of the file to send
     * @throws FileNotFoundException throws it when file cannot be found
     */
    public static void throwFile(String filePath) throws IOException {

        if(getServer() == null){
            throw new RuntimeException("Server not initialized");
        }

        notNull(filePath);
        checkFile(filePath);

        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);
        byte bytes[] = new byte[(int) file.length()];
        inputStream.read(bytes);

        String data = encodeFile(bytes);

        Client client = Client.create();

        WebResource webResource = client.resource(SERVER);
        //execution query
        ClientResponse response = webResource.queryParam("id", data).accept("application/json").get(ClientResponse.class);

        //if code is not 200, throw exception
        int stat = response.getStatus();
        if (stat != 200) {
            throw new RuntimeException("Errore!\nCod: " + stat);
        }
        System.out.println(response.getEntity(String.class)); //return string

    }

    private static String encodeFile(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    @Deprecated
    //TODO: test this method
    private static byte[] decodeFile(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
}
