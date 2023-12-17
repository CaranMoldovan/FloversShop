package ru.adrian.flovers.floversShop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.adrian.flovers.floversShop.Services.PeopleService;
import ru.adrian.flovers.floversShop.model.Person;
import  ru.adrian.flovers.floversShop.security.PersonDetails;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;
    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;

}
    //TO
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (!peopleService.loadByEmail(person.getEmail()).isEmpty()) {
            errors.rejectValue("email", "Пользователь с таким email уже существует");
        }
        if (!peopleService.loadByPhoneNumber(person.getPhoneNumber()).isEmpty()) {
            errors.rejectValue("phoneNumber", "Пользователь с таким номером телефона уже существует");
        }
    }



}
