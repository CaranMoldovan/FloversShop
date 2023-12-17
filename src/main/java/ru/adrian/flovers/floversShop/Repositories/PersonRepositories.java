package ru.adrian.flovers.floversShop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.adrian.flovers.floversShop.model.Person;

import java.util.Optional;
@Repository
public interface PersonRepositories extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
    Optional<Person>findByPhoneNumber(String number);


}
