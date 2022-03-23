import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class AddAssignmentJUnitTest {

    @Test(expected = ValidationException.class)
    public void addAssignment_emptyID_Invalid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("", "WBT", 10, 5);
        temaService.addTema(tema);
    }

    @Test
    public void addAssignment_Valid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "WBT", 10, 5);
        Tema addedTema = temaService.addTema(tema);
        assertNull(addedTema);
        assertEquals(temaService.findTema("1"), tema);
        temaService.deleteTema("1");
    }
}
