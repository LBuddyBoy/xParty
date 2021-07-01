package services.xenlan.party.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import services.xenlan.party.PartyConstants;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.command.annotation.Command;
import services.xenlan.party.util.command.annotation.Param;

public class PartyKickCommand {
	@Command(names = "party kick", permission = "", description = "Kicks a member of your party")
	public static void kick(Player sender, @Param(name = "member") PartyUser member) {
		Player bukkitPlayer = Bukkit.getPlayer(member.getUuid());
		Player senderBukkit = Bukkit.getPlayer(sender.getUniqueId());
		PartyUser senderUser = new PartyManager().getUserByUUID(sender.getUniqueId());
		if (!senderUser.isInAParty()) {
			senderBukkit.sendMessage(PartyConstants.NOT_IN_PARTY.getMessage());
			return;
		}
		if (!member.isInAParty()) {
			senderBukkit.sendMessage(PartyConstants.NOT_IN_PARTY_OTHER.getMessage());
			return;
		}
		if (member.getPartyIn() != senderUser.getPartyIn()) {
			senderBukkit.sendMessage(PartyConstants.NOT_IN_PARTY_OTHER.getMessage());
			return;
		}
		if (senderUser.getPartyIn().getLeader() != senderUser) {
			senderBukkit.sendMessage(PartyConstants.NOT_LEADER.getMessage());
			return;
		}
		if (member == senderUser.getPartyIn().getLeader()) {
			senderBukkit.sendMessage(CC.chat("&cYou can't kick yourself from the party."));
			return;
		}
		Party p = new PartyManager().getPartyByName(member.getPartyIn().getName());
		PartyUser.sendMessage(member, PartyConstants.PARTY_KICK_SENDER.getMessage().replace("%name%", p.getName()));
		p.getMembers().remove(member);
		member.setPartyIn(null);
		member.setInAParty(false);
		for (PartyUser uses : p.getMembers()) {
			PartyUser.sendMessage(uses, PartyConstants.PARTY_KICK_MEMBERS.getMessage().replace("%kicked%", member.getName()));
		}
		PartyUser.sendMessage(p.getLeader(), PartyConstants.PARTY_KICK_MEMBERS.getMessage().replace("%kicked%", member.getName()));
	}
}
