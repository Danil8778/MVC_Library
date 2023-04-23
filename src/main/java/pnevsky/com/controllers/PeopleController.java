package pnevsky.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pnevsky.com.DAO.PersonDAO;
import pnevsky.com.models.Person;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @PostMapping()
    public String create(Person person, Model model){
        model.addAttribute("person", person);
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("personBooks", personDAO.getPersonBooks(id));
        return "people/show";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable int id, Model model, Person person){
        model.addAttribute("person", person);
        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

    @GetMapping ("/new")
    public String addPerson(Model model){
        Person person = new Person();
        model.addAttribute("person",person);
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}/edit/{bookId}")
    public String returnBook(@PathVariable ("id") int id,@PathVariable ("bookId") int bookId, Model model){
        personDAO.returnBook(bookId);
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("personBooks", personDAO.getPersonBooks(id));
        return "redirect: /people/{id}";
    }
}