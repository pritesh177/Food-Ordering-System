package com.FOS.Foody.Service;

import com.FOS.Foody.model.User;

public interface UserService {
    public User findByJwtToken(String jwt) throws Exception;

    public User findByEmail(String username) throws Exception;
}
