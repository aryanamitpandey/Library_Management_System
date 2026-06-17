package controller;

import Dao.AuthorDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import Dao.BookDao;
import model.Author;
import model.Book;
import Services.BookServices;


import utils.DbConnection;

public class Main {
    public Main(){

    }

    public static void menu() {
        System.out.println();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║         MAIN MENU            ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1.  Author Management        ║");
        System.out.println("║ 2.  Book Management          ║");
        System.out.println("║ 3.  Exit Program             ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();
        System.out.print("Enter Choice : ");

    }

    public static void main(String[] args){
      new BookDao();
      new AuthorDao();
      Scanner sc = new Scanner(System.in);
      System.out.println("╔══════════════════════════════════════════════════════╗");
      System.out.println("║              LIBRARY MANAGEMENT SYSTEM               ║");
      System.out.println("╚══════════════════════════════════════════════════════╝");
      int choice = 0;

      label59:
      while(choice != 3) {
         menu();
         choice = sc.nextInt();
         sc.nextLine();
         switch (choice) {
            case 1:
               while(true) {
                  System.out.println();
                  System.out.println(" A)  Add Author \n B)  Delete Author \n C)  update Author \n D)  Show All Author \n E)  Go Back to Main Menu \n F)  Exit Program");
                  System.out.println();
                  System.out.print("Enter Choice : ");
                  char options = sc.next().charAt(0);
                  System.out.println();
                  sc.nextLine();
                  if (options == 'e' || options == 'E') {
                     System.out.println("Returning to Manin Menu...");
                     continue label59;
                  }

                   if (options == 'f' || options == 'F') {
                     System.out.println("Thank You ! Visit Again.");
                     System.exit(0);
                  }

                  authorCrud(options);

               }
            case 2:
               while(true) {
                  System.out.println();
                  System.out.println(" A)  Add Book \n B)  Delete Book \n C)  Update Edition or Price \n D)  Show All Books \n E)  Go Back to Main Menu \n F)  Exit Program");
                  System.out.println();
                  System.out.print("Enter Choice : ");
                  char option = sc.next().charAt(0);
                  System.out.println();
                  sc.nextLine();
                  if (option == 'e' || option == 'E') {
                     System.out.println("Returning to Manin Menu...");
                     continue label59;
                  }

                  if (option == 'f' || option == 'F') {
                     System.out.println("Thank You ! Visit Again.");
                     System.exit(0);
                  }

                  bookCrud(option);
               }
            case 3:
               System.out.println("Thank You ! Visit Again.");
               break;
            default:
               System.out.println("Invalid Input, Please Choice from Options !");
         }
      }

    }

    public static Book bookInput() throws SQLException {
      String URL = "jdbc:mysql://localhost:3306/Library_db";
      String USERNAME = "root";
      String PassWord = "root";
      Connection connection = DbConnection.getConnection();
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter Book Name :");
      String name = sc.nextLine();
      System.out.println("Enter Book Edition :");
      String edition = sc.nextLine();
      System.out.println("Enter Book Price :");
      double price = sc.nextDouble();
      sc.nextLine();
      System.out.println("Enter Book Author Name :");
      String authorName = sc.nextLine();
      Author author = AuthorDao.getAuthorByName(authorName);
      Book newBook = new Book(name, price, edition, author);
      return newBook;
    }

    public static void authorCrud(char option) {
      AuthorDao authorDao = new AuthorDao();
      Scanner sc = new Scanner(System.in);
      switch (option) {
         case 'A':
         case 'a':
            System.out.println("Enter Author Name :");
            String input1 = sc.nextLine();

            try {
               Connection con = DbConnection.getConnection();

               try {
                  authorDao.addAuthor(con, input1);
               } catch (Throwable var8) {
                  if (con != null) {
                     try {
                        con.close();
                     } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                     }
                  }

                  throw var8;
               }

               if (con != null) {
                  con.close();
               }
            } catch (SQLException e) {
               e.printStackTrace();
            }
            break;
         case 'B':
         case 'b':
            System.out.println("Enter Author Name to Delete :");
            String nameEntered = sc.nextLine();
            authorDao.delAuthor(nameEntered);
            break;
         case 'C':
         case 'c':
            System.out.println("Enter Previous Author Name :");
            String prevName = sc.nextLine();
            System.out.println("Enter New Author Name :");
            String updatedName = sc.nextLine();
            authorDao.updateAuthor(prevName, updatedName);
            break;
         case 'D':
         case 'd':
            authorDao.getAllAuthor();
            break;
         default:
            System.out.println("Invalid Input, Please Choose from Options !");
      }

    }

    public static void bookCrud(char option) {
      BookDao bookDao = new BookDao();
      BookServices bookServices = new BookServices(bookDao);
      new Scanner(System.in);
      switch (option) {
         case 'A':
         case 'a':
            bookServices.addBookServices();
            break;
         case 'B':
         case 'b':
            bookServices.deleteBookServices();
            break;
         case 'C':
         case 'c':
            bookServices.updateBookService();
            break;
         case 'D':
         case 'd':
            bookServices.getAllBooksService();
            break;
         default:
            System.out.println("Invalid Input, Please Choose from Options !");
        }

    }

}
        