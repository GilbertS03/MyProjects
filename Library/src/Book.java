public class Book {
    private String _name;
    private String _author;
    private String _upc;
    private double _deweyDecimal;
    private int _length;
    private double _price;
    public Book(String n, String a, String u, double dd, int l, double p){
        if(dd < 0 || dd > 1000)
            dd = 999.999;
        if(p < 0 || p > 100)
            p = 20.00;
        if(l < 0)
            l = 100;

        _name = n;
        _author = a;
        _upc = u;
        _deweyDecimal = dd;
        _length = l;
        _price = p;
    }
    public void setName(String n){_name = n;}
    public void setAuthor(String a){_author = a;}
    public boolean setUpc(String u){
        try{
            int upc = Integer.parseInt(u);
            _upc = u;
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean setDeweyDecimal(double dd){
        if(!(dd < 0.0 || dd > 1000.0)){
            _deweyDecimal = dd;
            return true;
        }
        return false;
    }
    public boolean setLength(int l){
        if(!(l < 0)){
            _length = l;
            return true;
        }
        return false;
    }
    public boolean setPrice(double p){
        if(!(p < 0 || p > 100)){
            _price = p;
            return true;
        }
        return false;
    }

    public String getName(){return _name;}
    public String getAuthor(){return _author;}
    public String getUpc(){return _upc;}
    public double getDeweyDecimal(){return _deweyDecimal;}
    public int getLength(){return _length;}
    public double getPrice(){return _price;}
}
