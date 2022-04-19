import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;


public class AddGradeTest {
    @Test
    public void addGrade_notInRepo_Valid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");

        NotaXMLRepo gradeRepo = new NotaXMLRepo("src/main/resources/Note.xml");
        NotaValidator notaValidator = new NotaValidator(studentRepo, temaRepo);
        Service gradeService = new Service(studentRepo, null, temaRepo, null, gradeRepo, notaValidator);
        Nota nota = new Nota("1", "333", "2", 10, LocalDate.now());
        var addedNota = gradeService.addNota(nota, "well done!");
        assertEquals(addedNota, nota.getNota());
        gradeService.deleteNota("1");
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

    @Test
    public void addAssignment_notInRepo_Valid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "WBT", 10, 5);
        Tema addedTema = temaService.addTema(tema);
        assertNull(addedTema);
        assertEquals(temaService.findTema("1"), tema);
        temaService.deleteTema("1");
    }

    @Test
    public void addGrade_Valid_Integration_Test(){
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");

        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("01", "WBT", 10, 5);
        Tema addedTema = temaService.addTema(tema);

        StudentValidator studentValidator = new StudentValidator();
        Service studentService = new Service(studentRepo, studentValidator, null, null, null, null);
        Student student = new Student("00", "Ana Popa", 917, "ana@yahoo.com");
        Student foundStudent = studentService.addStudent(student);

        NotaXMLRepo gradeRepo = new NotaXMLRepo("src/main/resources/Note.xml");
        NotaValidator notaValidator = new NotaValidator(studentRepo, temaRepo);
        Service gradeService = new Service(studentRepo, null, temaRepo, null, gradeRepo, notaValidator);
        Nota nota = new Nota("01", "00", "01", 10, LocalDate.now());
        var addedNota = gradeService.addNota(nota, "well done!");
        //assertNull(addedNota);;
        //Tema tema = new Tema("2", "SSVV", 10, 5);
        assertEquals(addedNota, nota.getNota());

        studentService.deleteStudent("00");
        temaService.deleteTema("01");
        gradeService.deleteNota("01");
    }

    @Test
    public void addAssignment_Integration_Test() {
        StudentXMLRepo studentRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        Student student = new Student("000", "Ana Dana", 917, "ana@yahoo.com");

        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(studentRepo, studentValidator, temaRepo, temaValidator, null, null);

        Student foundStudent = temaService.addStudent(student);
        assertNull(foundStudent);
        assertEquals(temaService.findStudent("000"), student);

        Tema tema = new Tema("001", "Integration testing", 10, 5);
        Tema addedTema = temaService.addTema(tema);
        assertNull(addedTema);
        assertEquals(temaService.findTema("001"), tema);

        temaService.deleteTema("001");
        temaService.deleteStudent("000");
    }

}
