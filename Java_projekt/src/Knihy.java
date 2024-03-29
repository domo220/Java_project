
public abstract class Knihy implements Comparable<Knihy>{
    
    private String name_book;
    private String author;
    private int release_date;
    enum availability {
        dostupny,
        nedostupny
    }

    private Knihy.availability availability;



    Knihy(String name, String author, int date, availability avaible){

        this.name_book=name;
        this.author=author;
        this.release_date=date;
        this.availability=avaible;
    }


    public void setName_book(String new_name_book){
        this.name_book=new_name_book;
    }

    public String getName_book(){
        return name_book;
    }

    public void setAuthor(String new_Author){
        this.author=new_Author;
    }

    public String getAuthor(){
        return author;
    }

    public void setRelease_date(int new_Release_date){
        this.release_date = new_Release_date;
    }

    public int getRelease_date(){
        return release_date;
    }

    public void setAvaibility(availability new_Avaibility){
        this.availability=new_Avaibility;
    }

    public availability getAvaibility(){
        return availability;
    }

    public int compareTo(Knihy other) {
        return this.name_book.compareTo(other.name_book);
    }


}
