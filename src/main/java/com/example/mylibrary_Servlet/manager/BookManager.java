package com.example.mylibrary_Servlet.manager;

import com.example.mylibrary_Servlet.db.DBConnectionProvider;
import com.example.mylibrary_Servlet.model.Author;
import com.example.mylibrary_Servlet.model.Book;
import com.example.mylibrary_Servlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private AuthorManager authorManager = new AuthorManager();
    private UserManager userManager = new UserManager();
    public List<Book> getAll(){
        List<Book> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Book");
            while(resultSet.next()){
                authors.add(getBookFromResultSet(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
    public Book getById(int id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Book where id = " + id);
            if(resultSet.next()){
                return getBookFromResultSet(resultSet);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return Book.builder()
                .title(resultSet.getString("title"))
                .author(authorManager.getById(Integer.valueOf(resultSet.getString("author_id"))))
                .description(resultSet.getString("description"))
                .price(resultSet.getString("price"))
                .id(Integer.valueOf(resultSet.getString("id")))
                .user(userManager.getById(Integer.valueOf(resultSet.getString("user_id"))))
                .build();
    }
    public void update(Book book) {
        String sql = "UPDATE book SET title = ?, author_id = ?, description = ?, price = ?, user_id =? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getAuthor().getId());
            ps.setString(3, book.getDescription());
            ps.setString(4, book.getPrice());
            ps.setInt(5, book.getUser().getId());
            ps.setInt(6, book.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("update");
    }
    public List<Book> getByUser(User user) {
        List<Book> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Book where user_id = " + user.getId());
            while(resultSet.next()){
                authors.add(getBookFromResultSet(resultSet));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public void save(Book book) {
        String sql = "INSERT INTO book(title,author_id,price,description,user_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, String.valueOf(book.getAuthor().getId()));
            ps.setString(3, book.getPrice());
            ps.setString(4, book.getDescription());
            ps.setString(5, String.valueOf(book.getUser().getId()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeById(int id){
        String sql = "DELETE FROM book WHERE id = " + id;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
