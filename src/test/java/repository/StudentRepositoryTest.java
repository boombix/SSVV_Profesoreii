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
    @DisplayName("Adding a student with valid fields and checking name")
    void studentRepository_addStudent_validFields_nameSavedSuccesfully() {
        this.studentRepository.save(new Student("1000", "student1", 931));
        Student student = this.studentRepository.findOne("1000");
        assertEquals(student.getNume(), "student1");

    }

    @Test
    @DisplayName("Adding a student with valid fields and checking group")
    void studentRepository_addStudent_validFields_groupSavedSuccesfully() {
        this.studentRepository.save(new Student("1000", "student1", 931));
        Student student = this.studentRepository.findOne("1000");
        assertEquals(student.getGrupa(), 931);

    }

    @Test
    @DisplayName("Adding a student with invalid name")
    void studentRepository_addStudent_invalidName_studentNotSaved() {
        this.studentRepository.save(new Student("100", "", 931));
        Student student = this.studentRepository.findOne("100");
        assertNull(student);
    }

    @Test
    @DisplayName("Adding a student with null name")
    void studentRepository_addStudent_nullName_studentNotSaved() {
        this.studentRepository.save(new Student("100", null, 931));
        Student student = this.studentRepository.findOne("100");
        assertNull(student);
    }


    @Test
    @DisplayName("Adding a student with number group equal to min-1")
    void studentRepository_addStudent_invalidGroup_lowerThanMin_studentNotSaved() {
        this.studentRepository.save(new Student("10", "student1", 110));
        Student student = this.studentRepository.findOne("10");
        assertNull(student);
    }

    @Test
    @DisplayName("Adding a student with number group equal to min")
    void studentRepository_addStudent_invalidGroup_equalToMin_studentSavedSuccesfully() {
        this.studentRepository.save(new Student("10", "student1", 111));
        Student student = this.studentRepository.findOne("10");
        assertEquals(student.getGrupa(), 111);
    }

    @Test
    @DisplayName("Adding a student with number group equal to min+1")
    void studentRepository_addStudent_invalidGroup_greaterThanMin_studentSavedSuccesfully() {
        this.studentRepository.save(new Student("10", "student1", 112));
        Student student = this.studentRepository.findOne("10");
        assertEquals(student.getGrupa(), 112);
    }

    @Test
    @DisplayName("Adding a student with number group equal to max-1")
    void studentRepository_addStudent_invalidGroup_lowerThanMax_studentSavedSuccesfully() {
        this.studentRepository.save(new Student("10", "student1", 936));
        Student student = this.studentRepository.findOne("10");
        assertEquals(student.getGrupa(), 936);
    }

    @Test
    @DisplayName("Adding a student with number group equal to max")
    void studentRepository_addStudent_invalidGroup_equalToMax_studentSavedSuccesfully() {
        this.studentRepository.save(new Student("10", "student1", 937));
        Student student = this.studentRepository.findOne("10");
        assertEquals(student.getGrupa(), 937);
    }

    @Test
    @DisplayName("Adding a student with number group equal to max+1")
    void studentRepository_addStudent_invalidGroup_greaterThanMax_studentNotSaved() {
        this.studentRepository.save(new Student("10", "student1", 938));
        Student student = this.studentRepository.findOne("10");
        assertNull(student);
    }



}
