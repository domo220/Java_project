import java.util.TreeMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/java_projekt";
    private static final String username = "roman";
    private static final String password = "1234";

    //Funkcia, ktora vezme data a ulozi ich do db
    public static boolean DataCollectionToDatabase(TreeMap<Knihy, String> Data){
        // JDBC variables for managing database connection

        Connection conn = null;
        PreparedStatement sql1 = null;
        PreparedStatement sql2 = null;

        try {
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, username, password);

            // SQL query to delete all rows from the table
            String sqlDeleteNovel = "DELETE FROM novel";
            String sqlDeleteTextbook = "DELETE FROM textbook";

            sql1 = conn.prepareStatement(sqlDeleteNovel);
            sql1.executeUpdate();

            sql1 = conn.prepareStatement(sqlDeleteTextbook);
            sql1.executeUpdate();

            for (Map.Entry<Knihy, String> entry : Data.entrySet()) {
                Knihy book = entry.getKey();
                if (book instanceof novel) {
                    novel novel = (novel) book;
                    String sqlInsert = "INSERT INTO novel (novel_name, novel_author, novel_availability, novel_date, novel_genre) VALUES (?, ?, ?, ?, ?)";

                    sql2 = conn.prepareStatement(sqlInsert);

                    sql2.setString(1, novel.getName_book());
                    sql2.setString(2, novel.getAuthor());
                    sql2.setString(3, novel.getAvaibility().toString());
                    sql2.setInt(4, novel.getRelease_date());
                    sql2.setString(5, novel.getZanr().toString());

                    sql2.executeUpdate();
                    // DATABASE: Pridat no databaze novel

                } else if (book instanceof textbook) {
                    textbook textbook = (textbook) book;

                    String sqlInsert = "INSERT INTO textbook (textbook_name, textbook_author, textbook_availability, textbook_date, textbook_rocnik) VALUES (?, ?, ?, ?, ?)";

                    sql2 = conn.prepareStatement(sqlInsert);

                    sql2.setString(1, textbook.getName_book());
                    sql2.setString(2, textbook.getAuthor());
                    sql2.setString(3, textbook.getAvaibility().toString());
                    sql2.setInt(4, textbook.getRelease_date());
                    sql2.setInt(5, textbook.getRocnik());

                    sql2.executeUpdate();
                    // DATABASE: Pridat do databaze textbook

                }
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an exception occurs
        } finally {
            try {
                if (sql1 != null) sql1.close();
            } catch (SQLException sql1DataError) {
                System.out.println(sql1DataError);
            }

            try {
                if (sql2 != null) sql2.close();
            } catch (SQLException sql2DataError) {
                System.out.println(sql2DataError);
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException conError) {
                System.out.println(conError);
            }
        }
    }

    //Funkcia, ktora vezme data z database a vrati hodnotu.
    public static TreeMap<Knihy, String> DataLoadFromDatabase(){
        TreeMap<Knihy,String> Data = new TreeMap<>();

        textbook book1 = new textbook("Roman", "Test", Knihy.availability.dostupny, 1245, 5);
        textbook book2 = new textbook("Peter", "Neviem", Knihy.availability.nedostupny, 4321, 1);
        textbook book3 = new textbook("Ferino", "Jozo", Knihy.availability.dostupny, 2134, 9);
        novel kniha1 = new novel("Ferio", "Jzo", Knihy.availability.dostupny, 2134, novel.genre.Scifi);
        Data.put(book1, "textbook");
        Data.put(book2, "textbook");
        Data.put(book3, "textbook");
        Data.put(kniha1, "novel");
        return Data;
    }
}
