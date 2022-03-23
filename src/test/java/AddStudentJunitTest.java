import domain.Student;
import org.junit.Test;
import repository.StudentXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class AddStudentJunitTest {

    @Test(expected = ValidationException.class)
    public void addStudent_emptyID_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("", "Ana Popa", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test
    public void addStudent_minimumLengthID_Valid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("0", "Ana Popa", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertNull(foundStudent);
        studentService.deleteStudent("0");
    }

    @Test(expected = ValidationException.class)
    public void addStudent_nullID_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student(null, "Ana Popa", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test
    public void addStudent_Valid_SuccessTest() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("700", "Cosmin Popa", 923, "cosmin@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("700"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("700");
    }

    @Test
    public void addStudent_AlreadyExists_ReturnStudent_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("700", "Cosmin Popa", 923, "cosmin@yahoo.com");
        studentService.addStudent(student);
        Student foundStudent = studentService.addStudent(student);
        assertEquals(student.getID(), foundStudent.getID());
        studentService.deleteStudent("700");
    }

    @Test(expected = ValidationException.class)
    public void addStudent_emptyName_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_nullName_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", null, 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_nullEmail_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, null);
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_emptyEmail_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmail_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "taie");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_NegativeGroup_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", -1, "alex@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLengthOne_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Alessandro", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameHyphenLengthOne_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Alessandro-Gabriel", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLowercaseAfterHyphen_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Alexandra-natalia Tudorescu", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLowercaseFamilyName_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Andreea ticala", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLowercaseFirstName_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "andreea Ticala", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test
    public void addStudent_RegExName_Valid_Test1() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Andreea Ticala", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_RegExName_Valid_Test2() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Alexandra-Natalia Tudorescu", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_RegExName_Valid_Test3() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Alexandra Natalia Tudorescu", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_RegExName_Valid_Test4() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Ana Maria Ioana Popescu-Matei", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_RegExName_Valid_Test5() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Ana Maria Ioana Popescu Matei", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegExNameLength_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Ana Maria Ioana Popescu Stanescu Matei", 917, "ana@yahoo.com");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailAroundOnly_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "@");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailLetterOnLeftAndAround_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "a@");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailLetterOnRightAndAround_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "@a");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailAroundAndDot_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "@.");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailLetterAroundDot_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "a@.,");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailAroundDotLetter_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "@.a");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailAroundLetterDot_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "@a.");
        studentService.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudent_RegexEmailLetterAroundLetterDot_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "a@a.");
        studentService.addStudent(student);
    }

    @Test
    public void addStudent_RegexEmail_Valid_Test1() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "a@a.a");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_RegexEmail_Valid_Test2() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 917, "ta@ub.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_Group_Valid_Test1() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 0, "ta@ub.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_Group_Valid_Test2() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", 1, "ta@ub.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_Group_Valid_Test3() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", Integer.MAX_VALUE-1, "ta@ub.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test
    public void addStudent_Group_Valid_Test4() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", Integer.MAX_VALUE, "ta@ub.com");
        Student foundStudent = studentService.addStudent(student);
        assertEquals(studentService.findStudent("1234"), student);
        assertNull(foundStudent);
        studentService.deleteStudent("1234");
    }

    @Test(expected = ValidationException.class)
    public void addStudent_GroupLimitExceeded_Invalid_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("1234", "Sustic Alessandro", Integer.MAX_VALUE+1, "ta@ub.com");
        studentService.addStudent(student);
    }
}
