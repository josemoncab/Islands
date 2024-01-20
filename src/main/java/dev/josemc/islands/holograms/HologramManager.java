package dev.josemc.islands.holograms;

import net.minestom.server.coordinate.Pos;

import java.util.HashMap;
import java.util.UUID;

public class HologramManager {

    public static HashMap<String, Hologram> hologramCache = new HashMap<>();

    public static void newTextHologram(String name, UUID instance, Pos pos, String line) {
        TextHologram hologram = new TextHologram(name, instance, pos, line);
        hologram.spawn();
        hologramCache.put(name, hologram);
    }
}
