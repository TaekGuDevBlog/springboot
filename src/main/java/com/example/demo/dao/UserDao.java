package com.example.demo.dao;

import com.example.demo.config.MysqlConfig;
import com.example.demo.dbutil.MysqlConnection;
import com.example.demo.vo.MemberVO;
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
public class UserDao {

    @Autowired
    MysqlConfig mysqlConfig;

    public void insertUserInfo(MemberVO memberVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement = null;

        String insertSql = "insert into today_meal.member_info (member_id, member_pw, member_name) values (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, memberVO.getMemberId());
            preparedStatement.setString(2, memberVO.getMemberPw());
            preparedStatement.setString(3, memberVO.getMemberName());

            preparedStatement.executeUpdate(); // insert

            log.info("New columns add in member_info table");
            log.info(memberVO.toString(memberVO));
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserInfo(MemberVO memberVO) {
        // only change password
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;

        String updateSql = "update today_meal.member_info set member_pw = ? where member_id = ?";

        try {
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, memberVO.getMemberPw());
            preparedStatement.setString(2, memberVO.getMemberId());

            preparedStatement.executeUpdate(); // update

            log.info("Update columns in member_info table");

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserInfo(MemberVO memberVO) {
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String tempPw;

        String selectSql = "select member_pw from today_meal.member_info where member_id = ?";


        try {
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, memberVO.getMemberId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempPw = resultSet.getString("member_pw");
                if (tempPw.equals(memberVO.getMemberPw())) {
                    String deleteSql = "delete from today_meal.member_info where member_id = ?";
                    preparedStatement = connection.prepareStatement(deleteSql);
                    preparedStatement.setString(1, memberVO.getMemberId());
                    preparedStatement.executeUpdate();
                }
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MemberVO selectOneUserInfo(String memberId) {
        MemberVO memberVO = new MemberVO();
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String selectSql = "select * from today_meal.member_info where member_id = ?";

        try {
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, memberId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                memberVO.setMemberSeq(resultSet.getInt("member_seq"));
                memberVO.setMemberId(resultSet.getString("member_id"));
                memberVO.setMemberName(resultSet.getString("member_name"));
                memberVO.setMemberPw(resultSet.getString("member_pw"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberVO;
    }

    public List<MemberVO> selectAllUserInfo() {
        List<MemberVO> memberVOList = new ArrayList<>();
        Connection connection = MysqlConnection.getConnection(mysqlConfig);
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String selectSql = "select * from today_meal.member_info";

        try {
            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                memberVOList.add(new MemberVO(
                        resultSet.getInt("member_seq"),
                        resultSet.getString("member_id"),
                        resultSet.getString("member_name"),
                        resultSet.getString("member_pw")
                ));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberVOList;
    }
}