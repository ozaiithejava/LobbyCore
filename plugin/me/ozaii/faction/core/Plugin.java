/*     */ package me.ozaii.faction.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import me.ozaii.faction.core.commands.ReportCommand;
/*     */ import me.ozaii.faction.core.guard.AdEngelleme;
/*     */ import me.ozaii.faction.core.guard.KufurEngelleme;
/*     */ import me.ozaii.faction.core.guard.SpamKoruma;
/*     */ import me.ozaii.faction.core.listener.ChatListener;
/*     */ import me.ozaii.faction.core.listener.FlightProtection;
/*     */ import me.ozaii.faction.core.listener.ReachBlocker;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ public class Plugin extends JavaPlugin {
/*     */   private static Plugin instance;
/*  20 */   private ArrayList<UUID> djPeople = new ArrayList<>();
/*     */   
/*     */   public static String kufurhook;
/*     */   
/*     */   public static String chathook;
/*     */   
/*     */   public static String reporthook;
/*     */   
/*     */   public static String prefix;
/*     */   
/*     */   public static String reklamhook;
/*     */   
/*     */   public static String spamhook;
/*     */   
/*     */   public static String reklamlistesi;
/*     */   public static String kufurlistesi;
/*     */   
/*     */   public void onEnable() {
/*  38 */     instance = this;
/*     */     
/*  40 */     getLogger().info(this.greenLogo);
/*     */     
/*  42 */     onCommands();
/*     */     
/*  44 */     onConfigLoader();
/*     */     
/*  46 */     onListeners();
/*  47 */     (new AdEngelleme(this)).start();
/*  48 */     (new KufurEngelleme(this)).start();
/*  49 */     (new SpamKoruma(this)).start();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCommands() {
/*  55 */     getCommand("report").setExecutor((CommandExecutor)new ReportCommand());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onListeners() {
/*  60 */     getServer().getPluginManager().registerEvents((Listener)new ChatListener(), (org.bukkit.plugin.Plugin)this);
/*  61 */     getServer().getPluginManager().registerEvents((Listener)new ReachBlocker(), (org.bukkit.plugin.Plugin)this);
/*  62 */     getServer().getPluginManager().registerEvents((Listener)new FlightProtection(), (org.bukkit.plugin.Plugin)this);
/*     */   }
/*     */   
/*     */   public void onConfigLoader() {
/*  66 */     getConfig().options().copyDefaults(true);
/*  67 */     saveDefaultConfig();
/*     */     
/*  69 */     FileConfiguration config = getConfig();
/*     */     
/*  71 */     spamhook = config.getString("spamhook");
/*  72 */     reklamhook = config.getString("reklamhook");
/*  73 */     kufurhook = config.getString("kufurhook");
/*  74 */     chathook = config.getString("chathook");
/*  75 */     reporthook = config.getString("reporthook");
/*     */     
/*  77 */     prefix = ChatColor.RED + config.getString("prefix") + " ";
/*     */     
/*  79 */     reklamlistesi = config.getString("reklamlistesi");
/*  80 */     kufurlistesi = config.getString("kufurlistesi");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  85 */   String logo = "                            \n                        |__/                | $$                    | $$      \n /$$  /$$  /$$  /$$$$$$  /$$ /$$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$ | $$   /$$\n| $$ | $$ | $$ /$$__  $$| $$| $$__  $$ /$$__  $$ |____  $$ /$$__  $$| $$  /$$/\n| $$ | $$ | $$| $$$$$$$$| $$| $$  \\ $$| $$  | $$  /$$$$$$$| $$  \\__/| $$$$$$/ \n| $$ | $$ | $$| $$_____/| $$| $$  | $$| $$  | $$ /$$__  $$| $$      | $$_  $$ \n|  $$$$$/$$$$/|  $$$$$$$| $$| $$  | $$|  $$$$$$$|  $$$$$$$| $$      | $$ \\  $$\n \\_____/\\___/  \\_______/|__/|__/  |__/ \\_______/ \\_______/|__/      |__/  \\__/\n                                                                              \n                                                                              \n                                                                              ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   String greenLogo = "\033[32m" + this.logo + "\033[0m";
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 101 */     getLogger().info("\033[31mPlugin devre dışı bırakıldı.");
/*     */   }
/*     */   
/*     */   public static Plugin getInstance() {
/* 105 */     return instance;
/*     */   }
/*     */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\core\Plugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */