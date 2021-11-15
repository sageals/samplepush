package com.crud.book.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crud.book.model.*;

public interface BookRepository extends MongoRepository<Book, String>{
	List<Book> findByPublished(boolean published);
	  List<Book> findByTitleContaining(String title);
}
