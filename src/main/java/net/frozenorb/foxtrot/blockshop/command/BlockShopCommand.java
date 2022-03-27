package net.frozenorb.foxtrot.blockshop.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.frozenorb.foxtrot.blockshop.BlockShop;
import org.bukkit.entity.Player;

@CommandAlias("blockshop|shop")
public class BlockShopCommand extends BaseCommand {

    @Default
    @Description("Opens the blockshop menu.")
    public static void blockShop(Player player){
        new BlockShop(player).updateMenu();
    }
}
