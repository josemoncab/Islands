package dev.josemc.islands.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;

public class Dimensions {
    public static final DimensionType TEST = DimensionType.builder(NamespaceID.from("islands:test_dim"))
            .ambientLight(2.0F)
            .skylightEnabled(true)
            .minY(-80)
            .height(416)
            .fixedTime(1000L)
            .build();

    static {
        MinecraftServer.getDimensionTypeManager().addDimension(TEST);
    }
}
