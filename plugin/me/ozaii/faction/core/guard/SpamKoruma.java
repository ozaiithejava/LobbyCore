/*    */ package me.ozaii.faction.core.guard;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import me.ozaii.faction.core.Plugin;
/*    */ import me.ozaii.faction.core.utils.DiscordWebhook;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpamKoruma
/*    */   implements Listener
/*    */ {
/*    */   private final JavaPlugin plugin;
/*    */   private final Map<String, List<String>> mesajGecmisi;
/*    */   
/*    */   public SpamKoruma(JavaPlugin plugin) {
/* 28 */     this.plugin = plugin;
/* 29 */     this.mesajGecmisi = new HashMap<>();
/*    */   }
/*    */   
/*    */   public void start() {
/* 33 */     this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerChat(AsyncPlayerChatEvent event) {
/* 38 */     String playerName = event.getPlayer().getName();
/* 39 */     List<String> gecmis = this.mesajGecmisi.getOrDefault(playerName, new ArrayList<>());
/* 40 */     gecmis.add(event.getMessage());
/* 41 */     if (gecmis.size() > 5)
/* 42 */       gecmis.remove(0); 
/* 43 */     this.mesajGecmisi.put(playerName, gecmis);
/* 44 */     if (gecmis.size() == 4 && gecmis.stream().distinct().count() == 1L) {
/* 45 */       event.getPlayer().sendMessage(String.valueOf(Plugin.prefix) + ChatColor.RED + "[ASİSTAN] " + 
/* 46 */           ChatColor.DARK_GREEN + "Dikkat Et 1kelime Daha Yazarsan Spamdan mute yiyeceksin");
/* 47 */     } else if (gecmis.size() == 5 && gecmis.stream().distinct().count() == 1L) {
/* 48 */       event.setCancelled(true);
/* 49 */       event.getPlayer().sendMessage(String.valueOf(Plugin.prefix) + ChatColor.RED + "[ASİSTAN] " + 
/* 50 */           ChatColor.DARK_GREEN + 
/* 51 */           "Spam yaptığınız için susturuldunuz! 5 dakika sonra tekrar yazabilirsiniz.");
/* 52 */       this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), 
/* 53 */           "mute " + event.getPlayer().getName() + " 5m Spam Yapmak yasaktır!");
/* 54 */       this.plugin.getLogger().info("Oyuncu " + event.getPlayer().getName() + 
/* 55 */           " spam yaptığı için 5 dakika süreyle susturuldu.");
/* 56 */       DiscordWebhook webhook = new DiscordWebhook(Plugin.spamhook);
/*    */ 
/*    */ 
/*    */       
/* 60 */       webhook.addEmbed((new DiscordWebhook.EmbedObject())
/* 61 */           .setTitle("(SPAM ENGELLEME SISTEMI)")
/* 62 */           .setImage("https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png")
/* 63 */           .setColor(Color.DARK_GRAY)
/* 64 */           .addField("Username", event.getPlayer().getName(), true)
/* 65 */           .addField("Spam Yaptığı için muteledim!", "", true)
/* 66 */           .setFooter("Developed by: ozaii", "https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png"));
/*    */       
/*    */       try {
/* 69 */         webhook.execute();
/* 70 */       } catch (IOException e) {
/*    */         
/* 72 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\guard\SpamKoruma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */