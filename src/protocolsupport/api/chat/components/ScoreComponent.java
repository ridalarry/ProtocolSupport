/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.scoreboard.Objective
 *  org.bukkit.scoreboard.Score
 *  org.bukkit.scoreboard.Scoreboard
 */
package protocolsupport.api.chat.components;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import protocolsupport.api.chat.components.BaseComponent;

public class ScoreComponent
extends BaseComponent {
    private String playername;
    private String objectivename;
    private String value;

    public ScoreComponent(String playername, String objectivename) {
        this.playername = playername;
        this.objectivename = objectivename;
    }

    public String getPlayerName() {
        return this.playername;
    }

    public String getObjectiveName() {
        return this.objectivename;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        Scoreboard board;
        Objective objective;
        Player player;
        if (this.value == null && (player = Bukkit.getPlayerExact((String)this.playername)) != null && (objective = (board = player.getScoreboard()).getObjective(this.objectivename)) != null) {
            this.value = String.valueOf(objective.getScore(this.playername).getScore());
        }
        if (this.value == null) {
            this.value = "";
        }
        return this.value;
    }
}

