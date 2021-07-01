package services.xenlan.party.util.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class Menu {

	public abstract String name();
	public abstract String title(Player player);
	public abstract List<Button> buttons(Player player);
	public abstract int size();
	public abstract boolean autoUpdate();

	public void openMenu(Player player, Player target) {
		Inventory i = Bukkit.createInventory(null, size(), title(target));
		for (Button b : buttons(target)) {
			i.setItem(b.slot(), b.stack(target));
		}
		player.openInventory(i);
	}

//	public void updateMenu(Menu menu) {
//
//	}

}
