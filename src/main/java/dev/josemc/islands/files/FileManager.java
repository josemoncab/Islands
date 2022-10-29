package dev.josemc.islands.files;

import dev.josemc.islands.Islands;
import dev.josemc.islands.utils.files.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class FileManager {
    private final Islands instance = Islands.get();
    public FileManager() {
        init();
    }

    private void init() {
        FileUtils.getFromJar(".json").forEach(file -> {
            Path path = Path.of(instance.getDataFolder().getPath(), file.split("files")[1]);
            System.out.println(file);
            File f = new File(path.toUri());
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                try {
                    Files.copy(FileUtils.class.getResourceAsStream("/" + file), path, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
