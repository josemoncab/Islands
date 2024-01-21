package dev.josemc.islands.holograms;

import dev.josemc.islands.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.display.AbstractDisplayMeta;
import net.minestom.server.entity.metadata.display.TextDisplayMeta;

import java.util.ArrayList;
import java.util.UUID;

public class TextHologram implements Hologram {
    private final UUID uuid;
    private final String name;
    private final UUID instance;
    private UUID entityUUID;
    private Pos position;
    private final ArrayList<String> lines = new ArrayList<>();

    public TextHologram(String name, UUID instance, Pos position, String line) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.instance = instance;
        this.position = position;
        this.lines.add(line);
    }

    public void spawn() {
        Entity entity = new Entity(EntityType.TEXT_DISPLAY);
        TextDisplayMeta meta = (TextDisplayMeta) entity.getEntityMeta();
        meta.setTransformationInterpolationDuration(20);
        meta.setHasNoGravity(true);
        meta.setBillboardRenderConstraints(AbstractDisplayMeta.BillboardConstraints.CENTER);
        entity.setInstance(MinecraftServer.getInstanceManager().getInstance(instance), position);
        this.entityUUID = entity.getUuid();
        this.update();
    }

    public void addLine(String line) {
        lines.add("<newline>" + line);
        this.update();
    }

    public void removeLine(int index) {
        lines.remove(index - 1);
        this.update();
    }

    public void modifyLine(int index, String line) {
        lines.remove(index - 1);
        lines.add(index - 1, "<newline>" + line);
        this.update();
    }

    public void move(Pos pos) {
        this.position = pos;
        this.update();
    }

    public void kill() {
        Entity entity = Entity.getEntity(entityUUID);
        entity.remove();
    }

    public void update() {
        Entity entity = Entity.getEntity(entityUUID);
        TextDisplayMeta meta = (TextDisplayMeta) entity.getEntityMeta();
        entity.teleport(position);
        TextComponent.Builder builder = Component.text();
        lines.forEach(l -> builder.append(AdventureUtils.parse(l)));
        meta.setText(builder.build());
    }

    @Override
    public String toString() {
        return String.format("TextHologram[%s]{Name: %s, Position: (%s, %f, %f, %f), Lines: %s}",
                uuid, name, instance, position.x(), position.y(), position.z(), lines);
    }
}
