import java.util.TreeMap;

public class Database {

    //Funkcia, ktora vezme data a ulozi ich do db
    public static boolean DataCollectionToDatabase(TreeMap<Knihy, String> Data){
        return false;
    }

    //Funkcia, ktora vezme data z database a vrati hodnotu.
    public static TreeMap<Knihy, String> DataLoadFromDatabase(){
        TreeMap<Knihy,String> Data = new TreeMap<>();

        textbook book1 = new textbook("Roman", "Test", Knihy.availability.dostupny, 1245, 5);
        textbook book2 = new textbook("Peter", "Neviem", Knihy.availability.nedostupny, 4321, 1);
        textbook book3 = new textbook("Ferino", "Jozo", Knihy.availability.dostupny, 2134, 9);
        Data.put(book1, "textbook");
        Data.put(book2, "textbook");
        Data.put(book3, "textbook");
        return Data;
    }
}
