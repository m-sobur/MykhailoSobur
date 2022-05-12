package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.NoSuchUserException;
import com.epam.spring.homework3.quiz.exception.repositoryException.UserAlreadyExistsException;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final List<User> temporaryDataBase = new ArrayList<>();


    @Override
    public User getUserByEmail(String email) throws NoSuchUserException {
        User userToGet = temporaryDataBase.stream()
                .filter(usr -> usr.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new NoSuchUserException("User not found in the 'temporararyDataBase' while executing getUserByEmail"));
        log.info("REPOSITORY LAYER: getUserByEmail method " + email);
        return userToGet;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        boolean userIsAlreadyExist = temporaryDataBase.stream()
                .anyMatch(usr -> usr.getEmail().equals(user.getEmail()));
        if (userIsAlreadyExist) {
            throw new UserAlreadyExistsException("User is already exist at 'temporaryDataBase' while executing createUser");
        } else {
            temporaryDataBase.add(user);
            log.info("REPOSITORY LAYER: createUser method ");
            return user;
        }
    }

    @Override
    public User updateUserByEmail(String email, User user) throws NoSuchUserException {
        User userToUpdate = temporaryDataBase.stream() //getUserByEmail(email); <--can be replaced
                .filter(usr -> usr.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new NoSuchUserException("User not found in the 'temporararyDataBase' while executing updateUserByEmail"));


        userToUpdate.setFirst_name(user.getFirst_name());
        userToUpdate.setUsr_role(user.getUsr_role());
        userToUpdate.setPasswd(user.getPasswd());
        userToUpdate.setLast_name(user.getLast_name());
        log.info("REPOSITORY LAYER: updateUserByEmail method " + email);
        return userToUpdate;
    }

    @Override
    public void deleteUserByEmail(String email) throws NoSuchUserException {
        boolean isDeleted = temporaryDataBase.removeIf(usr -> usr.getEmail().equals(email));
        if (!isDeleted) {
            throw new NoSuchUserException("User not found in the 'temporararyDataBase' while executing deleteUserByEmail");
        }
        log.info("REPOSITORY LAYER: deleteUserByEmail method " + email);
    }
}
