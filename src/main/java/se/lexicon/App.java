package se.lexicon;

import se.lexicon.model.Product;
import se.lexicon.model.VendingMachine;
import se.lexicon.products.*;

import java.util.Arrays;

/**
 * Entry point for the vending machine application.
 * Demonstrates basic usage: initializing candy products, adding currency, purchasing, and ending session.
 */
public class App {
    public static void main(String[] args) {


        // Step 1: Create a vending machine and fill it with assortment
        // Each product has a unique ID, a price, a name, and a candy-specific attribute
        VendingMachine vm = new VendingMachine(Arrays.asList(
                new Snickers(1,20, "Snickers", Size.LARGE),
                new CottonCandy(2, 50, "Cotton Candy", "Strawberry"),
                new MintDrops(3, 30, "Mint Drops", 4),
                new Taco(4, 60, "Taco")
        ));

        // Step 2: Simulate the user inserting money into the machine
        // Only valid denominations are accepted (e.g., 1, 5, 10, 20, etc.)
        vm.addCurrency(100); // Insert 100 SEK

        // Step 3: Print the current balance to confirm the deposit
        System.out.println("Balance: " + vm.getBalance());

        // Step 4: Show the description of the product with ID 4
        // This helps the user decide whether they want to buy it
        System.out.println(vm.getDescription(4));

        // Step 5: Attempt to purchase the product with ID 4
        // If the user has enough money, the product is returned
        Product p = vm.request(4);

        // Step 6: Simulate using the product (e.g., eating or chewing)
        // The use() method gives a fun message based on the product type
        if (p != null) {
            System.out.println(p.use());
        }

        // Step 7: End the session and return any leftover balance as change
        System.out.println("Change: " + vm.endSession());

        System.out.println();
        //Step 2: Simulate buying an item that costs more than you have money for
        vm.addCurrency(20); // Insert 20 SEK


        // Step 3: Print the current balance to confirm the deposit
        System.out.println("Balance: " + vm.getBalance());


        // Step 4: Show the description of the product with ID 4
        // This helps the user decide whether they want to buy it
        System.out.println(vm.getDescription(3));

        // Step 5: Attempt to purchase the product with ID 4
        // If the user has enough money, the product is returned
        Product taco = vm.request(3);

        // Step 6: Simulate using the product (e.g., eating or chewing)
        // The use() method gives a fun message based on the product type
        if (taco != null) {
            System.out.println(taco.use());
        }
    }
}