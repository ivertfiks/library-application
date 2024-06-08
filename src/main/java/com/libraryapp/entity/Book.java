package com.libraryapp.entity;

import com.libraryapp.entity.enums.BookGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "authors_id")
    private Author author;
    private BookGenre genre;

    public Book(String title, Author name, BookGenre genre) {
        this.title = title;
        this.author = name;
        this.genre = genre;
    }
}
