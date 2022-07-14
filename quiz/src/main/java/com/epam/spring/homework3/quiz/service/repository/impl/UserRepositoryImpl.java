package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final List<User> temporaryDataBase = new ArrayList<>();

    @Override
    public User getUserByEmail(String email) throws NoSuchElementException {
        User userToGet = temporaryDataBase.stream()
                .filter(usr -> usr.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("User not found in the 'temporararyDataBase' while executing getUserByEmail"));

        log.info("REPOSITORY LAYER: getUserByEmail method " + userToGet);
        return userToGet;
    }

    @Override
    public User createUser(User user) throws ElementAlreadyExistException {
        boolean userIsAlreadyExist = temporaryDataBase.stream()
                .anyMatch(usr -> usr.getEmail().equals(user.getEmail()));

        if (userIsAlreadyExist) {
            throw new ElementAlreadyExistException("User is already exist at 'temporaryDataBase' while executing createUser");
        } else {
            temporaryDataBase.add(user);
            log.info("REPOSITORY LAYER: createUser method ");
            return user;
        }
    }

    @Override
    public User updateUserByEmail(String email, User user) throws NoSuchElementException {
        User userToUpdate = temporaryDataBase.stream() //getUserByEmail(email); <--can be replaced
                .filter(usr -> usr.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("User not found in the 'temporararyDataBase' while executing updateUserByEmail"));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());

        log.info("REPOSITORY LAYER: updateUserByEmail method " + email);
        return userToUpdate;
    }

    @Override
    public void deleteUserByEmail(String email) throws NoSuchElementException {
        boolean isDeleted = temporaryDataBase.removeIf(usr -> usr.getEmail().equals(email));

        if (!isDeleted) {
            throw new NoSuchElementException("User not found in the 'temporararyDataBase' while executing deleteUserByEmail");
        }

        log.info("REPOSITORY LAYER: deleteUserByEmail method " + email);
    }
}
