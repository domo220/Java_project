import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class functions {
    

static TreeMap<Knihy,String> treeMap = new TreeMap<>();

    //Funkce pro pridani objektu do treemap
    public static void addname(){

        Scanner sc = new Scanner(System.in);
        String x;
        do{
            System.out.println("Choose if u want novel or textbook:");
             x = sc.nextLine();
        } while(!x.equals("novel") && !x.equals("textbook"));
        

        System.out.println("Enter the name of book");
        String name = sc.nextLine();
        System.out.println("Enter the name of author");
        String author = sc.nextLine();
        System.out.println("Enter the avaibility");

            Knihy.availability dostupnost=null;
            System.out.println("Zadejte Dostupnost: case 1: pro dostupnost \n case 2: pro nedostupnost");
            
            int dostupnost_vstup_switch=sc.nextInt();

            switch (dostupnost_vstup_switch) {
                case 1:
                    dostupnost=Knihy.availability.dostupny;
                    break;
                case 2:
                    dostupnost=Knihy.availability.nedostupny;
                    break;
                default:
                    break;
            }

        System.out.println("Enter the release date of the book");
        int date=0;
        while(true){

            String value = sc.nextLine();

            if(value.matches("\\d+")){
                date = Integer.parseInt(value);
                LocalDateTime now = LocalDateTime.now();
                int year = now.getYear();

                if(date<=year && date>0){
                    break;
                }else{
                    do{
                        System.out.println("Zadejte platny rok");
                        date = sc.nextInt();
                    }while(year<date && date<0);
                    break;
                }
                
            }else{
                System.out.println("Zadejte cislo");
                continue;
            }
        }

        novel.genre zanr=null; 
        if(x.equals("novel")){
            System.out.println("Zadejte Zanr: Scifi, Fantasy, Mistery, Fiction, Romance");
            
            String zanr_vstup_switch=sc.nextLine();
            switch (zanr_vstup_switch) {
                case "Scifi":
                    zanr=novel.genre.Scifi;
                    break;
                case "Fantasy":
                    zanr=novel.genre.Fantasy;
                    break;
                case "Mistery":
                    zanr=novel.genre.Mistery;
                    break;
                    case "Fiction":
                    zanr=novel.genre.Fiction;
                    break;
                    case "Romance":
                    zanr=novel.genre.Romance;
                    break;
                default:
                    break;
            }
        }
        if(x.equals("textbook")){
            textbook book1 = new textbook(name,author,dostupnost,date);
            treeMap.put(book1,"textbook");
        } else if(x.equals("novel")){
            novel book1= new novel(name,author,dostupnost,date,zanr);
            treeMap.put(book1,"novel");
        }
    }

    

    //Funkce na vypsani knih dle abecedy
    public static void list_of_books(){
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            Knihy book = entry.getKey();
            System.out.println(book.getName_book()+"  "+book.getAuthor()+book.getAvaibility()+book.getRelease_date());
            if (book instanceof novel) {
                novel novel = (novel) book;
                System.out.println("Nazev knihy: " + novel.getName_book() +
                        ", Autor knihy: " + novel.getAuthor() +
                        ", Rok vydani: " + novel.getRelease_date() +
                        ", Stav dostupnosti: " + novel.getAvaibility() +
                        ", Zanr: " + novel.getZanr());
            } else if (book instanceof textbook) {
                textbook textbook = (textbook) book;
                System.out.println("Nazev knihy: " + textbook.getName_book() +
                        ", Autor knihy: " + textbook.getAuthor() +
                        ", Rok vydani: " + textbook.getRelease_date() +
                        ", Stav dostupnosti: " + textbook.getAvaibility() +
                        ", Rocnik: " + "tu bude rocnik");
            }
        }
    }

    // Funkce na vyhledani urcite knihy
    public static void looking_book(){
        Scanner sc = new Scanner(System.in);
        String book_name = sc.nextLine();
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            Knihy book = entry.getKey();
            if(book_name.equals(book.getName_book())){
                if (book instanceof novel) {
                    novel novel = (novel) book;
                    System.out.println("Nazev knihy: " + novel.getName_book() +
                            ", Autor knihy: " + novel.getAuthor() +
                            ", Rok vydani: " + novel.getRelease_date() +
                            ", Stav dostupnosti: " + novel.getAvaibility() +
                            ", Zanr: " + novel.getZanr());
                } else if (book instanceof textbook) {
                    textbook textbook = (textbook) book;
                    System.out.println("Nazev knihy: " + textbook.getName_book() +
                            ", Autor knihy: " + textbook.getAuthor() +
                            ", Rok vydani: " + textbook.getRelease_date() +
                            ", Stav dostupnosti: " + textbook.getAvaibility() +
                            ", Rocnik: " + "tu bude rocnik");
                }
            }
        }
    }
    //Vymazani knihy
    public static void delete_book(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte nazev knihy, kterou chcete smazat");
        String book_name = sc.nextLine();
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            Knihy book = entry.getKey();
            if(book_name.equals(book.getName_book())){
                treeMap.remove(book);
            }
        }
    }
     
    //Funkce na editaci knih 
    public static void edit_of_book(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Zadejte jmeno knihy, kterou chcete upravit");
    String book_name = sc.nextLine();
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            Knihy book = entry.getKey();
            String KeyName=book.getName_book();

            if(book_name.equals(KeyName)){
                String value;
                do{
                    System.out.println("Nazev knihy: " + book.getName_book());
                    System.out.println("Mozne prvky k editaci: ");
                    System.out.println("Autor knihy: "+book.getAuthor()+"\nRok vydani: "+book.getRelease_date()+"\nStav dostupnosti: "+ book.getAvaibility());
                    System.out.println("Zadejte, co chcete upravit: ");
                    value = sc.nextLine();
                    
                    switch (value) {
                        case "Autor knihy":
                            String variable;
                            System.out.print("Novy autor knihy: ");
                            variable=sc.nextLine();
                            book.setAuthor(variable);
                            break;
                        case "Rok vydani":
                            int variable2;
                            System.out.print("Zadejte novy rok vydani: ");
                            variable2=sc.nextInt();
                            book.setRelease_date(variable2);
                            break;
                        case "Stav dostupnosti":
                            Knihy.availability dostupnost=null;
                            System.out.println("Zadejte Dostupnost: case 1: pro dostupnost \n case 2: pro nedostupnost");
                            
                            int dostupnost_vstup_switch=sc.nextInt();
            
                            switch (dostupnost_vstup_switch) {
                                case 1:
                                    dostupnost=Knihy.availability.dostupny;
                                    book.setAvaibility(dostupnost);
                                    break;
                                case 2:
                                    dostupnost=Knihy.availability.nedostupny;
                                    book.setAvaibility(dostupnost);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            if(!value.equals("konec")){
                                System.out.println("Zadali jste neplatnou hodnotu\n");
                            }
                    }
                }while(!value.equals("konec"));
            }
        }
    }
}
