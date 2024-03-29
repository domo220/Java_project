
public class novel extends Knihy{

 

    public novel(String name,String author,availability avaible,int date, genre zanr1){
        super(name,author,date,avaible);
        this.zanr=zanr1;
    }

    public enum genre {
        Scifi,
        Fantasy,
        Mistery,
        Romance,
        Fiction
    }
    
    private genre zanr;


    public void setZanr(genre zanr1){
        this.zanr = zanr1;
    }

    public genre getZanr(){
        return zanr;
    }
}
