package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;

public class PartyUninviteCommand {
	@Command(names = "party uninvite", permission = "")
	public static void uninvite(Player sender, @Param(name = "player") PartyUser target) {
		PartyUser senderUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (target != null) {
			if (senderUser.getPartyIn().getLeader() == senderUser) {
				if (!senderUser.getPartyIn().getInvites().contains(target)) {
					sender.sendMessage(CC.chat("&cThat player isn't invited to your party."));
					return;
				}
				if (!target.isInAParty()) {
//					Bukkit.getPlayer(target.getUuid()).sendMessage(CC.chat("&cYou have been uninvited from " + sender.getName() + "'s party."));
					sender.sendMessage(CC.chat("&aYou have just removed " + target.getName() + "'s invitation to your party."));
					senderUser.getPartyIn().getInvites().remove(target);
				}
			} else {
				sender.sendMessage(PartyConstants.NOT_LEADER.getMessage());
			}
		} else {
			sender.sendMessage(PartyConstants.INVALID_PARTYUSER.getMessage());
		}
	}
}
