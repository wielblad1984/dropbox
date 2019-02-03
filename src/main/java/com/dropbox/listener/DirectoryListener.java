package com.dropbox.listener;

import com.dropbox.config.ConfigService;
import com.dropbox.upload.DropBoxUploader;
import com.dropbox.upload.Uploader;

import java.io.IOException;
import java.nio.file.*;

import static com.dropbox.config.Keys.DIRECTORY;

public class DirectoryListener {
    private final Uploader uploader;
    private final String dir;


    public DirectoryListener(Uploader uploader,ConfigService cfg) {
        this.uploader = uploader;
        this.dir=cfg.get(DIRECTORY);
    }

    public void listen() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(dir);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                String name =  key.pollEvents().get(0).context().toString();
                System.out.println(name);
                uploader.upload(dir + name, name);
                key.reset();
            }
        }catch (IOException|InterruptedException e){
            throw new ListenerException("Can not listen to directory"+ dir,e);
        }

    }
}
