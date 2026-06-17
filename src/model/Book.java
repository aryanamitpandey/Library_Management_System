// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package model;

public class Book {
   private int id;
   private String name;
   private double price;
   private String edition;
   private Author author;

   public String toString() {
      int var10000 = this.id;
      return "Book{id=" + var10000 + ", name='" + this.name + "', price=" + this.price + ", edition='" + this.edition + "', author=" + String.valueOf(this.author) + "}";
   }

   public Author getAuthor() {
      return this.author;
   }

   public void setAuthor(Author author) {
      this.author = author;
   }

   public Book(String name, double price, String edition, Author author) {
      this.name = name;
      this.price = price;
      this.edition = edition;
      this.author = author;
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

   public double getPrice() {
      return this.price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public String getEdition() {
      return this.edition;
   }

   public void setEdition(String edition) {
      this.edition = edition;
   }
}
