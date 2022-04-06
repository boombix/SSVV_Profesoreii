package repository;

import domain.Tema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import validation.TemaValidator;
import validation.ValidationException;
import validation.Validator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentRepositoryTest {
    TemaRepository assignmentRepository;
    Validator<Tema> validator = new TemaValidator();

    @BeforeEach
    public void setUp() {
        assignmentRepository = new TemaRepository(validator);
    }

    @Test
    @DisplayName("")
    public void assignmentRepository_addAssignment_validNonExistingAssignment() {
        assertNull(this.assignmentRepository.save(new Tema("123", "abc", 1, 1)));
    }

//    @Test
//    @DisplayName("")
//    public void assignmentRepository_addAssignment_alreadyExistingAssignment() {
//        assignmentRepository.save(new Tema("123", "abc", 1, 1));
//        assertNotNull(assignmentRepository.save(new Tema("123", "abc", 1, 1)));
//        assertEquals(1, assignmentRepository.findAll().spliterator().getExactSizeIfKnown());
//    }

    @Test
    @DisplayName("")
    public void assignmentRepository_addAssignment_invalidId() {
        Tema assignment = assignmentRepository.save(new Tema(null, "abc", 1, 1));
        assertNull(assignment);

        assignment = assignmentRepository.save(new Tema("", "abc", 1, 1));
        assertNull(assignment);
//        ValidationException exception =
//            assertThrows(ValidationException.class,
//                () -> assignmentRepository.save(new Tema(null, "abc", 1, 1)));
//        assertEquals("ID invalid! \n", exception.getMessage());
//        exception =
//            assertThrows(ValidationException.class,
//                () -> assignmentRepository.save(new Tema("", "abc", 1, 1)));
//        assertEquals("ID invalid! \n", exception.getMessage());
    }

    @Test
    @DisplayName("")
    public void assignmentRepository_addAssignment_invalidDescription() {
        Tema assignment = assignmentRepository.save(new Tema("123", null, 1, 1));
        assertNull(assignment);

        assignment = assignmentRepository.save(new Tema("123", "", 1, 1));
        assertNull(assignment);
//        ValidationException exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", null, 1, 1)));
//        assertEquals("Descriere invalida! \n", exception.getMessage());
//        exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", "", 1, 1)));
//        assertEquals("Descriere invalida! \n", exception.getMessage());
    }

    @Test
    @DisplayName("")
    public void assignmentRepository_addAssignment_invalidDeadline() {
        Tema assignment = assignmentRepository.save(new Tema("123", "abc", 15, 1));
        assertNull(assignment);

        assignment = assignmentRepository.save(new Tema("123", "abc", 0, 1));
        assertNull(assignment);

        assignment = assignmentRepository.save(new Tema("123", "abc", 3, 4));
        assertNull(assignment);
//        ValidationException exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", "abc", 15, 1)));
//        assertEquals("Deadline invalid! \n", exception.getMessage());
//        exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", "abc", 0, 1)));
//        assertEquals("Deadline invalid! \n", exception.getMessage());
//        exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", "abc", 3, 4)));
//        assertEquals("Deadline invalid! \n", exception.getMessage());
    }

    @Test
    @DisplayName("")
    public void assignmentRepository_addAssignment_invalidStartline() {
        Tema assignment = assignmentRepository.save(new Tema("123", "abc", 1, 15));
        assertNull(assignment);
//        ValidationException exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", "abc", 1, 15)));
//        assertEquals("Deadline invalid! \n", exception.getMessage());
//        exception =
//                assertThrows(ValidationException.class,
//                        () -> assignmentRepository.save(new Tema("123", "abc", 1, 2)));
//        assertEquals("Data de primire invalida! \n", exception.getMessage());
    }
}
