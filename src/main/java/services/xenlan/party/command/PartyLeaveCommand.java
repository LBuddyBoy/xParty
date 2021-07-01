package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;

public class PartyLeaveCommand {
	@Command(names = "party leave", permission = "")
	public static void leave(Player bukkitPlayer) {
		PartyUser senderUser = new PartyManager().getUserByUUID(bukkitPlayer.getUniqueId());
		if (!senderUser.isInAParty()) {
			bukkitPlayer.sendMessage(PartyConstants.NOT_IN_PARTY.getMessage());
			return;
		}
		if (senderUser.getPartyIn().getLeader() == senderUser) {
			bukkitPlayer.sendMessage(CC.chat("&cYou can't leave the party as leader! Make someone else party leader or disband it by doing /party disband."));
			return;
		}
		bukkitPlayer.sendMessage(PartyConstants.PARTY_LEFT_SENDER.getMessage());
		Party p = new PartyManager().getPartyByName(senderUser.getPartyIn().getName());
		Party.sendMessage(p, PartyConstants.PARTY_LEFT_MEMBERS.getMessage().replace("%left%", senderUser.getName()));
		p.getMembers().remove(senderUser);
		senderUser.setPartyIn(null);
		senderUser.setInAParty(false);
	}
}
