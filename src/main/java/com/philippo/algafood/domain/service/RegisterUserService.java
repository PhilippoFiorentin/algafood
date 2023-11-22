package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.UserNotFoundException;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        userRepository.detach(user);

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user))
            throw new BusinessException(
                    String.format("User with this email already exists %s", user.getEmail()));

        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = findOrFail(userId);

        if (user.passwordNotMatch(currentPassword))
            throw new BusinessException("The given password does not match the user's password.");

        user.setPassword(newPassword);
    }

    public User findOrFail(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
