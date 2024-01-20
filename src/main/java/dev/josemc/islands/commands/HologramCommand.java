package dev.josemc.islands.commands;

import dev.josemc.islands.holograms.HologramManager;
import dev.josemc.islands.holograms.TextHologram;
import dev.josemc.islands.utils.AdventureUtils;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class HologramCommand extends Command {

    public HologramCommand() {
        super("texthologram");

        addSyntax(this::spawnText, ArgumentType.String("name"), ArgumentType.String("text"));
        addSyntax(this::addLine, ArgumentType.Literal("addline"), ArgumentType.Word("hologramName"), ArgumentType.String("text"));
        addSyntax(this::removeLine, ArgumentType.Literal("removeline"), ArgumentType.Word("hologramName"), ArgumentType.Integer("line"));
    }

    public void spawnText(CommandSender sender, CommandContext arguments) {
        if (!(sender instanceof Player player))
            return;

        String line = arguments.get("text");

        HologramManager.newTextHologram(arguments.get("name"), player.getInstance().getUniqueId(), player.getPosition(), line);
    }

    public void addLine(CommandSender sender, CommandContext arguments) {
        if (!(sender instanceof Player player))
            return;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        String line = arguments.get("text");

        TextHologram hologram = (TextHologram) HologramManager.hologramCache.get(arguments.get("hologramName"));
        hologram.addLine(line);
    }

    public void removeLine(CommandSender sender, CommandContext arguments) {
        if (!(sender instanceof Player player))
            return;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        int line = arguments.get("line");

        TextHologram hologram = (TextHologram) HologramManager.hologramCache.get(arguments.get("hologramName"));
        hologram.removeLine(line);
    }
}
