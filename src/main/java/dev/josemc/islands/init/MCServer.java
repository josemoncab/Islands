package dev.josemc.islands.init;

import ch.qos.logback.classic.Logger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.MinestomAdventure;
import org.slf4j.LoggerFactory;

public class MCServer {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MCServer.class);

    public MCServer() {
        long initTime = System.nanoTime();
        LOGGER.info("Initializing minecraft server...");

        MinecraftServer minecraftServer = MinecraftServer.init();

        MinestomAdventure.AUTOMATIC_COMPONENT_TRANSLATION = true;
        MinestomAdventure.COMPONENT_TRANSLATOR = (component, locale) -> component;

        MinecraftServer.getSchedulerManager().buildShutdownTask(this::onStop);
        minecraftServer.start("0.0.0.0", 25565);

        LOGGER.info(String.format("Server Started in %.2fs", (System.nanoTime() - initTime) / 1000000000f));
    }

    private void onStop() {
        MinecraftServer.stopCleanly();
    }
}
