package ru.adrian.flovers.floversShop.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adrian.flovers.floversShop.Repositories.PersonRepositories;
import ru.adrian.flovers.floversShop.model.Person;

@Service
public class RegistrationServices {

    private final PersonRepositories personRepositories;

    @Autowired
    public RegistrationServices(PersonRepositories personRepositories) {
        this.personRepositories = personRepositories;
    }
    @Transactional
    public void register(Person person){
        personRepositories.save(person);
    }
}
