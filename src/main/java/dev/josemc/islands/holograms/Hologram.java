package dev.josemc.islands.holograms;

import net.minestom.server.coordinate.Pos;

public interface Hologram {
    void spawn();

    void move(Pos pos);

    void kill();

    void update();

}

