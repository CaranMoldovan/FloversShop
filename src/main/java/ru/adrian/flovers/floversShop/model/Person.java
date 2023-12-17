package ru.adrian.flovers.floversShop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
/*
Класс отвечающий за связи базы данных с кодом через Hibernate относительно таблицы users
 */
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    @NotEmpty(message = "имя не должно быть пустым")
    @Size(min = 2, max = 40, message = "имя должно иметь не менее 2 символов и не более 40")
    private String name;
    @Column(name ="password")
    @Size(min = 8,max = 32)
    private String password;

@Column(name = "email")

private String email;
@Column(name = "phone_number")

private String  phoneNumber;

    public void setId(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person() {
    }
}
