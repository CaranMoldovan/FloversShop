package ru.adrian.flovers.floversShop.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.adrian.flovers.floversShop.Repositories.PersonRepositories;
import ru.adrian.flovers.floversShop.model.Person;
import ru.adrian.flovers.floversShop.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsServices implements UserDetailsService {
    private final PersonRepositories personRepositories;
@Autowired
    public PersonDetailsServices(PersonRepositories personRepositories) {
        this.personRepositories = personRepositories;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

           Optional<Person> person= personRepositories.findByName(username);
           if (person.isEmpty())
               throw new UsernameNotFoundException("User not found");
           return new PersonDetails(person.get());
    }
}
