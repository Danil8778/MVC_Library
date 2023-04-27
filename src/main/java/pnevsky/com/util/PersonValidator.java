package pnevsky.com.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pnevsky.com.DAO.PersonDAO;
import pnevsky.com.models.Person;

@Component
public class PersonValidator implements Validator {

    PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personDAO.getPersonByName(person.getName()).isPresent())
            errors.rejectValue("name","", "A person with this name already exist");

    }
}
