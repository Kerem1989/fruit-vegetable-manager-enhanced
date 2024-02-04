import java.util.ArrayList;
import java.util.HashSet;

/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */

public class Catalog {
    private ArrayList<Product> allFruitsandVegetables;
    private HashSet<String> setofPrimaryTypes;
    private HashSet<String> setOfProductTypes;

    public Catalog() {
        this.allFruitsandVegetables = new ArrayList<>();
        this.setofPrimaryTypes = new HashSet<>();
        this.setOfProductTypes =  new HashSet<>();
    }

    public ArrayList<Product> getAllFruitsandVegetables() {
        return allFruitsandVegetables;
    }

    public void setAllFruitsandVegetables(Product product) {
        this.allFruitsandVegetables.add(product);
    }

    public HashSet<String> getSetofPrimaryTypes() {
        return setofPrimaryTypes;
    }

    public void setSetofPrimaryTypes(HashSet<String> setofPrimaryTypes) {
        this.setofPrimaryTypes = setofPrimaryTypes;
    }

    public HashSet<String> getSetOfProductTypes() {
        return setOfProductTypes;
    }

    public void setSetOfProductTypes(HashSet<String> setOfProductTypes) {
        this.setOfProductTypes = setOfProductTypes;
    }

}
