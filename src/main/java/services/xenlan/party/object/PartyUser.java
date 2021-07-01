package services.xenlan.party.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import services.xenlan.party.util.CC;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PartyUser {

	private UUID uuid;
	@Setter private Party partyIn;
	@Setter private boolean inAParty;

	public String getName() {
		return bukkitPlayer(this).getName();
	}

	public static void sendMessage(PartyUser user, String msg) {
		Bukkit.getPlayer(user.getUuid()).sendMessage(CC.chat(msg));
	}

	public static Player bukkitPlayer(PartyUser user) {
		return Bukkit.getPlayer(user.getUuid());
	}
}
