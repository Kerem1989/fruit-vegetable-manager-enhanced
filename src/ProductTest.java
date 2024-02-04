import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductTest {
    Product test;
    @BeforeEach
    void setUp() {
        test = new Product("apple", 20, "pcs","fruit", "roundfruit" );
    }

    @Test
    void getName() {
        assertEquals("apple", test.getName());
    }

    @Test
    void setName() {
        test.setName("banana");
        assertEquals("banana", test.getName());
    }

    @Test
    void getPrice() {
        assertEquals(20, test.getPrice());
    }

    @Test
    void setPrice() {
        test.setPrice(25);
        assertEquals(25, test.getPrice());
        test.setPrice(88);
        assertEquals(88,test.getPrice());
    }

    @Test
    void getPricePerPcsOrKg() {
        assertEquals("pcs", test.getPricePerPcsOrKg());
    }

    @Test
    void userSetPricePerPcsOrKg() {
        test.userSetPricePerPcsOrKg("kg");
        assertEquals("kg", test.getPricePerPcsOrKg());
    }

    @Test
    void getFruitOrVegetable() {
        assertEquals("fruit", test.getFruitOrVegetable());
    }

    @Test
    void setFruitOrVegetable() {
        test.setFruitOrVegetable("vegetable");
        assertEquals("vegetable", test.getFruitOrVegetable());
    }

    @Test
    void getProductType() {
        assertEquals("roundfruit", test.getProductType());
    }

    @Test
    void setProductType() {
        test.setProductType("junglefruit");
        assertEquals("junglefruit", test.getProductType());
    }

    @Test
    void testToString() {
        assertTrue(test.toString().endsWith("roundfruit"));
        test.setProductType("junglefruit");
        assertTrue(test.toString().endsWith("junglefruit"));

    }
}