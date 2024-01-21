package dev.josemc.islands.init;

import ch.qos.logback.classic.Logger;
import dev.josemc.islands.commands.TextHologramCommand;
import dev.josemc.islands.events.EventManager;
import dev.josemc.islands.world.Instances;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.MinestomAdventure;
import net.minestom.server.command.CommandManager;
import net.minestom.server.extras.MojangAuth;
import org.slf4j.LoggerFactory;

public class MCServer {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MCServer.class);

    public MCServer() {
        long initTime = System.nanoTime();
        LOGGER.info("Initializing minecraft server...");

        MinecraftServer minecraftServer = MinecraftServer.init();

        MinestomAdventure.AUTOMATIC_COMPONENT_TRANSLATION = true;
        MinestomAdventure.COMPONENT_TRANSLATOR = (component, locale) -> component;

        MinecraftServer.getSchedulerManager().buildShutdownTask(MCServer::onStop);

        new Instances();
        EventManager.init();

        registerCommands();

        MojangAuth.init();

        minecraftServer.start("0.0.0.0", 25565);

        LOGGER.info(String.format("Server Started in %.2fs", (System.nanoTime() - initTime) / 1000000000f));
    }

    public static void onStop() {
        MinecraftServer.stopCleanly();
    }

    private void registerCommands() {
        CommandManager commandManager = MinecraftServer.getCommandManager();
        commandManager.register(new TextHologramCommand());
    }
}
