package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;

public class PartyJoinCommand {
	@Command(names = "party join", permission = "", description = "Joins the party desired if you are invited.")
	public static void join(Player sender, @Param(name = "party") Party p) {
		PartyUser senderUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (p != null) {
			if (!p.getInvites().contains(senderUser)) {
				sender.sendMessage(CC.chat("&cThat party hasn't invited you."));
				return;
			}
			if (senderUser.isInAParty()) {
				sender.sendMessage(CC.chat("&cYou are currently in a party!"));
				return;
			}
			p.getInvites().remove(senderUser);
			p.getMembers().add(senderUser);
			senderUser.setInAParty(true);
			senderUser.setPartyIn(p);
//			sender.sendMessage(CC.chat("&aYou have successfully joined " + p.getName() + "'s party."));
			sender.sendMessage(PartyConstants.PARTY_JOINED_SENDER.getMessage().replace("%name%", p.getName()));
//			Party.sendMessage(p, "&b" + sender.getName() + "&f has joined the party.");
			Party.sendMessage(p, PartyConstants.PARTY_JOINED_MEMBERS.getMessage().replace("%joiner%", sender.getName()));
		} else {
			sender.sendMessage(PartyConstants.NULL_PARTY.getMessage());
		}
	}
}
