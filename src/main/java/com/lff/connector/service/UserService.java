package com.lff.connector.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lff.connector.domain.UserDomain;
import com.lff.connector.dto.UserRequest;
import com.lff.connector.dto.UserResponse;
import com.lff.connector.handlers.BlankFieldException;
import com.lff.connector.handlers.NotFoundException;
import com.lff.connector.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private void validateFields(UserRequest user) {
        if (!StringUtils.hasText(user.first_name())) {
            throw new BlankFieldException("Primeiro nome não pode ser vazio");
        }
        if (!StringUtils.hasText(user.last_name())) {
            throw new BlankFieldException("Segundo nome não pode ser vazio");
        }
        if (!StringUtils.hasText(user.login())) {
            throw new BlankFieldException("Login não pode ser vazio");
        }
        if (!StringUtils.hasText(user.email())) {
            throw new BlankFieldException("E-mail não pode ser vazio");
        }
    }

    public UserDomain getById(Long id) {

        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Usuário com id '%d' não encontrado", id)));
    }

    public List<UserDomain> getAll() {
        return this.repository.findAll();
    }

    @Transactional
    public void deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException(String.format("Usuário com login <%d> não encontrado", id));
        }
        this.repository.deleteById(id);
    }

    public UserResponse createUser(UserRequest user) {
        validateFields(user);
        UserDomain domain = new UserDomain();
        domain.setFirstName(user.first_name());
        domain.setLastName(user.last_name());
        domain.setLogin(user.login());
        domain.setEmail(user.email());
        domain.setActive(false);

        repository.save(domain);

        return new UserResponse(
                domain.getFirstName(),
                domain.getLastName(),
                domain.getLogin(),
                domain.getEmail());
    }
}
