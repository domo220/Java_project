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
        System.out.println("Zadejte Dostupnost: \ncase 1: pro dostupnost \ncase 2: pro nedostupnost");
        
        



        while(true){

            int dostupnost_vstup_switch;
            try{

                dostupnost_vstup_switch=sc.nextInt();        
    
            }catch(InputMismatchException e){
                System.out.println("Zadej cislo blbecku");
                sc.next();
                continue;
            }

            if(dostupnost_vstup_switch>2){
                System.out.print("Zadejte cislo 1 nebo 2: ");
                continue;
            }

            if(dostupnost_vstup_switch==1) {

                dostupnost=Knihy.availability.dostupny;
                break;
            }else if(dostupnost_vstup_switch==2){

                dostupnost=Knihy.availability.nedostupny;
                break;
            }

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
            
            boolean konec_zanr = true;
            while(konec_zanr){

                String zanr_vstup_switch=sc.nextLine();
                switch (zanr_vstup_switch) {
                    case "Scifi":
                        zanr=novel.genre.Scifi;
                        konec_zanr = false;
                        break;
                    case "Fantasy":
                        zanr=novel.genre.Fantasy;
                        konec_zanr = false;
                        break;
                    case "Mistery":
                        zanr=novel.genre.Mistery;
                        konec_zanr = false;
                        break;
                        case "Fiction":
                        zanr=novel.genre.Fiction;
                        konec_zanr = false;
                        break;
                        case "Romance":
                        zanr=novel.genre.Romance;
                        konec_zanr = false;
                        break;
                    default:
                        System.out.println("Blbecku zadej spravny zanr");
                        break;
                }
            }
        }

        int rocnik=0; 
        if(x.equals("textbook")){
            System.out.println("Zadejte rocnik: 1-9");
            
            boolean konec_rocnik = true;
            while(konec_rocnik){

                int rocnik_vstup_switch=0;
                try {

                    rocnik_vstup_switch=sc.nextInt();

                } catch (InputMismatchException e) {
                    
                    sc.next();
                }
                

                switch (rocnik_vstup_switch) {
                    case 1:
                        rocnik=1;
                        konec_rocnik = false;
                        break;
                    case 2:
                        rocnik=2;
                        konec_rocnik = false;
                        break;
                    case 3:
                        rocnik=3;
                        konec_rocnik = false;
                        break;
                    case 4:
                        rocnik=4;
                        konec_rocnik = false;
                        break;
                    case 5:
                        rocnik=5;
                        konec_rocnik = false;
                        break;
                    case 6:
                        rocnik=6;
                        konec_rocnik=false;
                        break;
                    case 7:
                        rocnik=7;
                        konec_rocnik=false;
                        break;
                    case 8:
                        rocnik=8;
                        konec_rocnik=false;
                        break;
                    case 9:
                        rocnik=9;
                        konec_rocnik=false;
                        break;
                    default:
                        System.out.println("Zadali jste spatne cislo, nebo jste napsali String");
                        break;
                }
            }
        }

        if(x.equals("textbook")){
            textbook book1 = new textbook(name,author,dostupnost,date,rocnik);
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
            System.out.println("Debug: "+book.getName_book()+"  "+book.getAuthor()+book.getAvaibility()+book.getRelease_date());//kdyz uzivatel zada prvni pismeno velke, tak se to neradi podle abecedy, idk proc
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
                        ", Rocnik: " + textbook.getRocnik());
            }
        }
    }

    // Funkce na vyhledani urcite knihy
    public static void looking_book(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Zadejte jmeno knihy, kterou chcete vyhledat: ");
        String book_name = sc.nextLine();

        String KeyName=null;
        Knihy book=null;
        boolean book_found=false;
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            book = entry.getKey();
            KeyName=book.getName_book();

            if (book_name.equals(KeyName)){
                book_found=true;
                break;
            }

            if(book_found){
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
                            ", Rocnik: " + textbook.getRocnik());
                }
            }
        }
    }
    //Vymazani knihy
    public static void delete_book(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte nazev knihy, kterou chcete smazat");
        String book_name = sc.nextLine();

        String KeyName=null;
        Knihy book=null;
        boolean book_found=false;
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            book = entry.getKey();
            KeyName=book.getName_book();

            if (book_name.equals(KeyName)){
                book_found=true;
                break;
            }
        }
        if(book_found){
            treeMap.remove(book);
        } else {
            System.out.println("Kniha neexistuje");
        }
    }
     
    //Funkce na editaci knih 
    public static void edit_of_book(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Zadejte jmeno knihy, kterou chcete upravit");
    String book_name = sc.nextLine();

       String KeyName=null;
        Knihy book=null;
        boolean book_found=false;
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            book = entry.getKey();
            KeyName=book.getName_book();

            if (book_name.equals(KeyName)){
                book_found=true;
                break;
            }
        }

        if(book_found){
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
        } else{
            System.err.println("Kniha neexistuje");
        }
    }
    

    public static void borrow_book(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Zadejte jmeno knihy, u ktere chcete zmenit stav dostupnosti");
        String book_name = sc.nextLine();

        String KeyName=null;
        Knihy book=null;
        boolean book_found=false;
        for (Map.Entry<Knihy, String> entry : treeMap.entrySet()) {
            book = entry.getKey();
            KeyName=book.getName_book();

            if (book_name.equals(KeyName)){
                book_found=true;
                break;
            }
        }
        
        if(book_found){
            while (true) {
                System.out.println("case 1: kniha je vracena\ncase 2: kniha je vypujcena");

                int borrow=0;
                try {
                    borrow=sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Zadejte cislo");
                    continue;
                }
                if(borrow==1){
                    System.out.println("Kniha uz je zase dostupna");
                    book.setAvaibility(Knihy.availability.dostupny);
                    break;
                }else if (borrow==2){
                    System.out.println("Kniha je nedostupna");
                    book.setAvaibility(Knihy.availability.nedostupny);
                    break;
                }else{
                    System.out.println("spatny input");
                    sc.next();
                    continue;
                }
            }
        }else{
            System.out.println("Kniha neexistuje");
        }
    }
}
