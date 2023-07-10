/*    */ package me.ozaii.faction.core.guard;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import me.ozaii.faction.core.Plugin;
/*    */ import me.ozaii.faction.core.utils.DiscordWebhook;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AdEngelleme
/*    */   implements Listener
/*    */ {
/*    */   private final JavaPlugin plugin;
/*    */   private List<String> reklamListesi;
/*    */   
/*    */   public AdEngelleme(JavaPlugin plugin) {
/* 31 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   public void start() {
/* 35 */     this.reklamListesi = loadReklamListesi();
/* 36 */     this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerChat(AsyncPlayerChatEvent event) {
/* 41 */     String message = event.getMessage();
/* 42 */     for (String reklam : this.reklamListesi) {
/* 43 */       Pattern pattern = Pattern.compile("\\b" + reklam + "\\b", 2);
/* 44 */       Matcher matcher = pattern.matcher(message);
/* 45 */       if (matcher.find()) {
/* 46 */         event.setCancelled(true);
/* 47 */         event.getPlayer().sendMessage(String.valueOf(Plugin.prefix) + ChatColor.RED + "[ASİSTAN] " + ChatColor.DARK_GREEN + "Reklam tespit ettim!");
/* 48 */         this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "mute " + event.getPlayer().getName() + " 999999d Reklam Yapmak yasaktır!");
/* 49 */         this.plugin.getLogger().info("Oyuncu " + event.getPlayer().getName() + " tarafından reklam tespit edildi: " + reklam);
/*    */ 
/*    */         
/* 52 */         DiscordWebhook webhook = new DiscordWebhook(Plugin.reklamhook);
/*    */         
/* 54 */         webhook.addEmbed((new DiscordWebhook.EmbedObject())
/* 55 */             .setTitle("(REKLAM ENGELLEME SISTEMI)")
/* 56 */             .setImage("https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png")
/* 57 */             .setColor(Color.RED)
/* 58 */             .addField("Username", event.getPlayer().getName(), true)
/* 59 */             .addField("Sözcük: ", reklam, true)
/* 60 */             .setFooter("Developed by: ozaii", "https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png"));
/*    */         
/*    */         try {
/* 63 */           webhook.execute();
/* 64 */         } catch (IOException e) {
/*    */           
/* 66 */           e.printStackTrace();
/*    */         } 
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private List<String> loadReklamListesi() {
/* 76 */     List<String> reklamListesi = new ArrayList<>();
/*    */     try {
/* 78 */       URL url = new URL(Plugin.reklamlistesi);
/* 79 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/*    */       String satir;
/* 81 */       while ((satir = reader.readLine()) != null) {
/* 82 */         if (satir.trim().isEmpty())
/*    */           continue; 
/* 84 */         reklamListesi.add(satir.trim().toLowerCase());
/*    */       } 
/* 86 */       reader.close();
/* 87 */       this.plugin.getLogger().info("Reklam listesi yüklendi. Toplam " + reklamListesi.size() + " reklam kelimesi var.");
/* 88 */     } catch (IOException e) {
/* 89 */       this.plugin.getLogger().severe("Reklam listesi yüklenirken bir hata oluştu: " + e.getMessage());
/*    */     } 
/* 91 */     return reklamListesi;
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\guard\AdEngelleme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */