package pl.coderslab.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {

    @ModelAttribute("skills")
    public List<String> programingSkills() {
        return Arrays.asList("PHP", "Java", "JavaScript", "C#", "Ruby", "C++", ".net", "TypeScript", "Kotlin", "Go", "Swift");
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        return Arrays.asList("Scuba diving", "Music", "Hiking", "Star gazing",
                "Reading books", "Horseback riding", "Fishing", "Ju-jitsu");
    }

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark", "England", "Ukraine", "Japan", "China");
    }

    @GetMapping("/student/add")
//    @ResponseBody
    public String addStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "/student/add";
    }

    @PostMapping("/student/add")
    @ResponseBody
    public String addStudent(Student student) {
        return student.toString();
    }


}
