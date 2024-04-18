package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.util.FTPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService implements Consts {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    @Autowired
    private FTPUtils ftpUtils;

    public void uploadFile(String filePath) {

        try {
            // Check FTP connection before uploading
            if (ftpUtils.connect(server, port, username, password)) {
                // Check if remote directory exists before uploading
                if (ftpUtils.directoryExists(remoteDirectory)) {
                    ftpUtils.uploadFile(filePath, remoteDirectory);
                    ftpUtils.disconnect();
                } else {
                    logger.warn("Remote directory does not exist.");
                }
            } else {
                // Handle connection failure
                logger.warn("Failed to connect to FTP server.");
            }
        } catch (Exception e) {
            // Handle exception appropriately, log or propagate
            logger.warn("Failed to upload file to FTP server.");
            e.printStackTrace();
        }
    }
}
