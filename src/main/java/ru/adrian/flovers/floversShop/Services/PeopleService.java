package ru.adrian.flovers.floversShop.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.adrian.flovers.floversShop.Repositories.PersonRepositories;
import ru.adrian.flovers.floversShop.model.Person;
import ru.adrian.flovers.floversShop.security.PersonDetails;

import java.util.Optional;

@Service
public class PeopleService   {
    private final PersonRepositories personRepositories;
    @Autowired
    public PeopleService (PersonRepositories personRepositories) {
        this.personRepositories = personRepositories;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> person = personRepositories.findByName(username);

            return new PersonDetails(person.get());

    }
    @Transactional
    public Optional<Person> loadByEmail(String email) throws UsernameNotFoundException{
        Optional<Person> person = personRepositories.findByEmail(email);
        return person;
    }
    @Transactional
    public Optional<Person> loadByPhoneNumber(String number) throws UsernameNotFoundException{
        Optional<Person> person = personRepositories.findByPhoneNumber(number);
        return  person;
    }

}
