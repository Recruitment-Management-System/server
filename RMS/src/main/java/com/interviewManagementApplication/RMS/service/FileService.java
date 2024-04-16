package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.util.FTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FTPUtils ftpUtils;

    public void uploadFile(String filePath) {
        String server = "localhost";
        int port = 22;
        String username = "hsudusinghe";
        String password = "HansakaJayawarna123@";
        String remoteDirectory = "/home/hsudusinghe/Downloads/Sample";

        // ftpUtils.uploadFile(server, port, username, password, filePath, remoteDirectory);


        try {
            // Check FTP connection before uploading
            if (ftpUtils.connect(server, port, username, password)) {
                ftpUtils.uploadFile(filePath, remoteDirectory);
                ftpUtils.disconnect();
            } else {
                // Handle connection failure
                System.err.println("Failed to connect to FTP server.");
            }
        } catch (Exception e) {
            // Handle exception appropriately, log or propagate
            System.out.println("failed ------------------------------------------");
            e.printStackTrace();
        }
    }
}
