import java.io.File;

/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */

public class DiscountedProduct extends Product {
    private double discount;
    private int discountCondition;

    public DiscountedProduct(String name, double price, String pricePerPcsOrKg, String fruitOrVegetable, String productType, double discount, int discountCondition) {
        super(name, price, pricePerPcsOrKg, fruitOrVegetable, productType);
        this.discount = discount;
        this.discountCondition = discountCondition;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDiscountCondition() {
        return discountCondition;
    }

    public void setDiscountCondition(int discountCondition) {
        this.discountCondition = discountCondition;
    }
    @Override
    public String toString() {
        return super.toString() + "\nDiscount: " + "Buy " + discountCondition + " " + this.getPricePerPcsOrKg() + " for " + (100 -(discount*100)) + "% " + "off your purchase";
    }
}
