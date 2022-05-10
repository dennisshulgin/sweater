package org.shulgin.service;

import org.shulgin.entity.Role;
import org.shulgin.entity.User;
import org.shulgin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null) {
            return false;
        }
        user.setActivateCode(UUID.randomUUID().toString());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        sendActivationCode(user);

        return true;
    }

    public boolean activateUser(String code) {
        System.out.println(code);
        User user = userRepo.findByActivateCode(code);
        if(user != null) {
            user.setActivateCode(null);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public void editUser(User user, String username, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.setUsername(username);
        user.getRoles().clear();

        for(String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void editProfile(User user, String email, String password) {
        boolean isNewEmail = ((user.getEmail() != null
                && !user.getEmail().equals(email))
                || (email != null
                && !email.equals(user.getEmail())));

        if(isNewEmail) {
            user.setEmail(email);
            if(email != null && !email.isEmpty()) {
                user.setActivateCode(UUID.randomUUID().toString());
                sendActivationCode(user);
            }
        }
        if(password != null && !password.isEmpty()) {
            user.setPassword(password);
        }
        userRepo.save(user);
    }

    public void sendActivationCode(User user) {
        if(!StringUtils.isEmpty(user.getEmail())) {
            String message = "Hello! You activation link is http://localhost:8080/activate/%s";
            String readyMessage = String.format(message, user.getActivateCode());
            mailSenderService.send(user.getEmail(), "Activation code", readyMessage);
        }
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }
}
