package pnevsky.com.DAO;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pnevsky.com.models.Book;
import pnevsky.com.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class BookDAO {

    private JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexFreeBooks(){
        List<Book> bookList = jdbcTemplate.query("SELECT * FROM Books " +
                        "WHERE person_id is null",
                new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }

    public List<Book> indexBusyBooks(){
        List<Book> bookList = jdbcTemplate.query("SELECT * FROM Books " +
                        "WHERE person_id is not null",
                new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }

    public Book show(int id){
        Book book = jdbcTemplate.query("SELECT * FROM Books WHERE id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
        return book;
    }

    public void update(int id, Book book){
        jdbcTemplate.update("UPDATE Books SET title=?, author=?, year=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);

    }

    public void create(Book book){
        jdbcTemplate.update("INSERT INTO Books (title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void delete(int id) {

        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }

    public Person statusOfBook(int id){
        Person person =
                jdbcTemplate.query("SELECT p.id, name, birthyear FROM People p " +
                                "JOIN Books b on (p.id=b.person_id)\n" +
                                "         WHERE b.id =?;", new Object[]{id},
                                new BeanPropertyRowMapper<>(Person.class)).
                        stream().findAny().orElse(null);
        return person;
    }

    public void assign(int id, Person person){
        jdbcTemplate.update("UPDATE Books SET person_id=? WHERE id=?",
                person.getId(), id);
    }
}