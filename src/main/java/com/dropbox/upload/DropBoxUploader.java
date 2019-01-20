package com.dropbox.upload;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DropBoxUploader implements Uploader {
    @Override
    public void upload(String path) {

        String ACCESS_TOKEN = "ZkcJO7ZL-oAAAAAAAAAAdiw4fobckEQH3BkqmuZHG1M3kozNq0HDoRpwE1HR538o";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        try (InputStream in = new FileInputStream(path)) {
            client.files().uploadBuilder("/test.txt").uploadAndFinish(in);
        } catch (DbxException | IOException e) {
            throw new UploadException("Can not upload file"+path,e);
        }
    }

    }

