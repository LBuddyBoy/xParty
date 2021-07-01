package services.xenlan.party.util;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LBuddyBoy (lbuddyboy.me)
 * 30/06/2021 / 7:13 PM
 * xParty / services.xenlan.party.util
 */

public class ItemBuilder {

	@Getter
	private Material material;

	@Getter
	private ItemStack stack;

	public ItemBuilder(Material material) {
		this.material = material;

		stack = new ItemStack(getMaterial());
	}


	/*
	Sets the display name of the provided ItemStack when creating a new instance of the ItemBuilder.java Class.
	 */
	public void setDisplayName(String newName) {
		ItemMeta meta = this.stack.getItemMeta();
		meta.setDisplayName(CC.chat(newName));
		this.stack.setItemMeta(meta);
	}

	/*
	Add to the lore of the provided ItemStack when creating a new instance of the ItemBuilder.java Class.
 	*/
	public void addLore(String toAdd) {
		ItemMeta meta = this.stack.getItemMeta();

		List<String> newLore = new ArrayList<>();
		if (meta.getLore() == null) {
			meta.setLore(Collections.singletonList(toAdd));
		} else {
			newLore.add(toAdd);
			meta.setLore(newLore);
		}
		this.stack.setItemMeta(meta);
	}

	/*
	Sets the lore of the provided ItemStack when creating a new instance of the ItemBuilder.java Class.
 	*/
	public void setLore(List<String> toSet) {
		ItemMeta meta = this.stack.getItemMeta();

		meta.setLore(toSet);

		this.stack.setItemMeta(meta);
	}

}
