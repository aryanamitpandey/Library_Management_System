// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package Services;

import Dao.AuthorDao;
import Dao.BookDao;
import java.util.Scanner;
import model.Author;
import model.Book;

public class BookServices {
   private BookDao bookDao;

   public BookServices(BookDao bookDao) {
      this.bookDao = bookDao;
   }

   public void addBookServices() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Book Name:-");
      String name = sc.nextLine();
      System.out.println("Enter Book Edition:-");
      String edition = sc.nextLine();
      System.out.println("Enter Book Price:-");
      double price = sc.nextDouble();
      sc.nextLine();
      System.out.println("Enter book Author Name:-");
      String authorName = sc.nextLine();
      Author author = AuthorDao.getAuthorByName(authorName);
      if (author != null) {
         Book newBook = new Book(name, price, edition, author);
         this.bookDao.addBook(newBook);
      }
   }

   public void deleteBookServices() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Book Name to Delete: ");
      String bookName = sc.nextLine();
      if (bookName.length() != 0) {
         this.bookDao.deleteBookByName(bookName);
      }
   }

   public void getBookByNameService(String bookName) {
      this.bookDao.getBookByName(bookName);
      System.out.println("Book in Library");
   }

   public void getAllBooksService() {
      this.bookDao.getAllBook();
   }

   public void updateBookService() {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Book Name to be updated ");
      String bookName = sc.nextLine();
      System.out.println("Enter Book Price to be updated ");
      double bookPrice = sc.nextDouble();
      sc.nextLine();
      System.out.println("Enter Book Edition to be updated ");
      String bookEdition = sc.nextLine();
      this.bookDao.updateBookByEditionAndPrice(bookName, bookEdition, bookPrice);
   }
}
