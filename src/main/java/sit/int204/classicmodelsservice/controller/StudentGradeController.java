package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.model.Student;
import sit.int204.classicmodelsservice.services.StudentGradeService;

@RestController
@RequestMapping("/api/grades")
public class StudentGradeController {
    @Autowired
    StudentGradeService service;

    @GetMapping("")
    public Student calculatedGrade(@RequestBody Student student){
        return service.gradeCalculator(student);
    }
}
