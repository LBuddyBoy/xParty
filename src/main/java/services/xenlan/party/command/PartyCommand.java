package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.CommandHandler;
import services.xenlan.party.util.command.annotation.Command;

public class PartyCommand {

	@Command(names = {"party"}, permission = "", description = "Displays this message")
	public static void exe(Player sender) {
		sender.sendMessage(" ");
		sender.sendMessage(CC.chat("&b&lParty Help"));
		sender.sendMessage(" ");
		for (org.bukkit.command.Command cmd : CommandHandler.knownCommands.values()) {
			if (cmd.getName().contains("party")) {
				sender.sendMessage(CC.chat("/" + cmd.getName() + " - " + cmd.getDescription()));
			}
		}
	}



}
