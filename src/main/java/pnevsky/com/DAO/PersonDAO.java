package pnevsky.com.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pnevsky.com.models.Person;
import java.util.List;



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




}