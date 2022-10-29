package dev.josemc.islands.utils.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import dev.josemc.islands.Islands;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Json {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Islands instance = Islands.get();

    public static void write(@NotNull String fileName, @NotNull Object object) {
        Path path = Paths.get(instance.getDataFolder() + "/" + fileName + ".json");
        try {
            if(!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            GSON.toJson(object, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T read(@NotNull String fileName, @NotNull Type type) {
        Path path = Paths.get(instance.getDataFolder() + "/" + fileName + ".json");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            JsonReader jsonReader = new JsonReader(bufferedReader);
            return GSON.fromJson(jsonReader, type);
        } catch (IOException e) {
            return null;
        }
    }
}
