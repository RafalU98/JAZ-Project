package pl.edu.pjwstk.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pjwstk.User.Model.Student;
import pl.edu.pjwstk.User.Security.UserNotFoundException;
import pl.edu.pjwstk.User.Service.StudentService;

import java.util.List;

@Controller
public class
StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<Student> listStudents = studentService
                .listAll();
        model.addAttribute("listStudents", listStudents);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add New Student");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(Student student, RedirectAttributes redirectAttributes) {
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "The Student has been added to the List.");
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String showEditForm(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            studentService.delete(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}

