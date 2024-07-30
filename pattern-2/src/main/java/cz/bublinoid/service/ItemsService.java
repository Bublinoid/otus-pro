package cz.bublinoid.service;

import cz.bublinoid.dao.ItemsDao;
import cz.bublinoid.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsService {

    private ItemsDao itemsDao = new ItemsDao();

    public void addMultipleItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            items.add(new Item(0, "Item " + i, 100.0 * i));
        }
        itemsDao.createItemsBatch(items);
    }

    public void doublePrices() throws SQLException {
        List<Item> items = itemsDao.getAllItems();
        for (Item item : items) {
            item.setPrice(item.getPrice() * 2);
            itemsDao.updateItem(item);
        }
    }
}
