package com.inquirer.dao.impl;

import com.inquirer.dao.UserRepository;
import com.inquirer.models.User;
import com.inquirer.utils.DaoHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SELECT_ALL_USER_QUERY = "SELECT * FROM user";
    private static final String ADD_USER_QUERY = "INSERT INTO user(name,age) VALUES (?,?)";
    private static final String GET_USER_BY_NAME_QUERY = "SELECT * FROM user WHERE name = ?";

    private static final Logger LOGER = Logger.getLogger(QuestionRepositoryImpl.class);

    private DaoHelper daoHelper = new DaoHelper();

    @Override
    public void addUser(User user) throws SQLException {
        try (PreparedStatement statement = daoHelper.getStatement(ADD_USER_QUERY)) {
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

        try (PreparedStatement statement = daoHelper.getStatement(SELECT_ALL_USER_QUERY);
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
        finally {
            daoHelper.closeConnection();
        }

        return users;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        User user = null;
        try (PreparedStatement statement = daoHelper.getStatement(GET_USER_BY_NAME_QUERY)) {
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            LOGER.error(e);
        } finally {
            daoHelper.closeConnection();
        }
        return user;
    }
}
