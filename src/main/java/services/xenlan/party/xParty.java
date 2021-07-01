package services.xenlan.party;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import services.xenlan.party.listener.PartyUserListener;
import services.xenlan.party.object.Party;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.command.CommandHandler;
import services.xenlan.party.util.menu.MenuManager;

import java.util.HashSet;
import java.util.Set;

public class xParty extends JavaPlugin {

	@Getter public static xParty instance;

	@Getter private Set<Party> parties;
	@Getter private Set<PartyUser> partyUsers;

	@Override
	public void onEnable() {
		instance = this;

		getServer().getPluginManager().registerEvents(new PartyUserListener(), this);

		parties = new HashSet<>();
		partyUsers = new HashSet<>();

		new MenuManager();

		loadCommands();
	}

	/*
	Command API Borrowed from aLibbbbb
	 */
	private void loadCommands() {
		CommandHandler.init();
		CommandHandler.registerPackage(this, "services.xenlan.party.command");
	}

}
