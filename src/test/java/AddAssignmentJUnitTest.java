import domain.Tema;
import org.junit.Test;
import repository.TemaXMLRepo;
import service.Service;
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

    @Test(expected = ValidationException.class)
    public void addAssignment_nullId_Invalid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema(null, "WBT", 10, 5);
        temaService.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void addAssignment_emptyDescription_Invalid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "", 10, 5);
        temaService.addTema(tema);

    }

    @Test(expected = ValidationException.class)
    public void addAssignment_smallerDeadline_InValid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "SSVV", 0, 5);
        temaService.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void addAssignment_biggerDeadline_InValid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "SSVV", 15, 5);
        temaService.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void addAssignment_biggerReceivingDate_InValid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "SSVV", 10, 15);
        temaService.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void addAssignment_smallerReceivingDate_InValid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("1", "SSVV", 0, 15);
        temaService.addTema(tema);
    }

    @Test
    public void addAssignment_alreadyInRepo_Valid_Test() {
        TemaXMLRepo temaRepo = new TemaXMLRepo("src/main/resources/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        Service temaService = new Service(null, null, temaRepo, temaValidator, null, null);
        Tema tema = new Tema("2", "SSVV", 10, 5);
        Tema addedTema = temaService.addTema(tema);
        assertEquals(addedTema, tema);
    }
}