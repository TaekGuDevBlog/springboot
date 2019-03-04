package com.example.demo.dao;

import com.example.demo.config.MysqlConfig;
import com.example.demo.dbutil.MysqlConnection;
import com.example.demo.vo.StoreVO;
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
public class StoreDao {

    @Autowired
    MysqlConfig mysqlConfig;

    public void insertStoreInfo(StoreVO storeVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String insertSql = "insert into today_meal.store_info (store_name, store_location, branch_name ) values (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, storeVO.getStoreName());
            preparedStatement.setString(2, storeVO.getStoreLocation());
            preparedStatement.setString(3, storeVO.getBranchName());
            preparedStatement.executeUpdate(); // insert

            log.info("New columns add in store_info table");
            log.info(storeVO.toString(storeVO));

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStoreInfo(StoreVO storeVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String updateSql = "update today_meal.store_info set store_location = ? and branch_name = ? where store_name = ?";
        try{
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, storeVO.getStoreLocation());
            preparedStatement.setString(2, storeVO.getBranchName());
            preparedStatement.setString(3, storeVO.getStoreName());

            preparedStatement.executeUpdate();

            log.info("New columns update in store_info table");
            log.info(storeVO.toString(storeVO));
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void deleteStoreInfo(StoreVO storeVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String deleteSql = "delete from today_meal.store_info where store_name = ? and branch_name = ?";
        try{
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, storeVO.getStoreName());
            preparedStatement.setString(2, storeVO.getBranchName());

            preparedStatement.executeUpdate();

            log.info("Delete columns in store_info table");
            log.info(storeVO.toString(storeVO));
        } catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public List<StoreVO> selectStoreInfo(String name) {
        List<StoreVO> storeVOList = new ArrayList<>();
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        String selectSql = "select * from today_meal.store_info where store_name LIKE ?";

        try{
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, "%"+name+"%");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                storeVOList.add(new StoreVO(
                            resultSet.getInt("store_seq"),
                            resultSet.getString("store_name"),
                            resultSet.getString("store_location"),
                            resultSet.getString("branch_name")
                        ));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        return storeVOList;
    }
}
