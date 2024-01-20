package dev.josemc.islands.utils;

import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.ArrayList;
import java.util.List;

public class AdventureUtils {
    public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static ComponentLike parse(String message, TagResolver... resolvers) {
        ArrayList<TagResolver> tagResolvers = new ArrayList<>(List.of(resolvers));
        tagResolvers.add(TagResolver.standard());
        return MINI_MESSAGE.deserialize(message, TagResolver.resolver(tagResolvers));
    }

    public static TagResolver tagResolver(String tag, String msg) {
        return TagResolver.resolver(tag, Tag.selfClosingInserting(AdventureUtils.MINI_MESSAGE.deserialize(msg)));
    }
}
