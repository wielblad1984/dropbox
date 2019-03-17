package com.dropbox.listener;

import com.dropbox.config.ConfigService;
import com.dropbox.upload.DropBoxUploader;
import com.dropbox.upload.Uploader;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.dropbox.config.Keys.DIRECTORY;

public class DirectoryListener  {
    private final DropBoxUploader dropBoxUploader;
    private final String dir;


    public DirectoryListener(String dir, DropBoxUploader dropBoxUploader) {
        this.dropBoxUploader = dropBoxUploader;
        this.dir=dir;
    }

    public void listen() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(dir);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                final String name =  key.pollEvents().get(0).context().toString();
                System.out.println(name);


                dropBoxUploader.upload(dir + name, name);
                key.reset();
            }
        }catch (IOException|InterruptedException e){
            throw new ListenerException("Can not listen to directory"+ dir,e);
        }

    }
}
