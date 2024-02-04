import java.util.ArrayList;

/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */

public class Customerbasket {
    private ArrayList<Double> allItemsInCustomerBasket;
    private int itemsInBasket;

    public Customerbasket(int itemsInBasket) {
        this.allItemsInCustomerBasket = new ArrayList<>();
        this.itemsInBasket = itemsInBasket;
    }

    public ArrayList<Double> getAllItemsInCustomerBasket() {
        return allItemsInCustomerBasket;
    }

    public void setAllItemsInCustomerBasket(ArrayList<Double> allItemsInCustomerBasket) {
        this.allItemsInCustomerBasket = allItemsInCustomerBasket;
    }

    public double getItemsInBasket() {
        return itemsInBasket;
    }

    public void setItemsInBasket(int userQuantity) {
        itemsInBasket += userQuantity;
    }

}



