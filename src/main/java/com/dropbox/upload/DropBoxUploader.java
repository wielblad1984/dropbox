package com.dropbox.upload;

import com.dropbox.config.ConfigService;
import com.dropbox.config.Keys;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.mail.Email;
import com.dropbox.mail.EmailClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DropBoxUploader implements Uploader {
    private final ConfigService cfg;
    private final EmailClient client;

    public DropBoxUploader(ConfigService cfg,EmailClient client){

        this.cfg=cfg;
        this.client=client;
    }
    @Override
    public void upload(String path,String name) {
        String token = cfg.get(Keys.DROPBOX_KEY);
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 clientV2 = new DbxClientV2(config, token);

        try (InputStream in = new FileInputStream(path)) {
            clientV2.files().uploadBuilder( "/" +name)
                    .uploadAndFinish(in);

            String content=String.format(cfg.get(Keys.EMAIL_CONTENT),name);
            client.send(new Email(cfg.get(Keys.EMAIL_SUBJECT),content,cfg.get(Keys.EMAIL_TO)));
        } catch (IOException|DbxException e) {
            throw new UploadException("Can not upload file"+path,e);
        }
    }

    }

