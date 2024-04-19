package com.example.gps.tracker.services;

import com.example.gps.tracker.models.UserDTO;
import com.example.gps.tracker.models.entities.User;
import com.example.gps.tracker.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(UserDTO userDTO) {
        User user = new User();

        String hashedPass;

        try {
            hashedPass = encryptPassword(userDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        user.setUsername(userDTO.getUserName());
        user.setPassword(hashedPass);

        userRepository.save(user);

        return user;
    }

    public User logInUser(UserDTO userDTO) {
        try {
            String hashedPass = encryptPassword(userDTO.getPassword());
            User user = userRepository.findByUsernameAndPassword(userDTO.getUserName(), hashedPass);

            if (user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials provided");
            }

            return user;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytesPass = md.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte pass : bytesPass) {
            stringBuilder.append(Integer.toHexString(0xFF & pass));
        }
        return stringBuilder.toString();
    }
}
