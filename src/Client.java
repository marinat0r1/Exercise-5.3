import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.Iterator;

public class Client {

    public static void main(String[] args) {

        if (args.length != 1) {
            throw new IllegalArgumentException("Syntax: DateClient <hostname>");
        }

        try {
            ShoppingBasket shoppingBasket1 = (ShoppingBasket) Naming.lookup("basket1");
            ShoppingBasket shoppingBasket2 = (ShoppingBasket) Naming.lookup("basket2");

            // Tests on Basket1
            shoppingBasket1.addItemToBasket("Coffee", 3, 20);
            shoppingBasket1.addItemToBasket("Tea", 2, 40);
            shoppingBasket1.addItemToBasket("Water", 1, 60);

            shoppingBasket1.searchItem("Tea").setQuantity(50);

            System.out.println(shoppingBasket1.getShoppingBasketName() + "\n");

            HashSet<ShoppingItem> itemsFromBasket = shoppingBasket1.getItemsFromBasket();
            Iterator iterator = itemsFromBasket.iterator();

            int totalPriceOfBasket1 = 0;

            while (iterator.hasNext()) {
                ShoppingItem tempItem = (ShoppingItem) iterator.next();
                System.out.println(tempItem.getName() + " / " + tempItem.getPrice() + " / " + tempItem.getQuantity());
                totalPriceOfBasket1 += tempItem.getPrice() * tempItem.getQuantity();
            }

            System.out.println("Total Price of Basket1: " + totalPriceOfBasket1);

            ShoppingItem shoppingItemSearched = shoppingBasket1.searchItem("Tea");
            System.out.println("\n" + shoppingItemSearched.getName());

            // Tests on Basket2
            System.out.println("\n\n");

            shoppingBasket2.addItemToBasket("Car", 5000, 10);
            shoppingBasket2.addItemToBasket("Bike", 300, 30);


            System.out.println(shoppingBasket2.getShoppingBasketName() + "\n");

            HashSet<ShoppingItem> itemsFromBasket2 = shoppingBasket2.getItemsFromBasket();
            Iterator iterator2 = itemsFromBasket2.iterator();

            int totalPriceOfBasket2 = 0;

            while (iterator2.hasNext()) {
                ShoppingItem tempItem = (ShoppingItem) iterator2.next();
                System.out.println(tempItem.getName() + " / " + tempItem.getPrice() + " / " + tempItem.getQuantity());
                totalPriceOfBasket2 += tempItem.getPrice() * tempItem.getQuantity();
            }

            System.out.println("Total Price of Basket2: " + totalPriceOfBasket2);

            ShoppingItem shoppingItemSearched2 = shoppingBasket2.searchItem("Car");
            System.out.println("\n" + shoppingItemSearched2.getName());

        } catch (Exception e) {
            System.out.println("Client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
