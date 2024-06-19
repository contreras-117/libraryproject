package com.libraryproject.springbootlibrary.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "user_email")
    String userEmail;

    @Column(name = "date")
    @CreationTimestamp
    Date date;

    @Column(name = "rating")
    double rating;

    @Column(name = "book_id")
    Long bookId;

    @Column(name = "review_description")
    String reviewDescription;
}
