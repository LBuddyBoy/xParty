package services.xenlan.party.api;

import org.bukkit.Bukkit;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.xParty;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PartyAPI {

	/**
	 * gets a PartyUser's name by providing a unique id.
	 *
	 * @param user use this to check a players uuid to get their party user profile.
	 */
	public static String getPartyUserName(UUID user) {
		return Bukkit.getPlayer(user).getName();
	}

	/**
	 * gets the size of a party
	 *
	 * @param party gets the party that you want to check
	 */
	public static int getPartySize(String party) {
		return new PartyManager().getPartyByName(party).getMembers().size();
	}

	/**
	 * gets a party by name
	 *
	 * @param party gets the party that you want to check
	 */
	public static Party getPartyByName(String party) {
		return new PartyManager().getPartyByName(party);
	}

	/**
	 * gets the leader of a party
	 *
	 * @param party gets the party that you want to check
	 */
	public static PartyUser getLeader(String party) {
		return new PartyManager().getPartyByName(party).getLeader();
	}

	/**
	 * gets a list of members in a party
	 *
	 * @param party gets the party that you want to check
	 */
	public static List<PartyUser> getMembers(String party) {
		return new PartyManager().getPartyByName(party).getMembers();
	}

	/**
	 * gets a list of invites in a party
	 *
	 * @param party gets the party that you want to check
	 */
	public static List<PartyUser> getInvites(String party) {
		return new PartyManager().getPartyByName(party).getInvites();
	}

	/**
	 * gets a specific member inside of a party
	 *
	 * @param party gets the party that you want to check
	 * @param memberName gets the member name that you want to search for
	 */
	public static PartyUser getPartyMemberByName(String party, String memberName) {
		for (PartyUser members : getMembers(party)) {
			if (Bukkit.getPlayer(members.getUuid()).getName().equalsIgnoreCase(memberName))
				return members;
		}
		return new PartyManager().getUserByUUID(Bukkit.getPlayer(memberName).getUniqueId());
	}

	/**
	 * Created a specified party & name
	 *
	 * @param leader sets the leader of the newly created party
	 * @param name sets the name of the newly created party
	 */
	public static void create(PartyUser leader, String name) {
		Party created = new Party(name, leader, Collections.emptyList(), Collections.emptyList());
		leader.setPartyIn(created);
		leader.setInAParty(true);
		xParty.getInstance().getParties().add(created);
	}


	/**
	 * Disbands the specified party
	 *
	 * @param party disbands the whatever party is specified.
	 */
	public static void disband(Party party) {
		for (Party p : xParty.getInstance().getParties()) {
			if (p == party) {
				for (PartyUser users : p.getMembers()) {
					users.setInAParty(false);
					users.setPartyIn(null);
				}
				p.getLeader().setInAParty(false);
				p.getLeader().setPartyIn(null);
				p.setLeader(null);
				p.getInvites().clear();
				p.getMembers().clear();
				p.setName(null);
			}
			xParty.getInstance().getParties().removeIf(pp -> pp.getName() == null);
		}
	}
}
