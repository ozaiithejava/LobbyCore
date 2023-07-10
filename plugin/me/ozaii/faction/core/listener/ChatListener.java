/*    */ package me.ozaii.faction.core.listener;
/*    */ 
/*    */ import java.net.SocketException;
/*    */ import java.net.UnknownHostException;
/*    */ import java.time.LocalDateTime;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import me.ozaii.faction.core.Plugin;
/*    */ import me.ozaii.faction.core.utils.WebhookUtil;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ 
/*    */ 
/*    */ public class ChatListener
/*    */   implements Listener
/*    */ {
/* 17 */   LocalDateTime now = LocalDateTime.now();
/* 18 */   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
/* 19 */   String timestamp = this.now.format(this.formatter);
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerChat(AsyncPlayerChatEvent event) throws UnknownHostException, SocketException {
/* 23 */     String playerName = event.getPlayer().getName();
/* 24 */     String message = event.getMessage();
/*    */ 
/*    */     
/* 27 */     String content = String.valueOf(playerName) + ": " + message;
/*    */ 
/*    */     
/* 30 */     String title = "Oyuncu Sohbeti";
/* 31 */     String description = content;
/* 32 */     String color = "#ff0000";
/*    */     
/* 34 */     WebhookUtil.sendMessage(String.valueOf(this.timestamp) + " " + playerName + " : " + message, Plugin.chathook);
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\listener\ChatListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */