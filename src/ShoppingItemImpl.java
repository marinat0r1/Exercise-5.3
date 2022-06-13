import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShoppingItemImpl extends UnicastRemoteObject implements ShoppingItem {

    private String name;
    private int price;
    private int quantity;

    public ShoppingItemImpl(String name, int price, int quantity) throws RemoteException {
        super();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ShoppingItemImpl() throws RemoteException {
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
