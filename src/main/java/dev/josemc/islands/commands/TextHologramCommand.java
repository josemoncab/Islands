package dev.josemc.islands.commands;

import dev.josemc.islands.holograms.HologramManager;
import dev.josemc.islands.holograms.TextHologram;
import dev.josemc.islands.utils.AdventureUtils;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.Player;

// TODO: Improve the code and command syntax
public class TextHologramCommand extends Command {

    public TextHologramCommand() {
        super("texthologram");

        setCondition(Conditions::playerOnly);

        addSyntax(this::newHologram, ArgumentType.Literal("new"), ArgumentType.Word("hologramName"), ArgumentType.String("text"));
        addSyntax(this::addLine, ArgumentType.Literal("addLine"), ArgumentType.Word("hologramName"), ArgumentType.String("text"));
        addSyntax(this::removeLine, ArgumentType.Literal("removeLine"), ArgumentType.Word("hologramName"), ArgumentType.Integer("lineNumber"));
        addSyntax(this::editLine, ArgumentType.Literal("editLine"), ArgumentType.Word("hologramName"), ArgumentType.Integer("lineNumber"), ArgumentType.String("text"));
        addSyntax(this::remove, ArgumentType.Literal("remove"), ArgumentType.Word("hologramName"));
        addSyntax(this::move, ArgumentType.Literal("moveHere"), ArgumentType.Word("hologramName"));
    }

    private void newHologram(CommandSender sender, CommandContext arguments) {
        Player player = (Player) sender;

        String line = arguments.get("text");

        HologramManager.newHologram(arguments.get("hologramName"), player.getInstance().getUniqueId(), player.getPosition(), line);
    }

    private void addLine(CommandSender sender, CommandContext arguments) {
        Player player = (Player) sender;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        String line = arguments.get("text");

        TextHologram hologram = (TextHologram) HologramManager.hologramCache.get(arguments.get("hologramName"));
        hologram.addLine(line);
    }

    private void removeLine(CommandSender sender, CommandContext arguments) {
        Player player = (Player) sender;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        int line = arguments.get("lineNumber");

        TextHologram hologram = (TextHologram) HologramManager.hologramCache.get(arguments.get("hologramName"));
        hologram.removeLine(line);
    }

    private void editLine(CommandSender sender, CommandContext arguments) {
        Player player = (Player) sender;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        int line = arguments.get("lineNumber");
        String text = arguments.get("text");

        TextHologram hologram = (TextHologram) HologramManager.hologramCache.get(arguments.get("hologramName"));
        hologram.modifyLine(line, text);
    }

    private void remove(CommandSender sender, CommandContext arguments) {
        Player player = (Player) sender;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        HologramManager.remove(arguments.get("hologramName"));
    }

    private void move(CommandSender sender, CommandContext arguments) {
        Player player = (Player) sender;

        if (!HologramManager.hologramCache.containsKey(arguments.get("hologramName"))) {
            player.sendMessage(AdventureUtils.parse("Hologram not exist"));
            return;
        }

        HologramManager.hologramCache.get(arguments.get("hologramName")).move(player.getPosition());
    }
}
