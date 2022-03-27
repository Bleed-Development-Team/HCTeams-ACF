package net.frozenorb.foxtrot.redeem;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.util.CC;
import org.bukkit.entity.Player;
@CommandAlias("redeemclear")
@CommandPermission("op")
public class RedeemClearCommand extends BaseCommand {

    @Default
    @Description("Clears your redeemed creator.")
    public static void excuteClear(Player sender, @Optional Player target) {
        if (target == null) {
            Foxtrot.getInstance().runRedisCommand(jedis -> jedis.del("redeemed_creator:" + sender.getUniqueId().toString()));
        } else {
            Foxtrot.getInstance().runRedisCommand(jedis -> jedis.del("redeemed_creator:" + target.getUniqueId().toString()));

        }
        sender.sendMessage(CC.GREEN + "You have successfully cleared redeemed players.");
    }
    
}
