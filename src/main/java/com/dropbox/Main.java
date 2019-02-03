package com.dropbox;

import com.dropbox.config.ConfigService;
import com.dropbox.core.util.StringUtil;
import com.dropbox.listener.DirectoryListener;
import com.dropbox.mail.ClientProvider;
import com.dropbox.upload.DropBoxUploader;
import com.dropbox.upload.Uploader;

import java.io.IOException;

public class Main {
    private static final int PROPERTIES_INDEX = 0;

    public static void main(String[] args) throws IOException {

        String propsPath = args[PROPERTIES_INDEX];
        ConfigService cfg = new ConfigService(propsPath).load();
        ClientProvider cp=new ClientProvider(cfg);
        DropBoxUploader dropBoxUploader = new DropBoxUploader(cfg,cp.create());
        DirectoryListener directoryListener = new DirectoryListener(dropBoxUploader, cfg);
        directoryListener.listen();



    }


}

