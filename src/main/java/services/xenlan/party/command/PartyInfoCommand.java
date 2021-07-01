package services.xenlan.party.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;
import services.xenlan.party.util.menu.MenuManager;

public class PartyInfoCommand {
	@Command(names = "party info", permission = "", description = "Displays a menu with information regarding your party.")
	public static void info(Player sender, @Param(name = "player") PartyUser target) {
		if (target != null) {
			if (target.isInAParty()) {
				try {
					new MenuManager().byName("partyinfo").openMenu(sender, Bukkit.getPlayer(target.getUuid()));
				} catch (Exception ignored) {
					sender.sendMessage(CC.chat("&cCould not retreive any information on " + Bukkit.getPlayer(target.getUuid()).getName() + "'s party."));
				}
			} else {
				sender.sendMessage(CC.chat("&7User is not in a party."));
			}
		} else {
			sender.sendMessage(CC.chat("&cWe could not locate that player."));
		}
	}
}
