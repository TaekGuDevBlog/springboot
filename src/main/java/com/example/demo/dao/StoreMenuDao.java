package com.example.demo.dao;

import com.example.demo.config.MysqlConfig;
import com.example.demo.dbutil.MysqlConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class StoreMenuDao {

    @Autowired
    MysqlConfig mysqlConfig;

    public List<Map<String, Integer>> selectStoreMenu(String storeName) {
        List<Map<String, Integer>> storeMenuList = new ArrayList<>();
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String selectSql = "select A.store_name, B.menu_name, B.menu_price " +
                "from today_meal.store_info as A " +
                "left join today_meal.menu_info as B " +
                "on A.store_name = B.store_name " +
                "where A.store_name = ?";

        log.info(selectSql);

        try {
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, storeName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Map<String, Integer> menuList = new HashMap<>();
                menuList.put(resultSet.getString("menu_name"), resultSet.getInt("menu_price"));
                storeMenuList.add(menuList);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storeMenuList;
    }
}
