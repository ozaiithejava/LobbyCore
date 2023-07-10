/*     */ package me.ozaii.faction.core.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Array;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiscordWebhook
/*     */ {
/*     */   private final String url;
/*     */   private String content;
/*     */   private String username;
/*     */   private String avatarUrl;
/*     */   private boolean tts;
/*  29 */   private List<EmbedObject> embeds = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiscordWebhook(String url) {
/*  37 */     this.url = url;
/*     */   }
/*     */   
/*     */   public void setContent(String content) {
/*  41 */     this.content = content;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  45 */     this.username = username;
/*     */   }
/*     */   
/*     */   public void setAvatarUrl(String avatarUrl) {
/*  49 */     this.avatarUrl = avatarUrl;
/*     */   }
/*     */   
/*     */   public void setTts(boolean tts) {
/*  53 */     this.tts = tts;
/*     */   }
/*     */   
/*     */   public void addEmbed(EmbedObject embed) {
/*  57 */     this.embeds.add(embed);
/*     */   }
/*     */   
/*     */   public void execute() throws IOException {
/*  61 */     if (this.content == null && this.embeds.isEmpty()) {
/*  62 */       throw new IllegalArgumentException("Set content or add at least one EmbedObject");
/*     */     }
/*     */     
/*  65 */     JSONObject json = new JSONObject(null);
/*     */     
/*  67 */     json.put("content", this.content);
/*  68 */     json.put("username", this.username);
/*  69 */     json.put("avatar_url", this.avatarUrl);
/*  70 */     json.put("tts", Boolean.valueOf(this.tts));
/*     */     
/*  72 */     if (!this.embeds.isEmpty()) {
/*  73 */       List<JSONObject> embedObjects = new ArrayList<>();
/*     */       
/*  75 */       for (EmbedObject embed : this.embeds) {
/*  76 */         JSONObject jsonEmbed = new JSONObject(null);
/*     */         
/*  78 */         jsonEmbed.put("title", embed.getTitle());
/*  79 */         jsonEmbed.put("description", embed.getDescription());
/*  80 */         jsonEmbed.put("url", embed.getUrl());
/*     */         
/*  82 */         if (embed.getColor() != null) {
/*  83 */           Color color = embed.getColor();
/*  84 */           int rgb = color.getRed();
/*  85 */           rgb = (rgb << 8) + color.getGreen();
/*  86 */           rgb = (rgb << 8) + color.getBlue();
/*     */           
/*  88 */           jsonEmbed.put("color", Integer.valueOf(rgb));
/*     */         } 
/*     */         
/*  91 */         EmbedObject.Footer footer = embed.getFooter();
/*  92 */         EmbedObject.Image image = embed.getImage();
/*  93 */         EmbedObject.Thumbnail thumbnail = embed.getThumbnail();
/*  94 */         EmbedObject.Author author = embed.getAuthor();
/*  95 */         List<EmbedObject.Field> fields = embed.getFields();
/*     */         
/*  97 */         if (footer != null) {
/*  98 */           JSONObject jsonFooter = new JSONObject(null);
/*     */           
/* 100 */           jsonFooter.put("text", footer.getText());
/* 101 */           jsonFooter.put("icon_url", footer.getIconUrl());
/* 102 */           jsonEmbed.put("footer", jsonFooter);
/*     */         } 
/*     */         
/* 105 */         if (image != null) {
/* 106 */           JSONObject jsonImage = new JSONObject(null);
/*     */           
/* 108 */           jsonImage.put("url", image.getUrl());
/* 109 */           jsonEmbed.put("image", jsonImage);
/*     */         } 
/*     */         
/* 112 */         if (thumbnail != null) {
/* 113 */           JSONObject jsonThumbnail = new JSONObject(null);
/*     */           
/* 115 */           jsonThumbnail.put("url", thumbnail.getUrl());
/* 116 */           jsonEmbed.put("thumbnail", jsonThumbnail);
/*     */         } 
/*     */         
/* 119 */         if (author != null) {
/* 120 */           JSONObject jsonAuthor = new JSONObject(null);
/*     */           
/* 122 */           jsonAuthor.put("name", author.getName());
/* 123 */           jsonAuthor.put("url", author.getUrl());
/* 124 */           jsonAuthor.put("icon_url", author.getIconUrl());
/* 125 */           jsonEmbed.put("author", jsonAuthor);
/*     */         } 
/*     */         
/* 128 */         List<JSONObject> jsonFields = new ArrayList<>();
/* 129 */         for (EmbedObject.Field field : fields) {
/* 130 */           JSONObject jsonField = new JSONObject(null);
/*     */           
/* 132 */           jsonField.put("name", field.getName());
/* 133 */           jsonField.put("value", field.getValue());
/* 134 */           jsonField.put("inline", Boolean.valueOf(field.isInline()));
/*     */           
/* 136 */           jsonFields.add(jsonField);
/*     */         } 
/*     */         
/* 139 */         jsonEmbed.put("fields", jsonFields.toArray());
/* 140 */         embedObjects.add(jsonEmbed);
/*     */       } 
/*     */       
/* 143 */       json.put("embeds", embedObjects.toArray());
/*     */     } 
/*     */     
/* 146 */     URL url = new URL(this.url);
/* 147 */     HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
/* 148 */     connection.addRequestProperty("Content-Type", "application/json");
/* 149 */     connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
/* 150 */     connection.setDoOutput(true);
/* 151 */     connection.setRequestMethod("POST");
/*     */     
/* 153 */     OutputStream stream = connection.getOutputStream();
/* 154 */     stream.write(json.toString().getBytes());
/* 155 */     stream.flush();
/* 156 */     stream.close();
/*     */     
/* 158 */     connection.getInputStream().close();
/* 159 */     connection.disconnect();
/*     */   }
/*     */   
/*     */   public static class EmbedObject
/*     */   {
/*     */     private String title;
/*     */     private String description;
/*     */     private String url;
/*     */     private Color color;
/*     */     private Footer footer;
/*     */     private Thumbnail thumbnail;
/*     */     private Image image;
/*     */     private Author author;
/* 172 */     private List<Field> fields = new ArrayList<>();
/*     */     
/*     */     public String getTitle() {
/* 175 */       return this.title;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 179 */       return this.description;
/*     */     }
/*     */     
/*     */     public String getUrl() {
/* 183 */       return this.url;
/*     */     }
/*     */     
/*     */     public Color getColor() {
/* 187 */       return this.color;
/*     */     }
/*     */     
/*     */     public Footer getFooter() {
/* 191 */       return this.footer;
/*     */     }
/*     */     
/*     */     public Thumbnail getThumbnail() {
/* 195 */       return this.thumbnail;
/*     */     }
/*     */     
/*     */     public Image getImage() {
/* 199 */       return this.image;
/*     */     }
/*     */     
/*     */     public Author getAuthor() {
/* 203 */       return this.author;
/*     */     }
/*     */     
/*     */     public List<Field> getFields() {
/* 207 */       return this.fields;
/*     */     }
/*     */     
/*     */     public EmbedObject setTitle(String title) {
/* 211 */       this.title = title;
/* 212 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setDescription(String description) {
/* 216 */       this.description = description;
/* 217 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setUrl(String url) {
/* 221 */       this.url = url;
/* 222 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setColor(Color color) {
/* 226 */       this.color = color;
/* 227 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setFooter(String text, String icon) {
/* 231 */       this.footer = new Footer(text, icon, null);
/* 232 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setThumbnail(String url) {
/* 236 */       this.thumbnail = new Thumbnail(url, null);
/* 237 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setImage(String url) {
/* 241 */       this.image = new Image(url, null);
/* 242 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject setAuthor(String name, String url, String icon) {
/* 246 */       this.author = new Author(name, url, icon, null);
/* 247 */       return this;
/*     */     }
/*     */     
/*     */     public EmbedObject addField(String name, String value, boolean inline) {
/* 251 */       this.fields.add(new Field(name, value, inline, null));
/* 252 */       return this;
/*     */     }
/*     */     
/*     */     private class Footer {
/*     */       private String text;
/*     */       private String iconUrl;
/*     */       
/*     */       private Footer(String text, String iconUrl) {
/* 260 */         this.text = text;
/* 261 */         this.iconUrl = iconUrl;
/*     */       }
/*     */       
/*     */       private String getText() {
/* 265 */         return this.text;
/*     */       }
/*     */       
/*     */       private String getIconUrl() {
/* 269 */         return this.iconUrl;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Thumbnail {
/*     */       private String url;
/*     */       
/*     */       private Thumbnail(String url) {
/* 277 */         this.url = url;
/*     */       }
/*     */       
/*     */       private String getUrl() {
/* 281 */         return this.url;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Image {
/*     */       private String url;
/*     */       
/*     */       private Image(String url) {
/* 289 */         this.url = url;
/*     */       }
/*     */       
/*     */       private String getUrl() {
/* 293 */         return this.url;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Author {
/*     */       private String name;
/*     */       private String url;
/*     */       private String iconUrl;
/*     */       
/*     */       private Author(String name, String url, String iconUrl) {
/* 303 */         this.name = name;
/* 304 */         this.url = url;
/* 305 */         this.iconUrl = iconUrl;
/*     */       }
/*     */       
/*     */       private String getName() {
/* 309 */         return this.name;
/*     */       }
/*     */       
/*     */       private String getUrl() {
/* 313 */         return this.url;
/*     */       }
/*     */       
/*     */       private String getIconUrl() {
/* 317 */         return this.iconUrl;
/*     */       }
/*     */     }
/*     */     
/*     */     private class Field {
/*     */       private String name;
/*     */       private String value;
/*     */       private boolean inline;
/*     */       
/*     */       private Field(String name, String value, boolean inline) {
/* 327 */         this.name = name;
/* 328 */         this.value = value;
/* 329 */         this.inline = inline;
/*     */       }
/*     */       
/*     */       private String getName() {
/* 333 */         return this.name;
/*     */       }
/*     */       
/*     */       private String getValue() {
/* 337 */         return this.value;
/*     */       }
/*     */       
/*     */       private boolean isInline() {
/* 341 */         return this.inline; } } } private class Footer { private String text; private String iconUrl; private Footer(String text, String iconUrl) { this.text = text; this.iconUrl = iconUrl; } private String getText() { return this.text; } private String getIconUrl() { return this.iconUrl; } } private class Thumbnail { private String url; private Thumbnail(String url) { this.url = url; } private String getUrl() { return this.url; } } private class Image { private String url; private Image(String url) { this.url = url; } private String getUrl() { return this.url; } } private class Author { private String name; private String url; private String iconUrl; private Author(String name, String url, String iconUrl) { this.name = name; this.url = url; this.iconUrl = iconUrl; } private String getName() { return this.name; } private String getUrl() { return this.url; } private String getIconUrl() { return this.iconUrl; } } private class Field { private String name; private String value; private boolean inline; private boolean isInline() { return this.inline; } private Field(String name, String value, boolean inline) { this.name = name;
/*     */       this.value = value;
/*     */       this.inline = inline; } private String getName() { return this.name; } private String getValue() {
/*     */       return this.value;
/*     */     } }
/*     */    private class JSONObject {
/*     */     private final HashMap<String, Object> map; private JSONObject() {
/* 348 */       this.map = new HashMap<>();
/*     */     }
/*     */     void put(String key, Object value) {
/* 351 */       if (value != null) {
/* 352 */         this.map.put(key, value);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 358 */       StringBuilder builder = new StringBuilder();
/* 359 */       Set<Map.Entry<String, Object>> entrySet = this.map.entrySet();
/* 360 */       builder.append("{");
/*     */       
/* 362 */       int i = 0;
/* 363 */       for (Map.Entry<String, Object> entry : entrySet) {
/* 364 */         Object val = entry.getValue();
/* 365 */         builder.append(quote(entry.getKey())).append(":");
/*     */         
/* 367 */         if (val instanceof String) {
/* 368 */           builder.append(quote(String.valueOf(val)));
/* 369 */         } else if (val instanceof Integer) {
/* 370 */           builder.append(Integer.valueOf(String.valueOf(val)));
/* 371 */         } else if (val instanceof Boolean) {
/* 372 */           builder.append(val);
/* 373 */         } else if (val instanceof JSONObject) {
/* 374 */           builder.append(val.toString());
/* 375 */         } else if (val.getClass().isArray()) {
/* 376 */           builder.append("[");
/* 377 */           int len = Array.getLength(val);
/* 378 */           for (int j = 0; j < len; j++) {
/* 379 */             builder.append(Array.get(val, j).toString()).append((j != len - 1) ? "," : "");
/*     */           }
/* 381 */           builder.append("]");
/*     */         } 
/*     */         
/* 384 */         builder.append((++i == entrySet.size()) ? "}" : ",");
/*     */       } 
/*     */       
/* 387 */       return builder.toString();
/*     */     }
/*     */     
/*     */     private String quote(String string) {
/* 391 */       return "\"" + string + "\"";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\ozaii1337\Desktop\masaüstü\Rutex\RutexLobby\plugins\OCORE.jar!\me\ozaii\faction\cor\\utils\DiscordWebhook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */