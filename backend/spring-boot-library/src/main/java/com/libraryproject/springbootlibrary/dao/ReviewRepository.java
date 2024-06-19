package com.libraryproject.springbootlibrary.dao;

import com.libraryproject.springbootlibrary.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
