package sit.int204.classicmodelsservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sit.int204.classicmodelsservice.model.Student;

@Service
public class StudentGradeService {
    public static Character gradeCalculator(double score){
        if (score < 0 || score > 100) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid score" + score + ". Score must be between 0 and 100.");
        }

        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public Student gradeCalculator(Student student){
        if (student.getScore() == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Student has invalid score!");
        }
        char calGrade = gradeCalculator(student.getScore());
        student.setGrade(calGrade);
        return student;
    }
}
