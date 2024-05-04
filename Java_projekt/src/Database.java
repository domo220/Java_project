import java.sql.*;
import java.util.TreeMap;
import java.util.Map;
public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/java_projekt";
    private static final String username = "roman";
    private static final String password = "1234";

    //Funkcia, ktora vezme data a ulozi ich do db
    public static boolean DataCollectionToDatabase(TreeMap<Knihy, String> Data){

        Connection conn = null;
        PreparedStatement sql1 = null;
        PreparedStatement sql2 = null;

        try {
            conn = DriverManager.getConnection(DB_URL, username, password);

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

        Connection conn = null;
        PreparedStatement sql = null;
        ResultSet resultSet = null;
        TreeMap<Knihy, String> dataCollection = new TreeMap<>();

        try {

            conn = DriverManager.getConnection(DB_URL, username, password);

            String sqlQueryNovel = "SELECT novel_name, novel_author, novel_availability, novel_date, novel_genre FROM novel";
            sql = conn.prepareStatement(sqlQueryNovel);
            resultSet = sql.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("novel_name");
                String author = resultSet.getString("novel_author");
                String availabilityStr = resultSet.getString("novel_availability");
                Knihy.availability availability = Knihy.availability.valueOf(availabilityStr);
                int releaseDate = resultSet.getInt("novel_date");
                String genreStr = resultSet.getString("novel_genre");
                novel.genre genre = novel.genre.valueOf(genreStr);

                novel novelBook = new novel(name, author, availability, releaseDate, genre);
                dataCollection.put(novelBook, "novel");
            }

            String sqlQueryTextbook = "SELECT textbook_name, textbook_author, textbook_availability, textbook_date, textbook_rocnik FROM textbook";
            sql = conn.prepareStatement(sqlQueryTextbook);
            resultSet = sql.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("textbook_name");
                String author = resultSet.getString("textbook_author");
                String availabilityStr = resultSet.getString("textbook_availability");
                Knihy.availability availability = Knihy.availability.valueOf(availabilityStr);
                int releaseDate = resultSet.getInt("textbook_date");
                int rocnik = resultSet.getInt("textbook_rocnik");

                textbook textbookBook = new textbook(name, author, availability, releaseDate, rocnik);
                dataCollection.put(textbookBook, "textbook");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (sql != null) sql.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return dataCollection;
    }
}
