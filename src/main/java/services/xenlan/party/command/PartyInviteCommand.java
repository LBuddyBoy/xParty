package services.xenlan.party.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;
import services.xenlan.party.util.fancymsg.FancyMessage;

public class PartyInviteCommand {
	@Command(names = "party invite", permission = "", description = "Invites the player provided")
	public static void invite(Player sender, @Param(name = "player") PartyUser target) {
		PartyUser senderUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		Player targetBukkit = PartyUser.bukkitPlayer(target);
		if (!target.isInAParty()) {
			FancyMessage fancyTarget = new FancyMessage();
			FancyMessage fancySender = new FancyMessage();

			fancyTarget.text("Join " + sender.getName() + "'s party by clicking here").color(ChatColor.GREEN).command("/party join " + senderUser.getPartyIn().getName()).tooltip("Click to join");

			fancySender.text("You have just invited " + targetBukkit.getName() + " to the party. Click here to remove their invitation.").color(ChatColor.GREEN).command("/party uninvite " + targetBukkit.getName()).tooltip("Click to uninvite " + targetBukkit.getName()).color(ChatColor.YELLOW);

			fancyTarget.send(targetBukkit);
			fancySender.send(sender);

			senderUser.getPartyIn().getInvites().add(target);
		} else {
			sender.sendMessage(CC.chat("&cThat player is already in a party and cannot be invited as of right now."));
		}
	}
}
