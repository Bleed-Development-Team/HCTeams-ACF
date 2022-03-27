package net.frozenorb.foxtrot.redeem;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("redeem|redeemcreator|creatorredeem")
public class RedeemCreatorCommand extends BaseCommand {

   @Default
   @Description("Redeem yourself")
   public static void execute(Player player, String creator) {
      List<String> commands = Foxtrot.getInstance().getRedeemCreatorHandler().getPartnerCommands(creator);

      if (commands == null) {
         player.sendMessage(CC.translate("&cThe creator &e" + creator + " &cis not a valid creator."));
         return;
      }

      if (hasRedeemed(player)) {
         player.sendMessage(CC.RED + "You have already redeemed a creator this map.");
         return;
      }

      // Redeem the creator

      redeem(player, creator);

      Bukkit.getServer().getScheduler().runTask(Foxtrot.getInstance(), () -> {
         for (String command : commands) {
            command = command.startsWith("/") ? command.substring(1) : command;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
         }

         player.sendMessage(CC.GREEN + "Thank you for supporting " + CC.WHITE + creator + CC.GREEN + "!" + CC.RED + " ❤");
         Bukkit.broadcastMessage(CC.translate("&b&lRedeem &7" + CC.VERTICAL_SEPARATOR + " &b" + player.getName() + " &fhas redeemed the creator &b" + creator + "&f!"));

      });
   }



   private static boolean hasRedeemed(Player player) {
      return Foxtrot.getInstance().runRedisCommand(jedis -> jedis.exists("redeemed_creator:" + player.getUniqueId()));
   }

   private static void redeem(Player player, String creator) {
      Foxtrot.getInstance().runRedisCommand(jedis -> jedis.set("redeemed_creator:" + player.getUniqueId(), creator));
   }
}
