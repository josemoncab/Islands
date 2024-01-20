package dev.josemc.islands.events;

import dev.josemc.islands.world.Instances;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

// TODO: Make this dynamic. Load events from classes
public class EventManager {
    public static void init() {
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

        loadEvents(eventHandler);
    }

    private static void loadEvents(GlobalEventHandler eventHandler) {
        EventNode<Event> BASIC_NODE = EventNode.all("islands:basic_node")
                .addListener(AsyncPlayerConfigurationEvent.class, event -> {
                    Player player = event.getPlayer();
                    event.setSpawningInstance(Instances.TEST);
                    player.setRespawnPoint(new Pos(0, 20f, 0));
                })
                .addListener(PlayerSpawnEvent.class, event -> {
                    event.getPlayer().setGameMode(GameMode.CREATIVE);
                });

        eventHandler.addChild(BASIC_NODE);
    }
}
