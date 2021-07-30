package dev.wither.chunkcloner.core;

import dev.wither.chunkcloner.ChunkCloner;
import dev.wither.chunkcloner.cmdapi.CommandData;
import dev.wither.chunkcloner.cmdapi.ProCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandData(name = "chunkcloner", permission = "chunkcloner.use", console = true)
public class ChunkChangerCommand extends ProCommand {

    @Override
    public void execute(CommandSender sender, String[] input) {

        if (input.length == 0) {

            sender.sendMessage(ChatColor.RED + "Please use /chunkcloner enable, disable, message, or radius.");

        } else if (input[0].equalsIgnoreCase("enable")) {

            ChunkCloner.setEnabledCC(true);
            sender.sendMessage(ChatColor.RED + "Enabled the ChunkCloner.");

        } else if (input[0].equalsIgnoreCase("disable")) {

            ChunkCloner.setEnabledCC(false);
            sender.sendMessage(ChatColor.RED + "Disabled the ChunkCloner.");

        } else if (input[0].equalsIgnoreCase("message")) {

            if (input.length != 2) sender.sendMessage(ChatColor.RED + "Please use /chunkcloner message enable/disable.");

            else {

                if (input[1].equalsIgnoreCase("enable")) {

                    ChunkCloner.setMessageCC(true);
                    sender.sendMessage(ChatColor.RED + "Enabled the ChunkCloner Messages.");

                } else if (input[1].equalsIgnoreCase("disable")) {

                    ChunkCloner.setMessageCC(false);
                    sender.sendMessage(ChatColor.RED + "Disabled the ChunkCloner Messages.");

                } else {

                    sender.sendMessage(ChatColor.RED + "Please use /chunkcloner message enable/disable.");

                }

            }

        } else if (input[0].equalsIgnoreCase("radius")) {

            int r = 0;
            if (input.length != 2) {

                sender.sendMessage(ChatColor.RED + "Please use /chunkcloner radius (integer)");

            }

            else {

                try {

                    r = Integer.parseInt(input[1]);

                } catch (NumberFormatException e) {

                    sender.sendMessage(ChatColor.RED + "Please use /chunkcloner radius (integer)");;
                    return;

                }

            }

            if (r != 0) {

                ChunkCloner.setDistanceCC(r);
                sender.sendMessage(ChatColor.RED + "ChunkLoader radius is now " + r + ".");

            }


        }else {

            sender.sendMessage(ChatColor.RED + "Please use /chunkchanger enable or disable");

        }

    }

}
