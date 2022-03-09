import domain.*;
import org.junit.Test;
import repository.StudentXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import static junit.framework.TestCase.assertEquals;

public class AddStudentJunitTest {

    @Test(expected = ValidationException.class)
    public void addStudentValidationFailedTest(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("", "Ana", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test
    public void addStudentSuccessTest(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("700", "Cosmin", 923, "cosmin@yahoo.com");
        studentService.addStudent(student);
        assertEquals(studentService.findStudent("700"), student);
        studentService.deleteStudent("700");
    }
}
