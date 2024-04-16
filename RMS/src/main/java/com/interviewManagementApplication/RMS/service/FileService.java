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
        int port = 21;
        String username = "kmedagoda";
        String password = "20001211";
        String remoteDirectory = "/home/kmedagoda/Desktop";

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
