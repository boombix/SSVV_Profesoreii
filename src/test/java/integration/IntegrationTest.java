package integration;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntegrationTest {
    StudentRepository studentRepository;
    Validator<Student> studentValidator = new StudentValidator();
    TemaRepository assignmentRepository;
    Validator<Tema> assignmentValidator = new TemaValidator();
    NotaRepository gradeRepository;
    Validator<Nota> gradeValidator = new NotaValidator();

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository(studentValidator);
        assignmentRepository = new TemaRepository(assignmentValidator);
        gradeRepository = new NotaRepository(gradeValidator);
    }

    @Test
    @DisplayName("Adding a valid student")
    void studentRepository_addStudent_validFields_validNonExistingStudent() {
        this.studentRepository.save(new Student("1000", "student1", 931));
        Student student = this.studentRepository.findOne("1000");
        assertEquals(student.getNume(), "student1");

    }

    @Test
    @DisplayName("Adding a valid assignment")
    public void assignmentRepository_addAssignment_validNonExistingAssignment() {
        this.assignmentRepository.save(new Tema("1000", "assignment1", 1, 1));
        Tema assignment = this.assignmentRepository.findOne("1000");
        assertEquals(assignment.getDescriere(), "assignment1");
    }

    @Test
    @DisplayName("Adding a valid grade")
    public void gradeRepository_addGrade_validNonExistingGrade() {
        this.studentRepository.save(new Student("1000", "student1", 931));
        this.assignmentRepository.save(new Tema("1000", "assignment1", 1, 1));
        this.gradeRepository.save(new Nota(new Pair("1000","1000"), 10.00, 1, "10"));
        Nota grade = this.gradeRepository.findOne(new Pair("1000","1000"));
        assertEquals(grade.getFeedback(), "10");
    }

    @Test
    @DisplayName("Testing all tests together")
    public void allIntegrationTests() {
        studentRepository_addStudent_validFields_validNonExistingStudent();
        assignmentRepository_addAssignment_validNonExistingAssignment();
        gradeRepository_addGrade_validNonExistingGrade();
    }
}
