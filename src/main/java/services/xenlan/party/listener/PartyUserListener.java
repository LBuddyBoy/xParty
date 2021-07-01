package services.xenlan.party.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.xParty;

public class PartyUserListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		if (new PartyManager().getUserByUUID(event.getPlayer().getUniqueId()) == null) {
			PartyUser partyUser = new PartyUser(event.getPlayer().getUniqueId(), null, false);
			xParty.getInstance().getPartyUsers().add(partyUser);
		}

	}

}
