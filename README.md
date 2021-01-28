# This App Service example will download a file from Azure blob storage using **User** Assigned Managed Identity and Azure blob storage Java SDK.


# Pre-requisites
Storage account created (Gen purpose v2)

Container created in the storage account

Sample file present in the container for download

User Assigned Managed Identity created

https://docs.microsoft.com/en-us/azure/active-directory/managed-identities-azure-resources/how-to-manage-ua-identity-portal#create-a-user-assigned-managed-identity

# Clone and update files


## In pom.xml:

Update values of resource group and appname (search &quot;changeThis&quot; in the file to find the update spots)


## In /src/main/java/com/example/springboot/HelloController.java :

Update mystorage1 to your storage account name

String endpoint = String.format(Locale.ROOT, &quot;https://%s.blob.core.windows.net&quot;, &quot;myteststorage1&quot;);

Update the cliend id of your User Managed Identity in this line:

.credential(new DefaultAzureCredentialBuilder().managedIdentityClientId("changeThis").build())

Update container name and file name to download

mvn clean package -DskipTests


# Deploy webapp

mvn azure-webapp:deploy


# In storage account

Add Roles following roles for Storage account to your User assigned Managed Idnetity:

Contributor

Storage Blob Data Contributor

Storage Queue Data Contributor

# Access your app in browser and check the output
