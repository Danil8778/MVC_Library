package pnevsky.com.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pnevsky.com.models.Book;
import pnevsky.com.models.Person;
import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM People",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM People WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO People (name, birthYear) VALUES (?, ?)",
                person.getName(), person.getBirthYear());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE People SET name=?, birthYear=? " +
                "WHERE id=?", person.getName(), person.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM People WHERE id=?", id);
    }

    public List<Book> getPersonBooks(int id) {
        List<Book> personBooks = jdbcTemplate.query(
                "SELECT * FROM Books WHERE person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
        return personBooks;
    }

    public void returnBook(int id) {
        jdbcTemplate.update("update books set person_id=null where id=?", id);
    }

    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM people WHERE name=?",
                new Object[]{name}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny();
    }
}