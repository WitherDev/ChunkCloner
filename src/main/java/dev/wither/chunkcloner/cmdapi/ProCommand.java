package dev.wither.chunkcloner.cmdapi;

import dev.wither.chunkcloner.ChunkCloner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class ProCommand implements CommandExecutor {

    CommandData data;

    public ProCommand() {

        data = this.getClass().getDeclaredAnnotation(CommandData.class);
        Objects.requireNonNull(data, "&c@CommandData annotation missing on " + this.getClass().getSimpleName() +  ", command will not work.");

        ChunkCloner.getPlugin().getCommand(data.name()).setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!data.name().equalsIgnoreCase(cmd.getName())) return true;

        if (!data.enabled()) {

            sender.sendMessage(data.disabledMessage());
            return true;

        }
        if (!data.console() && !(sender instanceof Player)) {

            sender.sendMessage(data.consoleMessage());
            return true;

        }
        if (!sender.hasPermission(data.permission())) {

            sender.sendMessage(data.permissionMessage());
            return true;

        }

        execute(sender, args);

        return true;

    }

    public abstract void execute(CommandSender sender, String[] input);

}
