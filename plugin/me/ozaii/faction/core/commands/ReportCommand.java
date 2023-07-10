/*    */ package me.ozaii.faction.core.commands;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.time.LocalDateTime;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import java.util.HashMap;
/*    */ import me.ozaii.faction.core.Plugin;
/*    */ import me.ozaii.faction.core.utils.DiscordWebhook;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReportCommand
/*    */   implements CommandExecutor
/*    */ {
/* 24 */   private final String REPORT_DIRECTORY = "PlayersLogs/Reports";
/*    */   
/* 26 */   private HashMap<Player, Integer> reportCount = new HashMap<>();
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 29 */     if (!(sender instanceof Player)) {
/* 30 */       sender.sendMessage("Bu komut sadece oyuncular tarafından kullanılabilir.");
/* 31 */       return true;
/*    */     } 
/* 33 */     Player reporter = (Player)sender;
/* 34 */     if (args.length < 2) {
/* 35 */       reporter.sendMessage("Doğru kullanım: /report <oyuncu-adi> <sebep>");
/* 36 */       return true;
/*    */     } 
/* 38 */     String reportedPlayerName = args[0];
/* 39 */     String reason = String.join(" ", (CharSequence[])args).substring(args[0].length() + 1);
/* 40 */     if (this.reportCount.get(reporter) == null)
/* 41 */       this.reportCount.put(reporter, Integer.valueOf(0)); 
/* 42 */     if (((Integer)this.reportCount.get(reporter)).intValue() >= 3) {
/* 43 */       reporter.sendMessage(ChatColor.RED + "Günlük rapor sınırını aştınız.");
/* 44 */       return true;
/*    */     } 
/* 46 */     File reportDir = new File("PlayersLogs/Reports");
/* 47 */     if (!reportDir.exists())
/* 48 */       reportDir.mkdirs(); 
/* 49 */     File reportFile = new File("PlayersLogs/Reports" + reporter.getName() + ".json");
/*    */     try {
/* 51 */       if (!reportFile.exists())
/* 52 */         reportFile.createNewFile(); 
/* 53 */       FileWriter writer = new FileWriter(reportFile, true);
/* 54 */       LocalDateTime date = LocalDateTime.now();
/* 55 */       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
/* 56 */       String formattedDate = date.format(formatter);
/* 57 */       String report = "{\"reporter\": \"" + reporter.getName() + "\", \"reported_player\": \"" + reportedPlayerName + "\", \"reason\": \"" + reason + "\", \"date\": \"" + formattedDate + "\", \"developed by\": \"ozaii\"}\n";
/* 58 */       writer.write(report);
/* 59 */       writer.flush();
/* 60 */       writer.close();
/*    */       
/* 62 */       DiscordWebhook webhook = new DiscordWebhook(Plugin.reporthook);
/*    */       
/* 64 */       webhook.addEmbed((new DiscordWebhook.EmbedObject())
/* 65 */           .setTitle("(RAPOR SISTEMI)")
/* 66 */           .setImage("https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png")
/* 67 */           .setColor(Color.GREEN)
/* 68 */           .addField("Rapor eden:", reporter.getName(), true)
/* 69 */           .addField("Rapor edilen kişi: ", reportedPlayerName, true)
/* 70 */           .addField("Sebebi: ", reason, true)
/* 71 */           .setFooter("Developed by: ozaii", "https://cdn.discordapp.com/attachments/1120324929234149436/1120325398367047720/20230610_000652_0000.png"));
/*    */       
/*    */       try {
/* 74 */         webhook.execute();
/* 75 */       } catch (IOException e) {
/*    */         
/* 77 */         e.printStackTrace();
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 82 */       reporter.sendMessage(String.valueOf(Plugin.prefix) + ChatColor.GREEN + "Rapor başarıyla kaydedildi.");
/* 83 */       int count = ((Integer)this.reportCount.get(reporter)).intValue() + 1;
/* 84 */       this.reportCount.put(reporter, Integer.valueOf(count));
/* 85 */     } catch (IOException e) {
/* 86 */       e.printStackTrace();
/* 87 */       reporter.sendMessage(String.valueOf(Plugin.prefix) + ChatColor.RED + "Rapor kaydedilirken bir hata oluştu.");
/*    */     } 
/* 89 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\commands\ReportCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */