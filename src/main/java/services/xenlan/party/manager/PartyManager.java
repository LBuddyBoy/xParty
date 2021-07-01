package services.xenlan.party.manager;

import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.xParty;

import java.util.UUID;

public class PartyManager {



	public PartyManager() {

	}

	public void disband(Party party) {
		party.getMembers().clear();
		party.getInvites().clear();
		party.setName(null);
	}

	public Party getPartyByName(String party) {
		for (Party p : xParty.getInstance().getParties()) {
			if (p.getName().equals(party)) {
				return p;
			}
		}
		return null;
	}

	public PartyUser getUserByUUID(UUID name) {
		for (PartyUser partyUser : xParty.getInstance().getPartyUsers()) {
			if (partyUser.getUuid().equals(name)) {
				return partyUser;
			}
		}
		return null;
	}

}
