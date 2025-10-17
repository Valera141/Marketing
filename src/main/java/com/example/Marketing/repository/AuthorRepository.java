package com.example.Marketing.repository;

import com.example.Marketing.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> { // El ID de Author es String
}