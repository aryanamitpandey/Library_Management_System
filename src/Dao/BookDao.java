// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package Dao;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Author;
import model.Book;
import utils.DbConnection;

public class BookDao {
   public BookDao() {
   }

   public void addBook(Book bookObject) {
      try {
         Connection con = DbConnection.getConnection();
         String insertQuery = "insert into book_tb (name, edition,price,author_id) values (?,?,?,?)";
         PreparedStatement ps = con.prepareStatement(insertQuery);
         ps.setString(1, bookObject.getName());
         ps.setString(2, bookObject.getEdition());
         ps.setDouble(3, bookObject.getPrice());
         ps.setInt(4, bookObject.getAuthor().getId());
         int rowUpdate = ps.executeUpdate();
         System.out.println("Row updated ! " + rowUpdate);
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void testMethod(Connection con) {
      String query = "Select * from book_tb";

      try {
         Statement st = con.createStatement();

         try {
            st.executeQuery(query);
            System.out.println("Successfully Connected: ");
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
               PrintStream var10000 = System.out;
               String var10001 = rs.getString("name");
               var10000.println(var10001 + " " + rs.getInt("price"));
            }
         } catch (Throwable var7) {
            if (st != null) {
               try {
                  st.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (st != null) {
            st.close();
         }
      } catch (Exception e) {
         System.out.println("Test Failed !");
         e.printStackTrace();
      }

   }

   public Book getBookByName(String bookName) {
      String sql = "Select * from book_tb where ( name ) =?";
      Book retrivedBook = null;
      String nameRetrived = null;
      double priceRetrived = (double)0.0F;
      String editionRetrived = null;
      Author authorRetrived = null;
      int authorId = 0;

      try {
         Connection connection = DbConnection.getConnection();

         try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try {
               preparedStatement.setString(1, bookName);

               for(ResultSet resultSet = preparedStatement.executeQuery(); resultSet.next(); retrivedBook = new Book(nameRetrived, priceRetrived, editionRetrived, authorRetrived)) {
                  nameRetrived = resultSet.getString("name");
                  priceRetrived = resultSet.getDouble("price");
                  editionRetrived = resultSet.getString("edition");
                  authorId = resultSet.getInt("author_id");
                  authorRetrived = new Author("Unknown", 0);
                  authorRetrived.setId(authorId);
               }
            } catch (Throwable var16) {
               if (preparedStatement != null) {
                  try {
                     preparedStatement.close();
                  } catch (Throwable var15) {
                     var16.addSuppressed(var15);
                  }
               }

               throw var16;
            }

            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } catch (Throwable var17) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var14) {
                  var17.addSuppressed(var14);
               }
            }

            throw var17;
         }

         if (connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return retrivedBook;
   }

   public void deleteBookByName(String bookName) {
      String delquery = "delete from book_tb where ( name ) = ?";

      try {
         Connection connection = DbConnection.getConnection();

         try {
            PreparedStatement preparedStatement = connection.prepareStatement(delquery);

            try {
               preparedStatement.setString(1, bookName);
               int rowUpdate = preparedStatement.executeUpdate();
               System.out.println("No of rows Updated : " + rowUpdate);
            } catch (Throwable var9) {
               if (preparedStatement != null) {
                  try {
                     preparedStatement.close();
                  } catch (Throwable var8) {
                     var9.addSuppressed(var8);
                  }
               }

               throw var9;
            }

            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } catch (Throwable var10) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var7) {
                  var10.addSuppressed(var7);
               }
            }

            throw var10;
         }

         if (connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void getAllBook() {
      String query = "Select * from book_tb";

      try {
         Connection connection = DbConnection.getConnection();

         try {
            PreparedStatement ps = connection.prepareStatement(query);

            try {
               ResultSet rs = ps.executeQuery();
               System.out.println();
               System.out.println("╔══════════════════════════════════════════════════════════╗");
               System.out.println("║                       Book Records                       ║");
               System.out.println("╚══════════════════════════════════════════════════════════╝");
               System.out.printf("%-5s %-20s %-10s %-15s %-10s%n", "ID", "BOOK", "PRICE", "EDITION", "AUTHOR");
               System.out.println("------------------------------------------------------------");

               while(rs.next()) {
                  System.out.printf("%-5s %-20s %-10s %-15s %-10s%n", rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("edition"), rs.getInt("author_id"));
               }

               System.out.println("------------------------------------------------------------");
               System.out.println();
            } catch (Throwable var8) {
               if (ps != null) {
                  try {
                     ps.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }
               }

               throw var8;
            }

            if (ps != null) {
               ps.close();
            }
         } catch (Throwable var9) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var6) {
                  var9.addSuppressed(var6);
               }
            }

            throw var9;
         }

         if (connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void updateBookByEditionAndPrice(String bookName, String bookEdition, double price) {
      String query = "update book_tb set edition = ?, price = ? where name = ?";

      try {
         Connection conn = DbConnection.getConnection();

         try {
            PreparedStatement ps = conn.prepareStatement(query);

            try {
               ps.setString(1, bookEdition);
               ps.setDouble(2, price);
               ps.setString(3, bookName);
               int rowUpdate = ps.executeUpdate();
               System.out.println("Row updated ! " + rowUpdate);
            } catch (Throwable var12) {
               if (ps != null) {
                  try {
                     ps.close();
                  } catch (Throwable var11) {
                     var12.addSuppressed(var11);
                  }
               }

               throw var12;
            }

            if (ps != null) {
               ps.close();
            }
         } catch (Throwable var13) {
            if (conn != null) {
               try {
                  conn.close();
               } catch (Throwable var10) {
                  var13.addSuppressed(var10);
               }
            }

            throw var13;
         }

         if (conn != null) {
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
}
