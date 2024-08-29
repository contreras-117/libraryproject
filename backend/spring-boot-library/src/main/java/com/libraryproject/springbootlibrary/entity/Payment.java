package com.libraryproject.springbootlibrary.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_email")
    public String userEmail;

    @Column(name = "amount")
    public double amount;

}
