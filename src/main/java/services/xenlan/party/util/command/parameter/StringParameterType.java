package services.xenlan.party.util.command.parameter;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import services.xenlan.party.util.command.data.ParameterType;

import java.util.List;
import java.util.Set;

public class StringParameterType
        implements ParameterType<String> {
    @Override
    public String transform(CommandSender sender, String value) {
        return value;
    }

    @Override
    public List<String> tabComplete(Player sender, Set<String> flags, String prefix) {
        return ImmutableList.of();
    }
}

