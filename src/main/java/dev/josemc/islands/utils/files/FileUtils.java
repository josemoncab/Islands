package dev.josemc.islands.utils.files;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileUtils {
    public static ArrayList<String> getFromJar(String filter) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Enumeration<JarEntry> entries = new JarFile(FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile()).entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(filter)) {
                    list.add(entry.getName());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
