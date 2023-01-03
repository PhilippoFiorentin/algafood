package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.UserNotFoundException;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
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
