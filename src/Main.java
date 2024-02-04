import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/*
Namn: Kerem Tazedal
Mejl: kerem.tazedal@iths.se
 */
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Catalog catalog = new Catalog();
    public static Customerbasket customerbasket = new Customerbasket(0);
    public static final String GREEN = "\033[1;32m";
    public static final String BLUE = "\033[1;34m";
    public static boolean runCustomerMenu = true;
    public static boolean runProgram = true;
    public static DiscountedProduct discountedProduct = new DiscountedProduct("", 0, "", "", "", 0, 0);

    public static void main(String[] args) {
        // Here is four diccounted products thats is hardcoded for user in the program execution.
        catalog.getAllFruitsandVegetables().add(new DiscountedProduct("Banana", 19.90, "kg", "Fruit", "Berries", 0.8, 3));
        catalog.getAllFruitsandVegetables().add(new DiscountedProduct("Mango", 26.95, "pcs", "Fruit", "Flowering plant", 0.5, 2));
        catalog.getAllFruitsandVegetables().add(new DiscountedProduct("Tomato", 25, "kg", "Vegetable", "Potatoplant", 0.5, 3));

        String showIntroToMenu = inputUserString("\nWelcome to the Fruit and Vegetable program." +
                "\nPlease enter any key to continue to the menu.");
        runCustomerIntroMenu();
        System.out.println("\nThank you for using the Fruit and Vegetable program. \nAll credit goes to Kerem Incorporated.");
    }

    public static void runCustomerIntroMenu() {
        do {
            System.out.println(GREEN + "\nUser: CUSTOMER");
            System.out.println(GREEN + "1. Search the catalog" +
                    "\n2. Browse the catalog." +
                    "\n3. View your basket." +
                    "\n4. Log in as a employee." +
                    "\n5. Exit the program.");

            System.out.print(GREEN + "Enter one of the following numbers to continue: ");
            inputMismatchErrorNumber(Main::printCustomerMenuChoices);
        } while (runCustomerMenu);
    }

    public static void printCustomerMenuChoices() {
        int employeeInput = input.nextInt();
        input.nextLine();
        switch (employeeInput) {
            case 1 -> searchTheCatalog();
            case 2 -> browseTheCatalog();
            case 3 -> Main.printCustomerBasket();
            case 4 -> logInAsEmployee();
            case 5 -> runCustomerMenu = false;
            default -> System.out.println("\nThe input is not valid, please enter a number between 1-5. ");
        }
    }

    public static void runEmployeeIntroMenu() {
        do {
            System.out.println(BLUE + "\nUser: EMPLOYEE");
            System.out.println(BLUE + "1. Add a product." + "\n2. Remove a product." + "\n3. Display all products." + "\n4. Create a new employee account." + "\n5. Log out and return to customer menu." + "\n6. Exit the program.");
            System.out.print(BLUE + "Enter one of the following numbers to continue: ");
            inputMismatchErrorNumber(Main::printEmployeeMenuChoices);
        } while (runCustomerMenu);
    }

    public static void printEmployeeMenuChoices() {
        int employeeInput = input.nextInt();
        input.nextLine();
        switch (employeeInput) {
            case 1 -> createProduct();
            case 2 -> removeProduct();
            case 3 -> printAllProducts();
            case 4 -> createUserAccount();
            case 5 -> runCustomerIntroMenu();
            case 6 -> runCustomerMenu = false;
            default -> System.out.println("\nThe input is not valid, please enter a number between 1-5. ");
        }
    }

    /*
    This method takes the user input for a username and password, and compares it with the content of the textfiles
    and grant access to a specific part of the code if the creditentials match.
    */
    public static void logInAsEmployee() {
        boolean isLoginSuccessful = false;
        Path currentPath = Paths.get("").toAbsolutePath();
        File usernames = new File(currentPath + File.separator + "usernames.txt");
        File passwords = new File(currentPath + File.separator + "passwords.txt");
        System.out.println("\nWelcome to the login menu.");
        System.out.print("Please enter username: ");
        String username = input.nextLine();
        System.out.print("Please enter password: ");
        String password = input.nextLine();
        do {
            try {
                Scanner userNamesFile = new Scanner(usernames);
                Scanner userPasswordFile = new Scanner(passwords);

                while (userNamesFile.hasNext() && userPasswordFile.hasNext()) {
                    String userExist = userNamesFile.nextLine();
                    String passExist = userPasswordFile.nextLine();

                    if (userExist.equals(username) && passExist.equals(password)) {
                        isLoginSuccessful = true;
                        runEmployeeIntroMenu();
                        break;
                    }
                }

                if (!isLoginSuccessful) {
                    System.out.println("\nWrong username or password.");
                    runCustomerIntroMenu();
                    break;
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                runProgram = false;
            }
        } while (!isLoginSuccessful && runProgram);
    }

    /*
    This method recieves input from the user and creates objects from the class Product or DiscountedProduct
    depending on the user input.
    */
    public static void createProduct() {
        double userSetPrice = 0.0;
        String userSetPricePerPcsOrKg = "";
        String userSetFruitOrVegetable = "";
        String userSetProductType = "";
        Product createdUserProduct;
        double userSetDiscount = 0.0;
        int userSetDiscountCondition = 0;

        String userSetName = inputUserString("\nPlease enter the name of the product: ");
        String userChoicePrice = inputUserString("Do you want to add price? Please write yes or any key for no: ");
        if (userChoicePrice.equalsIgnoreCase("Yes")) {
            System.out.print("Please enter price: ");
            userSetPrice = input.nextDouble();
            input.nextLine();
            userSetPricePerPcsOrKg = inputUserString("Enter if price is per piece (pcs) or per kilogram (kg). Write pcs or kg: ");
        }
        String userChoiceFruitOrVegetable = inputUserString("Do you want add if product is a fruit or vegetable? Please write yes or any key for no: ");
        if (userChoiceFruitOrVegetable.equalsIgnoreCase("Yes")) {
            userSetFruitOrVegetable = inputUserString("Please enter if product is a fruit or vegetable: ");
        } else {
            userSetFruitOrVegetable = ("Field is unassigned");
        }
        String userChoiceProductType = inputUserString("Do you want to add the producttype? Please write yes or any key for no: ");
        if (userChoiceProductType.equalsIgnoreCase("Yes")) {
            userSetProductType = inputUserString("Please enter the producttype: ");

        } else {
            userSetProductType = ("Field is unassigned");
        }

        String userChoiceDiscountCondition = inputUserString("Do you want the product to be discounted? Please write yes or any key for no: ");
        if (userChoiceDiscountCondition.equalsIgnoreCase("Yes")) {
            System.out.print("Please enter the quantity of the product needed for the discount to be implemented (i.e. 2 needed for 50 percent discount): ");
            userSetDiscountCondition = input.nextInt();
            input.nextLine();
        }

        String userChoiceSetDiscount = inputUserString("Do you want to add a discount percentage to the product? Please write yes or any key for no: ");
        if (userChoiceSetDiscount.equalsIgnoreCase("Yes")) {
            System.out.print("Please enter the discount percentage (as a decimal, i.e., 0.1 for 10%): ");
            userSetDiscount = input.nextDouble();
            input.nextLine();
        }

        if (userSetDiscount == 0.0) {
            createdUserProduct = new Product(userSetName, userSetPrice, userSetPricePerPcsOrKg, userSetFruitOrVegetable, userSetProductType);
        } else {
            createdUserProduct = new DiscountedProduct(userSetName, userSetPrice, userSetPricePerPcsOrKg, userSetFruitOrVegetable, userSetProductType, userSetDiscount, userSetDiscountCondition);
        }
        System.out.println("\n\033[1mYou added: \033[0m" + createdUserProduct);
        catalog.setAllFruitsandVegetables(createdUserProduct);
    }

    // This method prints all the created Products/DicountedProducts from an arraylist.
    private static void printAllProducts() {
        System.out.println("\nAll products in the catalog:");
        for (Product tempCatalog : catalog.getAllFruitsandVegetables())
            System.out.println(tempCatalog);
    }

    /*
    This method recives user input to search the catalog for results,
    displays the result to the user and offers the option to calculate, display price and add to customerbasket.
     */
    public static void searchTheCatalog() {
        boolean runSearchCatalog = false;
        Customerbasket customerbasket = new Customerbasket(0);


        do {
            String searchTerm = inputUserString("\nPlease enter a keyword: ").trim();
            if (searchTerm.isEmpty()) {
                System.out.println("\nYou cannot leave it blank, please try again.");
                return;
            }

            boolean resultFoundInCatalog = false;

            for (Product tempCatalog : catalog.getAllFruitsandVegetables()) {
                if (tempCatalog.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    System.out.println("\n\033[1mThe result of your search:\033[0m");
                    System.out.println(tempCatalog);
                    String userChoiceYesOrNo = inputUserString("\nDo you want to calculate price for the product?" +
                            "\nPlease enter yes to accept or any key to decline: ");
                    if (userChoiceYesOrNo.equalsIgnoreCase("yes")) {
                        System.out.print("Please enter quantity: ");
                        double userChoiceQuantity = input.nextDouble();
                        input.nextLine();
                        calculatePrice(searchTerm, userChoiceQuantity, tempCatalog.getPrice(), tempCatalog);
                    }
                    Main.addToCustomerBasket();

                    resultFoundInCatalog = true;
                }
            }
            if (!resultFoundInCatalog) {
                System.out.println("\nNo search results found.");
            }
            String exitSearchCatalog = inputUserString("Do you want to exit to the main menu? Write Yes to exit or any key to try again: ");
            if (exitSearchCatalog.equalsIgnoreCase("Yes")) {
                runSearchCatalog = true;
            }

        } while (!runSearchCatalog);
    }

    // This method receives input from the user to remove objects of Product/DiscountedProduct from an arraylist.
    public static void removeProduct() {
        boolean runRemoveProduct = false;

        do {
            boolean doesProductExist = false;
            String userChoice = inputUserString("\nPlease enter the name of the product you want to remove: ");
            for (int i = 0; i < catalog.getAllFruitsandVegetables().size(); i++) {
                if (catalog.getAllFruitsandVegetables().get(i).getName().equalsIgnoreCase(userChoice)) {
                    Product removedProduct = catalog.getAllFruitsandVegetables().remove(i);
                    System.out.println("You removed " + removedProduct.getName() + ".");
                    doesProductExist = true;
                }
            }
            if (!doesProductExist) {
                System.out.println("\nThis product does not exist.");
            }
            String userChoiceToExit = inputUserString("Type yes to exit or any key to begin again: ");
            if (userChoiceToExit.equalsIgnoreCase("Yes")) {
                runRemoveProduct = true;
            }

        } while (!runRemoveProduct);


    }

    // This method receives four parameters and then calculates a price for the user to see.
    public static void calculatePrice(String searchTerm, double quantity, double price, Product thisProduct) {
        for (Product tempBasket : catalog.getAllFruitsandVegetables()) {
            if (tempBasket.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                if (tempBasket instanceof DiscountedProduct && ((DiscountedProduct) tempBasket).getDiscountCondition() == quantity) {
                    System.out.println("The total price will be: " + (quantity * price) * ((DiscountedProduct) tempBasket).getDiscount() + "kr with discount applied.");
                } else {
                    double sum = quantity * price;
                    System.out.println("The total price will be: " + sum + " kr." + " (" + price + " kr/" + thisProduct.getPricePerPcsOrKg() + ")");

                }
            }
        }
    }

    // This method recieves user input to navigate the main menu of the catalog section of this program.
    public static void browseTheCatalog() {
        boolean quitCatalog = false;
        do {
            System.out.println("\nWelcome to the product catalog, do you want to:" + "\n1. Browse the catalog." + "\n2. Search the catalog." + "\n3. Go back to the main menu.");
            System.out.print("Please enter a number: ");
            int userCatalogChoice = input.nextInt();
            input.nextLine();
            if (userCatalogChoice == 1) {
                findUniquePrimaryTypeInCatalog();
                System.out.print("Please choose from the options by entering the full name of the option: ");
                findUniqueProductTypeInCatalog();
                System.out.print("Please choose from the options by entering the full name of the option: ");
                printFullItemFromInputFromCatalog();
            } else if (userCatalogChoice == 2) {
                searchTheCatalog();
            } else if (userCatalogChoice == 3) {
                quitCatalog = true;
            }
        } while (!quitCatalog);
    }

    /*
    This method iterates trough arrays using for each loop to find the primarytype to add it to a HashSet, the method then
    iterates to find the uniquePrimaryTypes
    */
    public static void findUniquePrimaryTypeInCatalog() {
        for (Product uniqueItem : catalog.getAllFruitsandVegetables()) {
            String templist = uniqueItem.getFruitOrVegetable();
            catalog.getSetofPrimaryTypes().add(templist.toLowerCase());
            for (Iterator<String> i = catalog.getSetofPrimaryTypes().iterator(); i.hasNext(); ) {
                String element = i.next();
                if (element.equalsIgnoreCase("Field is Unassigned")) {
                    i.remove();
                }
            }
        }
        System.out.println("");
        System.out.println(catalog.getSetofPrimaryTypes().toString().toUpperCase().replace("[", "").replace("]", "").replace(",", " |"));

    }

    /*
    This method iterates trough arrays using for each loop to find the productypees to add it to a HashSet, the method then
    iterates to find the uniqueProductTypes.
    */
    public static void findUniqueProductTypeInCatalog() {
        String firstUserCatalogChoice = input.nextLine().toLowerCase();
        for (Product uniqueItem : catalog.getAllFruitsandVegetables()) {
            if (uniqueItem.getFruitOrVegetable().toLowerCase().contains(firstUserCatalogChoice)) {
                String templist = uniqueItem.getProductType();
                catalog.getSetOfProductTypes().add(templist.toLowerCase());
            }
        }
        System.out.println("");
        System.out.println(catalog.getSetOfProductTypes().toString().toUpperCase().replace("[", "").replace("]", "").replace(",", " |"));
    }

    // This method uses a for-each loop to search for a match based on the user input to print out the full product.
    public static void printFullItemFromInputFromCatalog() {
        String firstUserCatalogChoice = input.nextLine().toLowerCase();
        if (catalog.getSetOfProductTypes().contains(firstUserCatalogChoice.toLowerCase())) {
            System.out.println("\n\033[1mFull products: \033[0m");
            for (Product fullProduct : catalog.getAllFruitsandVegetables()) {
                if (fullProduct.getProductType().equalsIgnoreCase(firstUserCatalogChoice.toLowerCase())) {
                    System.out.println(fullProduct);
                    System.out.println();
                }
            }
        }
        catalog.getSetOfProductTypes().clear();
    }

    /*
    This method takes the user input, searches a array for the matching name, and adds it to a customer basket
    based on whether its a discounted product or a regular one.
    */
    public static void addToCustomerBasket() {
        String userChoiceYesOrNo = inputUserString("\nDo you want to add the product to the customerbasket?" +
                "\nPlease type yes to add product to basket or any key to decline: ");
        if (userChoiceYesOrNo.equalsIgnoreCase("yes")) {
            String userEnterProduct = inputUserString("Please enter the full name of the product to add to basket: ");
            System.out.print("How much or how many do you want to add to basket, please enter the quantity: ");
            int userEnterQuantity = input.nextInt();
            input.nextLine();
            for (Product tempBasket : catalog.getAllFruitsandVegetables()) {
                if (tempBasket.getName().equalsIgnoreCase(userEnterProduct)) {
                    if (tempBasket instanceof DiscountedProduct && ((DiscountedProduct) tempBasket).getDiscountCondition() == userEnterQuantity) {
                        customerbasket.getAllItemsInCustomerBasket().add((tempBasket.getPrice() * userEnterQuantity) * ((DiscountedProduct) tempBasket).getDiscount());
                        customerbasket.setItemsInBasket(userEnterQuantity);
                        System.out.println("\nYou added " + userEnterProduct + " to your basket");
                    } else {
                        customerbasket.getAllItemsInCustomerBasket().add((tempBasket.getPrice() * userEnterQuantity));
                        customerbasket.setItemsInBasket(userEnterQuantity);
                        System.out.println("\nYou added " + userEnterProduct + " to your basket.");
                    }
                }
            }
        }
    }

    /*
   This method goes trough the items in the customer bakset, calculates the total price, and then prints the amount
   of items, and also the total price at the checkout.
   */
    public static void printCustomerBasket() {
        double fullPrice = 0;
        for (Double list : customerbasket.getAllItemsInCustomerBasket()) {
            fullPrice += list;
        }
        System.out.println("\nWelcome to the checkout.");
        System.out.println("Your basket contains " + customerbasket.getItemsInBasket() + " items and the total price is " + fullPrice + " kr.");
    }

    // This method applies a try-catch to the execution of a given method to hande mismatches reg. user input.
    public static void inputMismatchErrorNumber(Runnable method) {
        try {
            method.run();
        } catch (InputMismatchException e) {
            System.out.println("\nThe input is wrong, you cannot enter a letter, please try again.");
            input.nextLine();
        }
    }

    // This method prints out a message to the user and then awaits an input from the user.
    public static String inputUserString(String messageToUser) {
        System.out.print(messageToUser);
        return input.nextLine();
    }

    // This method creates an object of the class Account consisting of a username and a password.
    public static void createUserAccount() {
        Account account = new Account("", "");
        String createUsername = inputUserString("Please enter username: ");
        String createPassword = inputUserString("Please enter password: ");
        account = new Account(createUsername, createPassword);
        System.out.println("Account " + createUsername + " created.");
    }

}







