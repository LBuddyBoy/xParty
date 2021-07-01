package services.xenlan.party.menus;

import org.bukkit.entity.Player;
import services.xenlan.party.menus.buttons.InfoButton;
import services.xenlan.party.util.menu.Button;
import services.xenlan.party.util.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class PartyInfoMenu extends Menu {
	@Override
	public String name() {
		return "partyinfo";
	}

	@Override
	public String title(Player p) {
		return "Party Info";
	}

	@Override
	public List<Button> buttons(Player p) {
		List<Button> bs = new ArrayList<>();
		bs.add(new InfoButton());
		return bs;
	}

	@Override
	public int size() {
		return 9;
	}

	@Override
	public boolean autoUpdate() {
		return false;
	}
}
