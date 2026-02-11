package org.main.deshimulaclone.service;


import lombok.RequiredArgsConstructor;
import org.main.deshimulaclone.model.User;
import org.main.deshimulaclone.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password) throws Exception{
        //check if username already taken
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new Exception("Username already taken!");
        }

        // username available
        user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
