package com.example.demo.dao;

import com.example.demo.config.MysqlConfig;
import com.example.demo.dbutil.MysqlConnection;
import com.example.demo.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MenuDao {

    @Autowired
    MysqlConfig mysqlConfig;

    public void insertMenuInfo(MenuVO menuVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String insertSql = "insert into today_meal.menu_info (store_name, menu_name, menu_price ) values (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, menuVO.getStoreName());
            preparedStatement.setString(2, menuVO.getMenuName());
            preparedStatement.setInt(3, menuVO.getMenuPrice());
            preparedStatement.executeUpdate(); // insert

            log.info("New columns add in store_info table");
            log.info(menuVO.toString(menuVO));

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMenuInfo(MenuVO menuVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String updateSql = "update today_meal.menu_info set menu_price = ? where store_name = ? and menu_name = ?";

        try{
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, menuVO.getMenuPrice());
            preparedStatement.setString(2, menuVO.getStoreName());
            preparedStatement.setString(3, menuVO.getMenuName());
            preparedStatement.executeUpdate();

            log.info("Update menu in menu_info table");
            log.info(menuVO.toString(menuVO));

            preparedStatement.close();
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void deleteMenuInfo(MenuVO menuVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String deleteSql = "delete from today_meal.menu_info where store_name = ? and menu_name = ?";

        try{
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, menuVO.getStoreName());
            preparedStatement.setString(2, menuVO.getMenuName());
            preparedStatement.executeUpdate();

            log.info("Delete menu in meni_info table");
            log.info(menuVO.toString(menuVO));

            preparedStatement.close();
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public List<MenuVO> selectMenuAll(String name) {
        List<MenuVO> menuVOList = new ArrayList<>();
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String selectSql = "select * from today_meal.menu_info where store_name = ?";

        try{
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                menuVOList.add(new MenuVO(
                        resultSet.getInt("menu_seq"),
                        resultSet.getString("store_name"),
                        resultSet.getString("menu_name"),
                        resultSet.getInt("menu_price")
                ));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return menuVOList;
    }

    public List<MenuVO> selectMenuInfo(String name, String menu) {
        List<MenuVO> menuVOList = new ArrayList<>();
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String selectSql = "select * from today_meal.menu_info where store_name = ? and menu_name = ?";

        try{
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, menu);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                menuVOList.add(new MenuVO(
                        resultSet.getInt("menu_seq"),
                        resultSet.getString("store_name"),
                        resultSet.getString("menu_name"),
                        resultSet.getInt("menu_price")
                ));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return menuVOList;
    }
}
