package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import com.epam.spring.homework3.quiz.controller.mapper.UserMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.UserService;
import com.epam.spring.homework3.quiz.service.model.User;
import com.epam.spring.homework3.quiz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) throws NoSuchElementException {
        log.info("SERVICE LAYER: getUserByEmail method entry" + email);

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found in the 'PostgresDB' while executing getUserByEmail"));

        log.info("SERVICE LAYER: getUserByEmail sucsses " + email);
        return user;
    }

    @Override
    public List<String> getAllUserFirstNameAndLastName() {
        log.info("SERVICE LAYER: getAllUserFirstNameAndLastName entry ");

        List<String> result = userRepository.getAllUserFirstNameAndLastName();

        log.info("SERVICE LAYER: getAllUserFirstNameAndLastName exit ");
        return result;
    }

    @Override
    public Slice<User> getAllUser(Pageable pageable) {
        log.info("SERVICE LAYER: createUser method with email entry ");

        Slice<User> result = userRepository.findAll(pageable);

        log.info("SERVICE LAYER: createUser method with email exit ");
        return result;
    }

    @Override
    @Transactional
    public User createUser(UserDto userDto) throws ElementAlreadyExistException {
        log.info("SERVICE LAYER: createUser method with email " + userDto.getEmail());

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ElementAlreadyExistException("User is already exist at 'PostgresDB' while executing createUser " + userDto.getEmail());
        }

        User user = userMapper.userDtoToUser(userDto);
        userRepository.save(user);

        log.info("SERVICE LAYER: createdUser User created exit " + user);
        return user;
    }

    @Override
    @Transactional
    public User updateUserByEmail(String email, UserDto userDto) throws NoSuchElementException {
        log.info("SERVICE LAYER: updateUserByEmail method entry " + userDto);
        User user = userMapper.userDtoToUser(userDto);

        User userToUpdate = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found in the 'PostgresDB' while executing updateUserByEmail"));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());

        userRepository.save(userToUpdate);

        log.info("SERVICE LAYER: User updated exit" + userToUpdate);
        return userToUpdate;
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) throws NoSuchElementException {
        log.info("SERVICE LAYER: deleteUserByEmail entry " + email);

        if (!userRepository.existsByEmail(email)) {
            throw new NoSuchElementException("User not found in the 'PostgresDB' while executing deleteUserByEmail " + email);
        }

        userRepository.deleteUserByEmail(email);
        log.info("SERVICE LAYER: deleteUserByEmail exit " + email);
    }
}
