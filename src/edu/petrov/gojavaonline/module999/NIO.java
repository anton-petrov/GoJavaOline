package edu.petrov.gojavaonline.module999;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by anton on 30/03/16.
 */


class NIO {
    public static void watchdir(String dir) {
        Path path = Paths.get(dir);
        WatchService watchService = null;
        try {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // Бесконечный цикл
        for (;;) {
            WatchKey key = null;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Итерации для каждого события
            for (WatchEvent event : key.pollEvents()) {
                switch (event.kind().name()) {
                    case "OVERFLOW":
                        System.out.println("We lost some events");
                        break;
                    case "ENTRY_CREATE":
                        System.out.println("File " + event.context()
                                + " is created!");
                        break;
                    case "ENTRY_MODIFY":
                        System.out.println("File " + event.context()
                                + " is modified!");
                        break;
                    case "ENTRY_DELETE":
                        System.out.println("File " + event.context()
                                + " is deleted!");
                        break;
                }
            }
            // Сброс ключа важен для получения последующих уведомлений
            key.reset();
        }
    }
}