package com.example.mylibrary_Servlet.manager;

import com.example.mylibrary_Servlet.db.DBConnectionProvider;
import com.example.mylibrary_Servlet.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
            while(resultSet.next()){
                authors.add(getAuthorFromResultSet(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .age(resultSet.getInt("age"))
                .email(resultSet.getString("email"))
                .build();
        return author;
    }
    public Author getByEmail(String email){
        String sql = "SELECT * FROM author where email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return getAuthorFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Author getById(int id){
        String sql = "SELECT * FROM author where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return getAuthorFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void removeById(int id){
        String sql = "DELETE FROM author WHERE id = " + id;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Author author){
        String sql = "UPDATE author Set name = ?, surname = ?, email = ?, age = ? where id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,author.getName());
            ps.setString(2,author.getSurname());
            ps.setString(3,author.getEmail());
            ps.setString(4,String.valueOf(author.getAge()));
            ps.setString(5,String.valueOf(author.getId()));
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void save(Author author){
        String sql = "INSERT INTO author(name, surname, email, age) values(?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setString(4, String.valueOf(author.getAge()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
