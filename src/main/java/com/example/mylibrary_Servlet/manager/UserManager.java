package com.example.mylibrary_Servlet.manager;

import com.example.mylibrary_Servlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.mylibrary_Servlet.db.DBConnectionProvider;
import com.example.mylibrary_Servlet.model.UserType;

public class UserManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    public List<User> getAll(){
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from user");
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public User getByEmail(String email){
        String sql = "Select * from user where email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getByEmailAndPassword(String email, String password){
        String sql = "Select * from user where email = ? and password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .userType(UserType.valueOf(resultSet.getString("userType")))
                .build();
        return user;
    }

    public void save(User user){
        String sql = "INSERT INTO user(name,surname,email,password,userType) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType().name());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(int id) {
        String sql = "SELECT * FROM user where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return getUserFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
