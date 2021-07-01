package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;

public class PartyLeaderCommand {

	@Command(names = "party leader", permission = "", description = "Changes the leader of the party")
	public static void leader(Player sender, @Param(name = "member")PartyUser user) {
		PartyUser senderUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (senderUser.getPartyIn() == null) {
			sender.sendMessage(PartyConstants.NOT_IN_PARTY.getMessage());
			return;
		}
		if (user.getPartyIn() == null) {
			sender.sendMessage(PartyConstants.NOT_IN_PARTY_OTHER.getMessage());
			return;
		}
		if (user.getPartyIn().getLeader() == senderUser) {
			sender.sendMessage(CC.chat("&cYou cannot make yourself the leader."));
			return;
		}

		user.getPartyIn().getMembers().remove(user);
		user.getPartyIn().setLeader(user);
		user.getPartyIn().getMembers().add(senderUser);



	}

}
