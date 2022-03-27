package net.frozenorb.foxtrot.commands;

import co.aikar.commands.BaseCommand;

public class NullFixCommand extends BaseCommand {
    /*
    @Command(value = { "nullfix" }, async = true)
    @Permission(value = "op")
    public static void fixNulls(CommandSender sender) {
        Set<UUID> nullUuids = Sets.newHashSet();
        
        for (Team team : Foxtrot.getInstance().getTeamHandler().getTeams()) {
            for (UUID member : team.getMembers()) {
                String name = UUIDUtils.name(member);
                if (name == null || name.equals("null")) {
                    nullUuids.add(member);
                }
            }
        }
        
        int fixed = 0;
        
        for (UUID nullUuid : nullUuids) {
            String name = getName(nullUuid);
            if (name != null) {
                if (name.equals("429")) {
                    break;
                }
                
                FrozenUUIDCache.update(nullUuid, name);
                fixed++;
            }
        }
        
        sender.sendMessage(ChatColor.GREEN + "Fixed " + fixed + " UUIDs.");
    }
    
    private String getName(UUID uuid) {
        String url = "https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names";
        try {
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size() - 1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);

            return nameObject.get("name").toString();
        } catch (Exception e) {
            return "429";
        }
    }
     */
    
}
