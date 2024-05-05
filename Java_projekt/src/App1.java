import javax.xml.crypto.Data;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App1 {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        String reset = "\u001B[0m";
        String blue = "\u001B[34m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";

        // Nacitanie dát z databáze
        functions.dbEntryPoint(Database.DataLoadFromDatabase());

        int x=0;
        boolean dataOK = false;
        do{
            System.out.println(blue + "\n********** Zadejte jakou akci chete provest. **********\n" + reset);
            System.out.println("case 1: Pridani ucebnice nebo romanu.");
            System.out.println("case 2: Uprava novely/texbooku.");
            System.out.println("case 3: Smazani novely/textbooku.");
            System.out.println("case 4: Vyhledani knihy podle nazvu");
            System.out.println("case 5: Editace dostupnosti knihy");
            System.out.println("case 6: Vypis podla autora");
            System.out.println("case 7: Vypis podla zanru");
            System.out.println("case 8: Vypis vypujicenych knih");
            System.out.println("case 9: Vypis knih");
            System.out.println("case 10: Ukonceni programu");
            System.out.print("Zadejte cislo: ");
            try{
                x=sc.nextInt();
                
            }catch(InputMismatchException e){
                System.out.println("Zadejte cislo");
                sc.next();
                continue;
            }
            switch(x){
                case 1:
                    functions.addname();
                    break;
                case 2:
                    functions.edit_of_book();
                    break;
                case 3:
                    functions.delete_book();
                    break;
                case 4:
                    functions.looking_book();
                    break;
                case 5:
                    functions.borrow_book();
                    break;
                case 6:
                    functions.vypisAutor();
                    break;
                case 7:
                    functions.vypisZanr();
                    break;
                case 8:
                    functions.vypisDostupnoti();
                    break;
                case 9:
                    functions.list_of_books();
                    break;
                case 10:
                    if (Database.DataCollectionToDatabase(functions.dbCollectionPoint())){
                        System.out.println("Ulozenie do databaze prebehlo úspešne!");
                        dataOK = true;
                        break;
                    }
                    else {
                        System.out.println("Ulozenie do databaze zlyhalo!");
                        dataOK = false;
                        break;
                    }
                default:
                        System.out.println("Nezadali jste cislo z nabizeneho vyberu\n");
            }    
        }while(x!=10);
    }
}
