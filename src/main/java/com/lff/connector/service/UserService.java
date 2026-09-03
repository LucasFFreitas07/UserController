package com.lff.connector.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lff.connector.domain.UserDomain;
import com.lff.connector.dto.UserRequest;
import com.lff.connector.dto.UserResponse;
import com.lff.connector.handlers.NotFoundException;
import com.lff.connector.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getById(Long id) {

        UserDomain user = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário com id '%d' não encontrado", id)));
        return UserResponse.from(user);
    }

    public List<UserResponse> getAll() {
        return this.repository.findAll().stream()
                .map(UserResponse::from)
                .toList();
    }

    @Transactional
    public void deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException(String.format("Usuário com id '%d' não encontrado", id));
        }
        this.repository.deleteById(id);
    }

    public UserResponse createUser(UserRequest user) {
        String encodedPassword = this.passwordEncoder.encode(user.password());
        
        UserDomain domain = new UserDomain();
        domain.setFirstName(user.first_name());
        domain.setLastName(user.last_name());
        domain.setLogin(user.login());
        domain.setPassword(encodedPassword);
        domain.setEmail(user.email());
        domain.setActive(false);
        repository.save(domain);
        return UserResponse.from(domain);
    }

    public UserResponse updateUser(Long id, UserRequest user) {
        String encodedPassword = this.passwordEncoder.encode(user.password());
        UserDomain domain = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário com id '%d' não encontrado", id)));

        domain.setFirstName(user.first_name());
        domain.setLastName(user.last_name());
        domain.setLogin(user.login());
        domain.setPassword(encodedPassword);
        domain.setEmail(user.email());

        repository.save(domain);
        return UserResponse.from(domain);
    }

    public UserResponse deactivateUser(Long id) {
        UserDomain domain = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário com id '%d' não encontrado", id)));

        domain.setActive(false);
        repository.save(domain);
        return UserResponse.from(domain);
    }

    public UserResponse activateUser(Long id) {
        UserDomain domain = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário com id '%d' não encontrado", id)));

        domain.setActive(true);
        repository.save(domain);
        return UserResponse.from(domain);
    }
}
