package guru.qa.restbackend.controller;

import guru.qa.restbackend.domain.library.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    private Map<String, String> data = new HashMap<String, String>(Map.of(
            "War and Peace", "Lev Tolstoy",
            "The Captain's Daughter", "Alexandr Pushkin",
            "Eugene Onegin", "Alexandr Pushkin"
    ));

    @PutMapping("add/author")
    @ApiOperation("Добавление автора")
    public AuthorInfo addAuthor(@RequestBody AddAuthorNameInfo addAuthorNameInfo) {
        if (!addAuthorNameInfo.getAuthorName().isEmpty() && (!data.containsValue(addAuthorNameInfo.getAuthorName()))) {
            data.put("unknown", addAuthorNameInfo.getAuthorName());
            return AuthorInfo.builder()
                    .loginDate(new Date())
                    .authorName(addAuthorNameInfo.getAuthorName())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("add/book")
    @ApiOperation("Добавление книги")
    public BookInfo addBook(@RequestBody AddBookInfo addBookInfo) {
        if (!addBookInfo.getBook().isEmpty() && (!data.containsKey(addBookInfo.getBook()))) {
            data.put(addBookInfo.getBook(), "unknown");
            return BookInfo.builder()
                    .loginDate(new Date())
                    .book(addBookInfo.getBook())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("get/allBooks/forOneAuthor")
    @ApiOperation("Получение всех книг определенного автора")
    public List<String> getBooksForOneAuthor(@RequestBody GetBookInfo getBookInfo) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getValue().contains(getBookInfo.getAuthorName())) {
                result.add(entry.getKey());
            }
        }
        return result;
    }


    @GetMapping("get/booksAll")
    @ApiOperation("Получение всех книг")
    public List<String> getAllBooks() {
        return data.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @PostMapping("get/book/forOneAuthor")
    @ApiOperation("Получение книги по автору")
    public String getBookForOneAuthor(@RequestBody GetBookInfo getBookInfo) {
        String result = null;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getValue().contains(getBookInfo.getAuthorName())) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}
