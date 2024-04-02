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

        int x=0;
        do{
            System.out.println(blue + "\n********** Zadejte jakou akci chete provest. **********\n" + reset);
            System.out.println("case 1: Pridani ucebnice nebo romanu.");
            System.out.println("case 2: Uprava novely/texbooku.");
            System.out.println("case 3: Smazani novely/textbooku.");
            System.out.println("case 4: Vyhledani knihy podle nazvu");
            System.out.println("case 5: Editace dostupnosti knihy");
            System.out.println("case 6: ");
            System.out.println("case 7: Vypis knih ");
            System.out.println("case 8: Ukonceni programu ");
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

                break;
                case 7:
                    functions.list_of_books();
                break;
                default:
                    if(x!=8)
                    System.out.println("Nezadali jste cislo z nabizeneho vyberu\n");
            }    
        }while(x!=8);           
    }
}
