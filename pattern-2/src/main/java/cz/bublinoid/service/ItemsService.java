package cz.bublinoid.service;

import cz.bublinoid.dao.ItemsDao;
import cz.bublinoid.model.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemsService {

    private ItemsDao itemsDao = new ItemsDao();

    public void addMultipleItems() throws SQLException {
        for (int i = 1; i <= 100; i++) {
            itemsDao.createItem(new Item(0, "Item " + i, 100.0 * i));
        }
    }

    public void doublePrices() throws SQLException {
        List<Item> items = itemsDao.getAllItems();
        for (Item item : items) {
            item.setPrice(item.getPrice() * 2);
            itemsDao.updateItem(item);
        }
    }
}
