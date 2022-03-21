import domain.*;
import org.junit.Test;
import repository.StudentXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class AddStudentJunitTest {

    @Test(expected = ValidationException.class)
    public void addStudent_emptyID_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("", "Ana Popa", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test
    public void addStudent_minimumLengthID_Valid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("0", "Ana Popa", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertNull(foundStudent);
        studentService.deleteStudent("0");
    }

    @Test(expected = ValidationException.class)
    public void addStudent_nullID_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student(null, "Ana Popa", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test
    public void addStudent_Valid_SuccessTest(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("700", "Cosmin Popa", 923, "cosmin@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("700"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("700");
    }

    @Test
    public void addStudent_AlreadyExists_ReturnStudent_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("700", "Cosmin Popa", 923, "cosmin@yahoo.com");
        studentService.addStudent(student);
        Student foundStudent = studentService.addStudent(student);
        assertEquals(student.getID(), foundStudent.getID());
        studentService.deleteStudent("700");
    }

    @Test(expected = ValidationException.class)
    public void addStudent_emptyName_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", "", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_nullName_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", null, 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLengthOne_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", "Alessandro", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameHyphenLengthOne_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", "Alessandro-Gabriel", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLowercaseAfterHyphen_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", "Alexandra-natalia Tudorescu", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLowercaseFamilyName_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", "Andreea ticala", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLowercaseFirstName_Invalid_Test(){
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo,studentValidator, null, null, null,null);
        Student student = new Student("1234", "andreea Ticala", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }
}
