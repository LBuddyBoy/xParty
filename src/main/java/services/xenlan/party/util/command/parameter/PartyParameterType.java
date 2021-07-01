package services.xenlan.party.util.command.parameter;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.Party;
import services.xenlan.party.util.command.data.ParameterType;
import services.xenlan.party.xParty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PartyParameterType implements ParameterType<Party> {
	@Override
	public Party transform(CommandSender sender, String value) {
		if (sender instanceof Party && (value.equalsIgnoreCase("self") || value.equals(""))) {
			return (Party) sender;
		}

		Party player= new PartyManager().getPartyByName(value);
		if (player == null || sender instanceof Party) {
			sender.sendMessage(ChatColor.RED + "No party with the name \"" + value + "\" found.");
			return null;
		}
		return player;
	}

	@Override
	public List<String> tabComplete(Player sender, Set<String> flags, String source) {
		ArrayList<String> completions=new ArrayList<>();
		for ( Party player : xParty.getInstance().getParties() ) {
			completions.add(player.getName());
		}
		return completions;
	}
}
