package services.xenlan.party.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import services.xenlan.party.util.CC;

import java.util.List;

@Getter
@AllArgsConstructor
public class Party {

	@Setter private String name;
	@Setter private PartyUser leader;
	private List<PartyUser> members;
	private List<PartyUser> invites;

	public static void sendMessage(Party p, String msg) {
		for (PartyUser uses : p.getMembers()) {
			if (Bukkit.getPlayer(uses.getUuid()).isOnline()) {
				Bukkit.getPlayer(uses.getUuid()).sendMessage(CC.chat(msg));
			}
		}
		Bukkit.getPlayer(p.getLeader().getUuid()).sendMessage(CC.chat(msg));
	}

}
