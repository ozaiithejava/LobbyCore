/*     */ package me.ozaii.faction.core.guard;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import me.ozaii.faction.core.Plugin;
/*     */ import me.ozaii.faction.core.utils.DiscordWebhook;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KufurEngelleme
/*     */   implements Listener
/*     */ {
/*     */   private final JavaPlugin plugin;
/*     */   private List<String> kufurListesi;
/*     */   
/*     */   public KufurEngelleme(JavaPlugin plugin) {
/*  31 */     this.plugin = plugin;
/*     */   }
/*     */   
/*     */   public void start() {
/*  35 */     this.kufurListesi = loadKufurListesi();
/*  36 */     this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin)this.plugin);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerChat(AsyncPlayerChatEvent event) {
/*  41 */     String message = event.getMessage();
/*  42 */     for (String kufur : this.kufurListesi) {
/*  43 */       Pattern pattern = Pattern.compile("\\b" + kufur + "\\b", 2);
/*  44 */       Matcher matcher = pattern.matcher(message);
/*  45 */       if (matcher.find()) {
/*  46 */         event.setCancelled(true);
/*  47 */         event.getPlayer().sendMessage(
/*  48 */             String.valueOf(Plugin.prefix) + ChatColor.RED + "[ASİSTAN] " + ChatColor.DARK_GREEN + "Küfür tespit ettim!");
/*  49 */         this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), 
/*  50 */             "mute " + event.getPlayer().getName() + " 3h Küfür etmek yasaktır!");
/*  51 */         this.plugin.getLogger().info("Oyuncu " + event.getPlayer().getName() + 
/*  52 */             " tarafından küfürlü kelime tespit edildi: " + kufur);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  59 */         DiscordWebhook webhook = new DiscordWebhook(Plugin.kufurhook);
/*     */         
/*  61 */         webhook.addEmbed((new DiscordWebhook.EmbedObject())
/*  62 */             .setTitle("(KUFUR ENGELLEME SISTEMI)")
/*  63 */             .setImage("https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png")
/*  64 */             .setColor(Color.ORANGE)
/*  65 */             .addField("Username", event.getPlayer().getName(), true)
/*  66 */             .addField("Sözcük: ", kufur, true)
/*  67 */             .setFooter("Developed by: ozaii", "https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png"));
/*     */         
/*     */         try {
/*  70 */           webhook.execute();
/*  71 */         } catch (IOException e) {
/*     */           
/*  73 */           e.printStackTrace();
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> loadKufurListesi() {
/*  84 */     List<String> kufurListesi = new ArrayList<>();
/*     */     try {
/*  86 */       URL url = new URL(Plugin.kufurlistesi);
/*  87 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/*     */       String satir;
/*  89 */       while ((satir = reader.readLine()) != null) {
/*  90 */         if (satir.trim().isEmpty())
/*     */           continue; 
/*  92 */         kufurListesi.add(satir.trim().toLowerCase());
/*     */       } 
/*  94 */       reader.close();
/*  95 */       this.plugin.getLogger().info("Küfür listesi yüklendi. Toplam " + kufurListesi.size() + 
/*  96 */           " küfür kelimesi var.");
/*  97 */     } catch (IOException e) {
/*  98 */       this.plugin.getLogger()
/*  99 */         .severe("Küfür listesi yüklenirken bir hata oluştu: " + e.getMessage());
/*     */     } 
/* 101 */     return kufurListesi;
/*     */   }
/*     */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\guard\KufurEngelleme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */