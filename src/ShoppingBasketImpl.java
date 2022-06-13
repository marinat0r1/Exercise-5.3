import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;

// On ShoppingBasket & ShoppingItem only work with the Interface!
public class ShoppingBasketImpl extends UnicastRemoteObject implements ShoppingBasket {

    private String name;
    private HashSet<ShoppingItem> items;

    public ShoppingBasketImpl(String name, HashSet<ShoppingItem> items) throws RemoteException {
        super();
        this.name = name;
        items = new HashSet<ShoppingItem>();
        this.items = items;
    }

    public ShoppingBasketImpl(String name) throws RemoteException {
        super();
        this.name = name;
        items = new HashSet<ShoppingItem>();
    }

    public ShoppingBasketImpl() throws RemoteException {
        super();
        items = new HashSet<ShoppingItem>();
    }

    @Override
    public ShoppingItem searchItem(String itemName) throws RemoteException {
        for (ShoppingItem item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public HashSet<ShoppingItem> getItemsFromBasket() throws RemoteException {
        return this.items;
    }

    @Override
    public String getShoppingBasketName() throws RemoteException {
        return this.name;
    }

    @Override
    public void addItemToBasket(String name, int price, int quantity) throws RemoteException {
        items.add(new ShoppingItemImpl(name, price, quantity));
    }

    public void print() {
        System.out.println(items.toString());
    }

    public static void main(String[] args) {

        try {
            ShoppingBasketImpl shoppingBasket1 = new ShoppingBasketImpl("basket1");
            ShoppingBasketImpl shoppingBasket2 = new ShoppingBasketImpl("basket2");

            Naming.rebind ("basket1", shoppingBasket1);
            Naming.rebind ("basket2", shoppingBasket2);
            System.out.println("The server is up");

        } catch (Exception e) {
            System.out.println("ShoppingItemImpl: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
