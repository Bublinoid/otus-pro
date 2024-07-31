package cz.bublinoid.proxy;

import cz.bublinoid.service.ItemsService;
import cz.bublinoid.util.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {

    private ItemsService itemsService;
    private Connection connection;

    public ItemsServiceProxy(ItemsService itemsService) {
        this.itemsService = itemsService;
        this.connection = DataSource.getInstance().getConnection();
    }

    public void addMultipleItems() throws SQLException {
        try {
            itemsService.addMultipleItems();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public void doublePrices() throws SQLException {
        try {
            itemsService.doublePrices();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
