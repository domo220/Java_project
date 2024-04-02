
public class textbook extends Knihy{


    private int rocnik;

    
    public textbook(String name,String author,availability avaible,int date,int Rocnik){
        super(name,author,date,avaible);
        this.rocnik=Rocnik;
    }


    public int getRocnik(){
        return rocnik;
    }


    public void setRocnik(int Rocnik){
        this.rocnik=Rocnik;
    }

}
