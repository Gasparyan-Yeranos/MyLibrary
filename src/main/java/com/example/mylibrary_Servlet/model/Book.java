package com.example.mylibrary_Servlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    private int id;
    private String title;
    private String description;
    private String price;
    private Author author;
    private User user;
}
