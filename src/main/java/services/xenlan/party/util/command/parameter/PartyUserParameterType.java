package services.xenlan.party.util.command.parameter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.command.data.ParameterType;
import services.xenlan.party.xParty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PartyUserParameterType implements ParameterType<PartyUser> {
	@Override
	public PartyUser transform(CommandSender sender, String value) {
		if (sender instanceof PartyUser && (value.equalsIgnoreCase("self") || value.equals(""))) {
			return (PartyUser) sender;
		}

		PartyUser player= new PartyManager().getUserByUUID(Bukkit.getPlayer(value).getUniqueId());
		if (player == null || sender instanceof PartyUser) {
			sender.sendMessage(ChatColor.RED + "No player with the name \"" + value + "\" found.");
			return null;
		}
		return player;
	}

	@Override
	public List<String> tabComplete(Player sender, Set<String> flags, String source) {
		ArrayList<String> completions=new ArrayList<>();
		for ( PartyUser player : xParty.getInstance().getPartyUsers() ) {
			completions.add(Bukkit.getPlayer(player.getUuid()).getName());
		}
		return completions;
	}
}