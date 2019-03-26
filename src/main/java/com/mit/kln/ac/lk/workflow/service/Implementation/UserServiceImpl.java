package com.mit.kln.ac.lk.workflow.service.Implementation;

import com.mit.kln.ac.lk.workflow.exception.ResourceNotFoundException;
import com.mit.kln.ac.lk.workflow.model.User;
import com.mit.kln.ac.lk.workflow.repository.UserRepository;
import com.mit.kln.ac.lk.workflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> usersList=userRepository.findAll();
        return usersList ;
    }

    @Override
    public String saveUser(User user) {
        userRepository.save(user);
        return "New User "+user.getFname()+" Created";
    }

    @Override
    public User getUserByEmail(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return "User "+user.getFname()+" Deleted";
    }



    @Override
    public Optional findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }
}
