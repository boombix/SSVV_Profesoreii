package repository;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class StudentRepositoryTest {
    StudentRepository studentRepository;
    Validator<Student> validator = new StudentValidator();

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository(validator);
    }


    @Test
    @DisplayName("Adding a student with valid fields")
    void studentRepository_addStudent_validFields_nameSavedSuccesfully() {
        this.studentRepository.save(new Student("1000", "student1", 931));
        Student student = this.studentRepository.findOne("1000");
        assertEquals(student.getNume(), "student1");

    }

    @Test
    @DisplayName("Adding a student with invalid name")
    void studentRepository_addStudent_invalidName_studentNotSaved() {
        this.studentRepository.save(new Student("100", "", 931));
        Student student = this.studentRepository.findOne("100");
        assertNull(student);
    }

    @Test
    @DisplayName("Adding a student with invalid group")
    void studentRepository_addStudent_invalidGroup_studentNotSaved() {
        this.studentRepository.save(new Student("10", "student1", -1));
        Student student = this.studentRepository.findOne("10");
        assertNull(student);
    }


}
