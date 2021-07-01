package services.xenlan.party.menus.buttons;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import services.xenlan.party.api.PartyAPI;
import services.xenlan.party.manager.PartyManager;
import services.xenlan.party.object.PartyUser;
import services.xenlan.party.util.CC;
import services.xenlan.party.util.ItemBuilder;
import services.xenlan.party.util.menu.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoButton extends Button {
	@Override
	public ItemStack stack(Player p) {
		PartyUser user = new PartyManager().getUserByUUID(p.getUniqueId());
		List<String> names = new ArrayList<>();
		for (PartyUser u : user.getPartyIn().getMembers()) {
			names.add(Bukkit.getPlayer(u.getUuid()).getName());
		}
		return new ItemBuilder(Material.BOOK)
				.setDisplayName(CC.chat("&b&l" + user.getPartyIn().getName() + " Info "))
				.setLore(CC.chat(Arrays.asList(
						" ",
						"&fLeader: &b" + PartyAPI.getPartyUserName(user.getPartyIn().getLeader().getUuid()),
						"&fMembers: &b" + (user.getPartyIn().getMembers().isEmpty() ? "None" : StringUtils.join(names, ", "))
						)))
				.make();
	}

	@Override
	public void action(Player player, int slot, InventoryClickEvent event) {
		event.setCancelled(true);
	}

	@Override
	public int slot() {
		return 4;
	}
}
