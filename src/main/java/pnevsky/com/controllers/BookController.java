package pnevsky.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pnevsky.com.DAO.BookDAO;
import pnevsky.com.DAO.PersonDAO;
import pnevsky.com.models.Book;
import pnevsky.com.models.Person;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("freeBooks",bookDAO.indexFreeBooks());
        model.addAttribute("busyBooks",bookDAO.indexBusyBooks());

        return "books/index";
    }

    @PostMapping()
    public String create(Book book, Model model){
        model.addAttribute("book", book);
        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Person person, Model model){
        model.addAttribute("person", person);
        model.addAttribute("book",bookDAO.show(id));
        Person owner = bookDAO.statusOfBook(id);
        List<Person> people = personDAO.index();
        if (owner != null)
            model.addAttribute("owner", owner);
        else {
            model.addAttribute("people",people);
        }
        return "books/show";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable ("id") int id, Book book, Model model){
        model.addAttribute("book", book);
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute(book);
        return "books/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable ("id") int id, Model model){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, Person selectedPerson, Model model) {
        model.addAttribute("person", selectedPerson);
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books";
    }
}