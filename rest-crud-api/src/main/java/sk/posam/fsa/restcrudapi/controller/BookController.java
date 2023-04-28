package sk.posam.fsa.restcrudapi.controller;

import sk.posam.fsa.restcrudapi.model.Book;
import sk.posam.fsa.restcrudapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    // Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(value = "search", required = false) String search) {
        if (search == null || search.isEmpty()) {
            return ResponseEntity.ok(bookService.findAllBooks());
        } else {
            return ResponseEntity.ok(bookService.searchBooks(search));
        }
    }

    // Retrieve a book by its id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return ResponseEntity.ok(bookService.updateBook(id, updatedBook));
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
