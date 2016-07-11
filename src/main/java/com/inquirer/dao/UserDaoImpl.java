package com.inquirer.dao;

import com.inquirer.models.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String SELECT_ALL_USER_QUERY = "SELECT * FROM user";
    private static final String ADD_USER_QUERY = "INSERT INTO user(name,age) VALUES (?,?)";

    private static final Logger LOGER = Logger.getLogger(QuestionDaoImpl.class);

    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    @Override
    public void addUser(User user) throws SQLException {
        try (PreparedStatement statement = propertiesLoader.getStatement(ADD_USER_QUERY)) {
            statement.setString(1,user.getName());
            statement.setInt(2,user.getAge());
            statement.execute();
        } catch (SQLException e){
            LOGER.error(e);
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList();

        try (PreparedStatement statement = propertiesLoader.getStatement(SELECT_ALL_USER_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User tempUser = new User();
                tempUser.setId(resultSet.getInt("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setAge(resultSet.getInt("age"));
                users.add(tempUser);
            }
        } catch (SQLException e){
            LOGER.error(e);
        }

        return users;
    }
}
