package com.interviewManagementApplication.RMS.util;


import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

@Component
public class FTPUtils {

    private FTPClient ftp;

    public boolean connect(String server, int port, String username, String password) {
        ftp = new FTPClient();
        try {
            ftp.connect(server, port);
            return ftp.login(username, password);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        if (ftp != null && ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFile(InputStream inputStream, String filePath, String remoteDirectory) {
        try {
            // Check if FTP connection is established
            if (ftp.isConnected()) {
                // Change working directory
                ftp.changeWorkingDirectory(remoteDirectory);

                // Store file with the unique filename
                boolean uploaded = ftp.storeFile(filePath, inputStream);

                if (uploaded) {
                    System.out.println("File uploaded successfully as: " + filePath);
                } else {
                    System.out.println("Failed to upload file");
                }
            } else {
                System.out.println("FTP connection is not established");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateUniqueFilename(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        return uuid + extension;
    }

    public boolean directoryExists(String directoryPath) {
        boolean exists = false;
        try {
            exists = ftp.changeWorkingDirectory(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public byte[] downloadFile(String remoteFilePath) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            boolean success = ftp.retrieveFile(remoteFilePath, outputStream);
            if (success) {
                return outputStream.toByteArray();
            } else {
                //logger.error("Failed to download file from FTP server: {}", remoteFilePath);
                return null;
            }
        } catch (IOException e) {
           // logger.error("Error downloading file from FTP server: {}", e.getMessage());
            return null;
        }
    }
}
