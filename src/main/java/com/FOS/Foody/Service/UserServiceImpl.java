package com.FOS.Foody.Service;

import com.FOS.Foody.Repository.UserRepository;
import com.FOS.Foody.config.Jwtprovider;
import com.FOS.Foody.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Jwtprovider jwtprovider;

    @Override
    public User findByJwtToken(String jwt) throws Exception {
        String email=jwtprovider.getEmailFromJwtToken(jwt);
        User user=findByEmail(email);
        return user;
    }

    @Override
    public User findByEmail(String username) throws Exception {
        User user=userRepository.findByEmail(username);
        if(user==null){
            throw new Exception("User not Found");
        }
        return user;
    }
}

