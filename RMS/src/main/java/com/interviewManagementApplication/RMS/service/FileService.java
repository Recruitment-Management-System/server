package com.interviewManagementApplication.RMS.service;

import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.util.FTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

@Slf4j
@Service
public class FileService implements Consts{

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    @Autowired
    private FTPUtils ftpUtils;

    public void uploadFile(InputStream inputStream,String filePath) {


        try {
            // Check FTP connection before uploading
            if (ftpUtils.connect(SERVER, port, USERNAME, PASSWORD)) {
                // Check if remote directory exists before uploading
                if (ftpUtils.directoryExists(REMOTE_DIRECTORY)) {
                    ftpUtils.uploadFile(inputStream, filePath, REMOTE_DIRECTORY);
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

    public byte[] getCVFile(String filePath) {
        try {
            if (ftpUtils.connect(SERVER, port, USERNAME, PASSWORD)) {
                return ftpUtils.downloadFile(filePath);
            } else {
                logger.warn("Failed to connect to FTP server.");
                return null;
            }
        } catch (Exception e) {
            logger.warn("Failed to retrieve CV file from FTP server.");
            e.printStackTrace();
            return null;
        } finally {
            ftpUtils.disconnect();
        }
    }


}
