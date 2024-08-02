package cz.bublinoid;

import cz.bublinoid.proxy.ItemsServiceProxy;
import cz.bublinoid.service.ItemsService;

public class Main {
    public static void main(String[] args) {
        try {
            ItemsService service = new ItemsService();
            ItemsServiceProxy serviceProxy = new ItemsServiceProxy(service);

            serviceProxy.addMultipleItems();
            System.out.println("100 items added successfully!");


            serviceProxy.doublePrices();
            System.out.println("Prices of all items have been doubled successfully!");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
