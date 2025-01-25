import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Library {
    private HashMap<Double,ArrayList<Book>> _library;
    private String _name;
    private static Library uniqueInstance;

    private Library(){
        double initial = 99.999;
        for(int i = 0; i < 9; i++){
            _library.put(initial, new ArrayList<>());
            initial += 100.0;
        }
        _name = "";
    }
    private Library(HashMap<Double, ArrayList<Book>> lib, String name){
        _library = lib;
        _name = name;
    }

    private Book findByDeweyDecimal(Double dd){
        boolean found = false;
        int i = 0;
        Set<Double> keys = _library.keySet();
        Iterator iterator = keys.iterator();
        while(iterator.hasNext() && !found){
            while(_library.)
        }
    }

    public static Library getUniqueInstance(){
        if(uniqueInstance == null)
            uniqueInstance = new Library();
        return uniqueInstance;
    }

    public boolean add(Book b){
        double dewey = b.getDeweyDecimal();
        if(dewey < 0 || dewey > 1000)
            return false;
        else {
            if(dewey <= 99.999)
                _library.get(99.999).add(b);
            else if(dewey >= 100.0 && dewey <= 199.99)
                _library.get(199.99).add(b);
            else if(dewey >= 200.0 && dewey <= 299.99)
                _library.get(299.99).add(b);
            else if(dewey >= 300.0 && dewey <= 399.99)
                _library.get(399.99).add(b);
            else if(dewey >= 400.0 && dewey <= 499.99)
                _library.get(499.99).add(b);
            else if(dewey >= 500.0 && dewey <= 599.99)
                _library.get(599.99).add(b);
            else if(dewey >= 600.0 && dewey <= 699.99)
                _library.get(699.99).add(b);
            else if(dewey >= 700.0 && dewey <= 799.99)
                _library.get(799.99).add(b);
            else if(dewey >= 800.0 && dewey <= 899.99)
                _library.get(899.99).add(b);
            else if(dewey >= 900.0 && dewey <= 999.99)
                _library.get(999.99).add(b);
        }
        return true;
    }
    public boolean remove(Book b){

    }
}
