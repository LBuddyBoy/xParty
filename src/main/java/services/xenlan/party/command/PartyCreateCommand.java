package services.xenlan.party.command;

import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.api.PartyAPI;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;

public class PartyCreateCommand {
	@Command(names = "party create", permission = "", description = "Creates a new party with a name you desire!")
	public static void ex(Player sender, @Param(name = "name") String name) {
		PartyUser partyUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (partyUser.isInAParty()) {
			sender.sendMessage(PartyConstants.IN_A_PARTY.getMessage());
			return;
		}
		PartyAPI.create(partyUser, name);
		sender.sendMessage(PartyConstants.PARTY_CREATED.getMessage().replace("%name%", name));
	}
}
