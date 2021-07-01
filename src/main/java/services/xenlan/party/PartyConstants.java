package services.xenlan.party;

import lombok.Getter;
import services.xenlan.party.util.CC;

/**
 * @author LBuddyBoy (lbuddyboy.me)
 * 30/06/2021 / 6:04 PM
 * xParty / services.xenlan.party
 */
public enum PartyConstants {

	PARTY_CREATED(CC.chat("&aYou have just created a party by the name: %name%")),
	PARTY_JOINED_SENDER(CC.chat("&aYou have just joined the %name% party.")),
	PARTY_JOINED_MEMBERS(CC.chat("&a%joiner% has just joined the party.")),
	PARTY_DISBAND_SENDER(CC.chat("&aSuccessfully disbaned your party.")),
	PARTY_DISBAND_MEMBERS(CC.chat("&aYour party has just been disbanded.")),
	PARTY_KICK_SENDER(CC.chat("&aYou have just been kicked from the %name% party.")),
	PARTY_KICK_MEMBERS(CC.chat("&a%kicked% has just been removed from the party.")),
	PARTY_LEFT_SENDER(CC.chat("&aYou have left the party.")),
	PARTY_LEFT_MEMBERS(CC.chat("&a%left% has just left the party.")),
	INVALID_PARTYUSER(CC.chat("&aThat party user does not exist.")),
	NULL_PARTY(CC.chat("&cThat party does not exist.")),
	NOT_IN_PARTY(CC.chat("&cYou can only do this in a party.")),
	NOT_IN_PARTY_OTHER(CC.chat("&cThat player is not in a party.")),
	IN_A_PARTY(CC.chat("&cYou are already in a party.")),
	NOT_LEADER(CC.chat("&cYou cannot do this as you are not the leader of the party."));

	@Getter private String message;

	PartyConstants(String message) {
		this.message = message;
	}

}
