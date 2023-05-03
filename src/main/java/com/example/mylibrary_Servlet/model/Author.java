package com.example.mylibrary_Servlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {
    private int id;
    private int age;
    private String name;
    private String surname;
    private String email;
}
