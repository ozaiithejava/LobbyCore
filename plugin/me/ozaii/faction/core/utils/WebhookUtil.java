/*    */ package me.ozaii.faction.core.utils;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintWriter;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLEncoder;
/*    */ 
/*    */ 
/*    */ public class WebhookUtil
/*    */ {
/*    */   public static void sendMessage(String message, String webhook) {
/* 15 */     PrintWriter out = null;
/* 16 */     BufferedReader in = null;
/* 17 */     StringBuilder result = new StringBuilder();
/*    */     try {
/* 19 */       URL realUrl = new URL(webhook);
/* 20 */       URLConnection conn = realUrl.openConnection();
/* 21 */       conn.setRequestProperty("accept", "*/*");
/* 22 */       conn.setRequestProperty("connection", "Keep-Alive");
/* 23 */       conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
/* 24 */       conn.setDoOutput(true);
/* 25 */       conn.setDoInput(true);
/* 26 */       out = new PrintWriter(conn.getOutputStream());
/* 27 */       String postData = String.valueOf(String.valueOf(URLEncoder.encode("content", "UTF-8"))) + "=" + URLEncoder.encode(message, "UTF-8");
/* 28 */       out.print(postData);
/* 29 */       out.flush();
/* 30 */       in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*    */       String line;
/* 32 */       while ((line = in.readLine()) != null)
/* 33 */         result.append("/n").append(line); 
/* 34 */     } catch (Exception e) {
/* 35 */       e.printStackTrace();
/*    */     } finally {
/*    */       try {
/* 38 */         if (out != null)
/* 39 */           out.close(); 
/* 40 */         if (in != null)
/* 41 */           in.close(); 
/* 42 */       } catch (IOException ex) {
/* 43 */         ex.printStackTrace();
/*    */       } 
/*    */     } 
/* 46 */     System.out.println(result.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\cor\\utils\WebhookUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */