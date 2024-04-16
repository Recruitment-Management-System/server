package com.interviewManagementApplication.RMS.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    public void uploadFile(String filePath, String remoteDirectory) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            ftp.changeWorkingDirectory(remoteDirectory);
            ftp.storeFile(new File(filePath).getName(), inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
