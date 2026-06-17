// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package Dao;

import Exception.AuthorNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Author;
import utils.DbConnection;

public class AuthorDao {
   public AuthorDao() {
   }

   public void addAuthor(Connection connection, String author_name) {
      String sql = "insert into author_tb (name) values (?)";

      try {
         PreparedStatement preparedStatement = connection.prepareStatement(sql);

         try {
            preparedStatement.setString(1, author_name);
            int row = preparedStatement.executeUpdate();
            System.out.println("Row updated" + row);
         } catch (Throwable var8) {
            if (preparedStatement != null) {
               try {
                  preparedStatement.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (preparedStatement != null) {
            preparedStatement.close();
         }
      } catch (Exception e) {
         System.out.println("Exception occured in AddAuthor Block");
         e.printStackTrace();
      }

   }

   public static Author getAuthorByName(String authorName) {
      String sql = "select * from author_tb where ( name ) = ?";
      Author retriveAuthor = null;
      String nameRetrived = null;
      int bookPublishedRetrived = 0;
      int idRetrived = 0;

      try {
         Connection connection = DbConnection.getConnection();

         try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try {
               preparedStatement.setString(1, authorName);
               ResultSet resultSet = preparedStatement.executeQuery();
               if (!resultSet.next()) {
                  throw new AuthorNotFoundException();
               }

               idRetrived = resultSet.getInt("id");
               nameRetrived = resultSet.getString("name");
               bookPublishedRetrived = resultSet.getInt("book_published");
               retriveAuthor = new Author(nameRetrived, bookPublishedRetrived);
               retriveAuthor.setId(idRetrived);
            } catch (Throwable var12) {
               if (preparedStatement != null) {
                  try {
                     preparedStatement.close();
                  } catch (Throwable var11) {
                     var12.addSuppressed(var11);
                  }
               }

               throw var12;
            }

            if (preparedStatement != null) {
               preparedStatement.close();
            }
         } catch (Throwable var13) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var10) {
                  var13.addSuppressed(var10);
               }
            }

            throw var13;
         }

         if (connection != null) {
            connection.close();
         }
      } catch (AuthorNotFoundException var14) {
         System.out.println("Author not found");
      } catch (Exception e) {
         e.printStackTrace();
         return retriveAuthor;
      }

      return retriveAuthor;
   }

   public void delAuthor(String authorName) {
      String query = "delete from author_tb where ( name ) = ?";

      try {
         Connection con = DbConnection.getConnection();

         try {
            PreparedStatement preparedStatement = con.prepareStatement(query);

            try {
               preparedStatement.setString(1, authorName);
               int rowUpdate = preparedStatement.executeUpdate();
               System.out.println("Row updated" + rowUpdate);
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
            if (con != null) {
               try {
                  con.close();
               } catch (Throwable var7) {
                  var10.addSuppressed(var7);
               }
            }

            throw var10;
         }

         if (con != null) {
            con.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void getAllAuthor() {
      try {
         Connection con = DbConnection.getConnection();

         try {
            Statement st = con.createStatement();

            try {
               String query = "Select * from author_tb";
               ResultSet rs = st.executeQuery(query);
               System.out.println();
               System.out.println("╔═════════════════════════╗");
               System.out.println("║     Author Records      ║");
               System.out.println("╚═════════════════════════╝");
               System.out.printf("%-5s %-25s %n", "ID", "AUTHOR NAME");
               System.out.println("---------------------------");

               while(rs.next()) {
                  System.out.printf("%-5s %-25s %n", rs.getInt("id"), rs.getString("name"));
               }

               System.out.println("---------------------------");
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
         } catch (Throwable var8) {
            if (con != null) {
               try {
                  con.close();
               } catch (Throwable var5) {
                  var8.addSuppressed(var5);
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

   }

   public void updateAuthor(String prevName, String updatedName) {
      String updateQuery = "update author_tb set name = ? where name =? ";

      try {
         Connection connection = DbConnection.getConnection();

         try {
            PreparedStatement ps = connection.prepareStatement(updateQuery);

            try {
               ps.setString(2, prevName);
               ps.setString(1, updatedName);
               int rowUpdate = ps.executeUpdate();
               System.out.println("Row updated" + rowUpdate);
            } catch (Throwable var10) {
               if (ps != null) {
                  try {
                     ps.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }
               }

               throw var10;
            }

            if (ps != null) {
               ps.close();
            }
         } catch (Throwable var11) {
            if (connection != null) {
               try {
                  connection.close();
               } catch (Throwable var8) {
                  var11.addSuppressed(var8);
               }
            }

            throw var11;
         }

         if (connection != null) {
            connection.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
}
