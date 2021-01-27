package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.azure.storage.blob.*;
import com.azure.identity.DefaultAzureCredentialBuilder;
import java.util.Locale;
import java.io.File;
import java.io.FileInputStream;


@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
           String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", "myteststorage1");
           BlobServiceClient storageClient = new BlobServiceClientBuilder()
              .endpoint(endpoint)
              .credential(new DefaultAzureCredentialBuilder().managedIdentityClientId("changeThis").build())
              .buildClient();
           //String containerName = "quickstartblobs" + java.util.UUID.randomUUID();
           //BlobContainerClient containerClient = storageClient.createBlobContainer(containerName);
           //BlobClient blobClient = containerClient.getBlobClient("testfile");
           //blobClient.uploadFromFile("/home/site/wwwroot/testfile");
           BlobClient blobClient =  storageClient.getBlobContainerClient("quickstartblobs1")
                .getBlobClient("curl-7.61.1-r3.apk");
           String localPath = "/home/site/wwwroot/data/";
           String downloadFileName = localPath+java.util.UUID.randomUUID()+"-userIdentity-curl-7.61.1-r3.apk";
           File downloadedFile = new File(localPath + downloadFileName);
           blobClient.downloadToFile(downloadFileName);

           return "File "+downloadFileName+" downloaded successfully";
        }
}
