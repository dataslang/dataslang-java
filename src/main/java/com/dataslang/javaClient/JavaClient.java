package com.dataslang.javaClient;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileNotFoundException;

public class JavaClient {

    private static final String SERVER = "http://api.dataslang";
    private static final String PATH_VALIDATE = "/xml/validate";

    public static void xmlValidate(String xml, String xsd) throws FileNotFoundException {

        File xmlFile = new File(xml);
        File xsdFile = new File(xml);

        checkFile(xmlFile, xsdFile);

        Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        WebTarget webTarget = client.target(SERVER).path(PATH_VALIDATE);
        MultiPart multiPart = new MultiPart();
        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

        FileDataBodyPart bodyxml = new FileDataBodyPart("xml", xmlFile, MediaType.APPLICATION_OCTET_STREAM_TYPE);
        FileDataBodyPart bodyxsd = new FileDataBodyPart("xsd", xsdFile, MediaType.APPLICATION_OCTET_STREAM_TYPE);

        multiPart.bodyPart(bodyxml);
        multiPart.bodyPart(bodyxsd);

    }

    private static void checkFile(File... files) throws FileNotFoundException {
        for(File f : files){
            if(!f.exists())
                throw new FileNotFoundException(f.getAbsolutePath() + " (no such file)");
        }
    }
}
