package it.integration.db2csv.storage;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Profile("gcs-dummy")
@Component
@Slf4j
public class GoogleCloudStorageDummy implements GCSStorage {

    public void send(String filePath,String blobName) throws Exception {
 
    	log.debug("invio dummy");
    }

}
