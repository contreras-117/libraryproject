package com.libraryproject.springbootlibrary.dao;

import com.libraryproject.springbootlibrary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
