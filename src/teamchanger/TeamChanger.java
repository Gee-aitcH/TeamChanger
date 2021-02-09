package teamchanger;

import arc.util.CommandHandler;
import mindustry.game.Team;
import mindustry.gen.Player;
import pluginutil.GHPlugin;

import java.util.Arrays;

import static pluginutil.PluginUtil.GHColors.accent;
import static pluginutil.PluginUtil.GHColors.pass;
import static pluginutil.PluginUtil.f;

public class TeamChanger extends GHPlugin {

    public TeamChanger() {
        adminOnlyCommands = new String[]{"team"};
    }

    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("team", "[team_name]", "Team Changer.", (args, player) -> {
            if (!player.admin)
                return;

            if (args == null || args.length == 0){
                msg(accent, f("Not enough arguments, the available teams are %s.", Arrays.toString(Team.baseTeams)), player);
                return;
            }

            for (Team team : Team.baseTeams)
                if (args[0].equals(team.name)){
                    if (player.team() == team){
                        msg(accent, "You are already on this team!", player);
                        return;
                    }
                    player.team(team);
                    msg(pass, f("You are now on Team %s!", team.name), player);
                    return;
                }

            msg(accent, f("Can't find Team [%s], the available teams are %s, please check again.", args[0], Arrays.toString(Team.baseTeams)), player);
        });
    }
}