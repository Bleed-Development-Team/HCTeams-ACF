package net.frozenorb.foxtrot.redeem;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.Foxtrot;
import org.bukkit.entity.Player;

@CommandAlias("redeemreload")
@CommandPermission("op")
public class RedeemReload extends BaseCommand {


    @Default
    @Description("Reloads the redeem config")
    public static void reloadCmd(Player player){
        Foxtrot.getInstance().getRedeemCreatorHandler().getFileConfig().save();
    }
}
