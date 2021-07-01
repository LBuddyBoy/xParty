package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.api.PartyAPI;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.command.annotation.Command;

public class PartyDisbandCommand {
	@Command(names = "party disband", permission = "", description = "Disbands the party you are in if you're leader")
	public static void disband(Player sender) {
		PartyUser partyUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (!partyUser.isInAParty()) {
			sender.sendMessage(PartyConstants.NOT_IN_PARTY.getMessage());
			return;
		}
		if (partyUser.getPartyIn().getLeader() != partyUser) {
			sender.sendMessage(PartyConstants.NOT_LEADER.getMessage());
			return;
		}
		for (PartyUser u : partyUser.getPartyIn().getMembers()) {
			PartyUser.sendMessage(u, PartyConstants.PARTY_DISBAND_MEMBERS.getMessage());
		}
		PartyAPI.disband(partyUser.getPartyIn());
		sender.sendMessage(PartyConstants.PARTY_DISBAND_SENDER.getMessage());
	}
}
