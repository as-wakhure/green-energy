package com.ultragreenenery.UltraGreenEnergy.service.user;

import com.ultragreenenery.UltraGreenEnergy.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public String saveUserData(User userDto);

    public List<User> getAllUsers();

    public User getUserByName(String userName);

   public String updateUser(User userDto);

    public String deleteUserByName(String userName);
}
