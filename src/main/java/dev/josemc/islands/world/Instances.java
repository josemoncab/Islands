package dev.josemc.islands.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;

public class Instances {
    public static InstanceContainer TEST;

    private final InstanceManager instanceManager;

    public Instances() {
        instanceManager = MinecraftServer.getInstanceManager();

        TEST = instanceManager.createInstanceContainer(Dimensions.TEST);
        TEST.setChunkSupplier(LightingChunk::new);
        TEST.setGenerator(generationUnit -> generationUnit.modifier().fillHeight(0, 20, Block.STONE));
    }
}
