package com.crud.book.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.book.model.Book;
import com.crud.book.repository.BookRepository;



@RestController
public class BookController {
	@GetMapping("/bookstest")
	public String data() {
		return "entered spring";
	}
	  @Autowired
	  BookRepository bookRepository;

	  @GetMapping("/books")
	  public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
		 
		  
	    try {
	      List<Book> books = new ArrayList<Book>();
	      System.out.println("the program entered in getmap");
	      if (title == null)
	        bookRepository.findAll().forEach(books::add);
	      
	      else
	    	  bookRepository.findByTitleContaining(title).forEach(books::add);
	      

	      if (books.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(books, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  public String dasfta() {
	    	return "getapping 1mapping";}

	  @GetMapping("/tutorials/{id}")
	  public ResponseEntity<Book> getTutorialById(@PathVariable("id") String id) {
		    Optional<Book> tutorialData = bookRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  public String dasftad() {
	    	return "getapping id mapping";}
	  @PostMapping("/books")
	  public ResponseEntity<Book> createTutorial(@RequestBody Book book) {
		  
	    try {
	    	Book _book= bookRepository.save(new Book(book.getTitle(), book.getDescription(), false));
	      return new ResponseEntity<>(_book, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  public String dpasfta() {
	    	return "postapping 1mapping";}

	  @PutMapping("/books/{id}")
	  public ResponseEntity<Book> updateBook(@PathVariable("id") String id, @RequestBody Book book) {
		  
	    Optional<Book> bookData = bookRepository.findById(id);

	    if (bookData.isPresent()) {
	    	Book _book = bookData.get();
	      _book.setTitle(book.getTitle());
	      _book.setDescription(book.getDescription());
	      _book.setPublished(book.isPublished());
	      return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/books/{id}")
	  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") String id) {
		  
	    try {
	      bookRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("/books")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	      bookRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	  }
	  public String dafta() {
	    	return "deletemapping";
	    }

	  @GetMapping("/books/published")
	  public ResponseEntity<List<Book>> findByPublished() {
		  System.out.print("the program entered in getmap publish");
	    try {
	      List<Book> books = bookRepository.findByPublished(true);

	      if (books.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(books, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
