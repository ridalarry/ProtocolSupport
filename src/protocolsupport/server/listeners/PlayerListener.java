/*    */ package protocolsupport.server.listeners;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*    */ import protocolsupport.api.ProtocolSupportAPI;
/*    */ import protocolsupport.api.ProtocolVersion;
/*    */ import protocolsupport.api.tab.TabAPI;
/*    */ 
/*    */ public class PlayerListener implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onShift(PlayerToggleSneakEvent event)
/*    */   {
/* 18 */     Player player = event.getPlayer();
/* 19 */     if ((player.isInsideVehicle()) && (ProtocolSupportAPI.getProtocolVersion(player).isBeforeOrEq(ProtocolVersion.MINECRAFT_1_5_2))) {
/* 20 */       player.leaveVehicle();
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler(ignoreCancelled=true)
/*    */   public void onVehicleInteract(PlayerInteractEntityEvent event) {
/* 26 */     Player player = event.getPlayer();
/* 27 */     if ((player.isInsideVehicle()) && (ProtocolSupportAPI.getProtocolVersion(player).isBeforeOrEq(ProtocolVersion.MINECRAFT_1_5_2)) && 
/* 28 */       (player.getVehicle().equals(event.getRightClicked()))) {
/* 29 */       player.leaveVehicle();
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onJoin(PlayerJoinEvent event)
/*    */   {
/* 36 */     TabAPI.sendHeaderFooter(event.getPlayer(), TabAPI.getDefaultHeader(), TabAPI.getDefaultFooter());
/*    */   }
/*    */ }

