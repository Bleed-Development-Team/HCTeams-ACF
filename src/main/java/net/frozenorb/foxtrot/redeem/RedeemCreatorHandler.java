package net.frozenorb.foxtrot.redeem;

import lombok.Getter;
import net.frozenorb.foxtrot.Foxtrot;
import net.frozenorb.foxtrot.util.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class RedeemCreatorHandler {
   @Getter public FileConfig fileConfig = new FileConfig(Foxtrot.getInstance(), "redeem_creator.yml");

   public RedeemCreatorHandler() {
      this.loadConfig();
   }

   private void loadConfig() {
      FileConfiguration config = this.fileConfig.getConfig();

      config.options().copyDefaults(true);
      this.fileConfig.save();
   }

   public List<String> getPartnerCommands(String partner) {
      String path = "partner." + partner.toLowerCase();
      FileConfiguration config = this.fileConfig.getConfig();
      List<String> commands = config.getStringList(path + ".commands");
      return !commands.isEmpty() ? commands : null;
   }
}
