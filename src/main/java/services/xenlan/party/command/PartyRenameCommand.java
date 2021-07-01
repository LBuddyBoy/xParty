package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;
import services.xenlan.party.xParty;

/**
 * @author LBuddyBoy (lbuddyboy.me)
 * 30/06/2021 / 6:32 PM
 * xParty / services.xenlan.party.command
 */
public class PartyRenameCommand {

	@Command(names = "party rename", permission = "")
	public static void rename(Player sender, @Param(name = "new-name") String newName) {
		PartyUser senderUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (senderUser.getPartyIn() == null) {
			sender.sendMessage(PartyConstants.NOT_IN_PARTY.getMessage());
			return;
		}
		for (Party p : xParty.getInstance().getParties()) {
			if (p.getName().equalsIgnoreCase(newName)) {
				sender.sendMessage(CC.chat("&cA party with that name already exists."));
				break;
			}
		}

	}

}
