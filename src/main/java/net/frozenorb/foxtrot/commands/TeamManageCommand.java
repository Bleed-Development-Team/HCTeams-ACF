package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.team.Team;
import net.frozenorb.foxtrot.team.commands.SetTeamBalanceCommand;
import net.frozenorb.foxtrot.team.commands.team.TeamCommands;
import net.frozenorb.foxtrot.team.menu.*;
import net.frozenorb.foxtrot.util.Callback;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
@CommandAlias("manageteam")
@CommandPermission("foxtrot.team.manage")
public class TeamManageCommand extends BaseCommand {


    @Subcommand("leader")
    @Description("Set a team leader")
    public static void teamLeader(Player sender, Team team) {
        new SelectNewLeaderMenu(team).openMenu(sender);
    }
    @Subcommand("promote")
    @Description("Promote a player")
    public static void promoteTeam(Player sender, Team team) {
        new PromoteMembersMenu(team).openMenu(sender);
    }


    @Subcommand("demote")
    @Description("Demote a player")
    public static void demoteTeam(Player sender, Team team) {
        new DemoteMembersMenu(team).openMenu(sender);
    }



    @Subcommand("kick")
    @Description("Kick a player from a team")
    public static void kickTeam(Player sender, Team team) {
        new KickPlayersMenu(team).openMenu(sender);
    }



    @Subcommand("balance")
    @Description("Set a team balance")
    public static void balanceTeam(Player sender, Team team) {
        conversationDouble(sender, "§bEnter a new balance for " + team.getName() + ".", (d) -> {
            SetTeamBalanceCommand.setTeamBalance(sender, team, d.floatValue());
            sender.sendRawMessage(ChatColor.GRAY + team.getName() + " now has a balance of " + team.getBalance());
        });
    }


    @Subcommand("dtr")
    @Description("Set a team DTR")
    public static void dtrTeam(Player sender, Team team) {
        if (sender.hasPermission("foxtrot.manage.setdtr")) {
            conversationDouble(sender, "§eEnter a new DTR for " + team.getName() + ".", (d) -> {
                team.setDTR(d.floatValue());
                sender.sendRawMessage(ChatColor.LIGHT_PURPLE + team.getName() + ChatColor.YELLOW + " has a new DTR of " + ChatColor.LIGHT_PURPLE + d.floatValue() + ChatColor.YELLOW + ".");
            });
        } else {
            new DTRMenu(team).openMenu(sender);
        }
    }


    @Subcommand("rename")
    @Description("Rename a team")
    public static void renameTeam(Player sender, Team team) {
        conversationString(sender, "§aEnter a new name for " + team.getName() + ".", (name) -> {
            String oldName = team.getName();
            team.rename(name);
            sender.sendRawMessage(ChatColor.GRAY + oldName + " now has a name of " + team.getName());
        });
    }



    @Subcommand("mute")
    @Description("Mute a team")
    public static void muteTeam(Player sender, Team team) {
        new MuteMenu(team).openMenu(sender);

    }



    @Subcommand("manage")
    @Description("Manage a team")
    public static void manageTeam(Player sender, Team team) {
        new TeamManageMenu(team).openMenu(sender);
    }

    private static void conversationDouble(Player p, String prompt, Callback<Double> callback) {
        ConversationFactory factory = new ConversationFactory(Foxtrot.getInstance()).withModality(true).withPrefix(new NullConversationPrefix()).withFirstPrompt(new StringPrompt() {

            public String getPromptText(ConversationContext context) {
                return prompt;
            }

            @Override
            public Prompt acceptInput(ConversationContext cc, String s) {
                try {
                    callback.callback(Double.parseDouble(s));
                } catch (NumberFormatException e) {
                    cc.getForWhom().sendRawMessage(ChatColor.RED + s + " is not a number.");
                }

                return Prompt.END_OF_CONVERSATION;
            }

        }).withLocalEcho(false).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Go away evil console!");

        Conversation con = factory.buildConversation(p);
        p.beginConversation(con);

    }

    private static void conversationString(Player p, String prompt, Callback<String> callback) {
        ConversationFactory factory = new ConversationFactory(Foxtrot.getInstance()).withModality(true).withPrefix(new NullConversationPrefix()).withFirstPrompt(new StringPrompt() {

            public String getPromptText(ConversationContext context) {
                return prompt;
            }

            @Override
            public Prompt acceptInput(ConversationContext cc, String newName) {

                if (newName.length() > 16) {
                    cc.getForWhom().sendRawMessage(ChatColor.RED + "Maximum team name size is 16 characters!");
                    return Prompt.END_OF_CONVERSATION;
                }

                if (newName.length() < 3) {
                    cc.getForWhom().sendRawMessage(ChatColor.RED + "Minimum team name size is 3 characters!");
                    return Prompt.END_OF_CONVERSATION;
                }

                if (!TeamCommands.ALPHA_NUMERIC.matcher(newName).find()) {
                    if (Foxtrot.getInstance().getTeamHandler().getTeam(newName) == null) {
                        callback.callback(newName);
                        return Prompt.END_OF_CONVERSATION;

                    } else {
                        cc.getForWhom().sendRawMessage(ChatColor.RED + "A team with that name already exists!");
                    }
                } else {
                    cc.getForWhom().sendRawMessage(ChatColor.RED + "Team names must be alphanumeric!");
                }


                return Prompt.END_OF_CONVERSATION;
            }

        }).withLocalEcho(false).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Go away evil console!");

        Conversation con = factory.buildConversation(p);
        p.beginConversation(con);

    }
}
