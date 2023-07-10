/*    */ package me.ozaii.faction.core.listener;
/*    */ 
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ 
/*    */ 
/*    */ public class FlightProtection
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onPlayerMove(PlayerMoveEvent event) {
/* 15 */     Player player = event.getPlayer();
/*    */     
/* 17 */     if (player.getGameMode() != GameMode.SURVIVAL) {
/*    */       return;
/*    */     }
/*    */     
/* 21 */     if (player.getLocation().getY() > 256.0D)
/* 22 */       player.teleport(event.getFrom()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\listener\FlightProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */