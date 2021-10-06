package it.integration.db2csv.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.extern.slf4j.Slf4j;

@Profile("!gcs-dummy")
@Component
@Slf4j
public class GoogleCloudStorage implements GCSStorage{
    private Storage storage;
    private Bucket bucket;
    
    @Value("${gsutil.projectId}")
    private String projectId;
    @Value("${gsutil.bucketname}")
    private String bucketname;
    
    @PostConstruct
    private void setup() throws IOException {
    	createGoogleCloudStorage(projectId,bucketname);
    }
    public void send(String filePath,String blobName) throws Exception {
    	InputStream content = FileUtils.openInputStream(new File(filePath));
    	Blob blob = bucket.create(blobName, content, "text/plain");
    	IOUtils.closeQuietly(content); 
    	log.debug("file "+blobName+" inviato");
    }

    // Use path and project name
    private void createGoogleCloudStorage(String projectId, String bucketname) throws IOException {
    	Credentials credentials = GoogleCredentials.getApplicationDefault();
        storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
        bucket = storage.get(bucketname);
    }

}
