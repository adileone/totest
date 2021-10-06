package it.integration.db2csv.storage;

public interface GCSStorage {
	public void send(String filePath,String blobName) throws Exception;
}
