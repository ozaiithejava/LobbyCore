/*    */ package me.ozaii.faction.core.listener;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ 
/*    */ public class ReachBlocker
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onPlayerMove(PlayerMoveEvent event) {
/* 13 */     Player player = event.getPlayer();
/* 14 */     double reachLimit = 3.0D;
/*    */     
/* 16 */     double playerReach = player.getLocation().distance(event.getTo());
/*    */     
/* 18 */     if (playerReach > reachLimit)
/* 19 */       event.setTo(event.getFrom()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\listener\ReachBlocker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */