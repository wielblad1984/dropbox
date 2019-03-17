package com.dropbox.upload;

import com.dropbox.config.ConfigService;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.mail.EmailService;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;

import static com.dropbox.config.Keys.DROPBOX_KEY;

public class DropBoxUploader implements Uploader {
    private final ConfigService cfg;


    public DropBoxUploader(ConfigService cfg) {
        this.cfg = cfg;

    }

    public void upload(String path,String name) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 clientV2 = new DbxClientV2(config, cfg.get(DROPBOX_KEY));

        try (InputStream in = new FileInputStream(path)) {
            clientV2.files().uploadBuilder( "/" +name)
                    .uploadAndFinish(in);

           new EmailService(cfg).sendMail(name);
        } catch (IOException|DbxException e) {
            throw new UploadException("Can not upload file" + path, e);
        } catch (MailjetSocketTimeoutException e) {
            e.printStackTrace();
        } catch (MailjetException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    }

