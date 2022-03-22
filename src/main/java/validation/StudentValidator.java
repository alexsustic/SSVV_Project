package validation;

import domain.Student;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator implements Validator<Student> {

    void validateNameRegEx(String name){
        Pattern namePattern = Pattern.compile("^([A-Z][a-z]*(\\-[A-Z][a-z\\-]*)? ?){2,5}$");
        Matcher matcher = namePattern.matcher(name);
        boolean matchFound = matcher.find();
        if(!matchFound) {
            throw new ValidationException("Nume incorect!");
        }
    }

    void validateEmailRegEx(String email){
        Pattern emailPattern = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        boolean matchFound = matcher.find();
        if(!matchFound) {
            throw new ValidationException("Email incorect!");
        }
    }

    /**
     * Valideaza un student
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if(entity.getID() == null){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getID().equals("")){
            throw new ValidationException("Id incorect!");
        }
        if(entity.getNume() == null){
            throw new ValidationException("Nume incorect!");
        }
        if(entity.getNume().equals("")){
            throw new ValidationException("Nume incorect!");
        }
        if(entity.getGrupa() < 0) {
            throw new ValidationException("Grupa incorecta!");
        }
        if(entity.getEmail() == null){
            throw new ValidationException("Email incorect!");
        }
        validateNameRegEx(entity.getNume());
        if(entity.getEmail().equals("")){
            throw new ValidationException("Email incorect!");
        }
        validateEmailRegEx(entity.getEmail());
    }
}
