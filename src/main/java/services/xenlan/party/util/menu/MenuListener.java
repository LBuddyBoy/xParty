package services.xenlan.party.util.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

	@EventHandler
	public void onInvClick(InventoryClickEvent event) {

		if (event.getWhoClicked() instanceof Player) {

			Player p = (Player) event.getWhoClicked();
			for (Menu m : new MenuManager().menus) {
				if (m.title(p).equalsIgnoreCase(event.getInventory().getTitle())) {
					for (Button b : m.buttons(p)) {
						if (event.getCurrentItem() == null)
							return;

						if (b.slot() == event.getRawSlot()) {
							b.action(p, b.slot(), event);
						} else {
							event.setCancelled(true);
						}
					}
				}
			}

		}

	}

}
