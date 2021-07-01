package services.xenlan.party.util.menu;

import org.bukkit.Bukkit;
import services.xenlan.party.util.ClassUtils;
import services.xenlan.party.xParty;

import java.util.HashSet;
import java.util.Set;

public class MenuManager {

	public Set<Menu> menus;

	public MenuManager() {
		Bukkit.getPluginManager().registerEvents(new MenuListener(), xParty.getInstance());
		menus = new HashSet<>();
//		menus.add(new PartyInfoMenu());

		ClassUtils.getClassesInPackage(xParty.getInstance(), "services.xenlan.party").forEach(clazz -> {
			if (Menu.class.isAssignableFrom(clazz)) {
				try {
					Menu menu = (Menu) clazz.newInstance();

					menus.add(menu);
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Menu byName(String name) {
		for (Menu m : menus) {
			if (m.name().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

}
