// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package model;

public class Author {
   private int id;
   private String name;
   private int bookPublished;

   public String toString() {
      return "Author{id=" + this.id + ", name='" + this.name + "', bookPublished=" + this.bookPublished + "}";
   }

   public Author(String name, int bookPublished) {
      this.name = name;
      this.bookPublished = bookPublished;
   }

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getBookPublished() {
      return this.bookPublished;
   }

   public void setBookPublished(int bookPublished) {
      this.bookPublished = bookPublished;
   }
}
