package com.ultragreenenery.UltraGreenEnergy.service.user;

import com.ultragreenenery.UltraGreenEnergy.entity.user.User;
import com.ultragreenenery.UltraGreenEnergy.exceptions.UserAlreadyExistException;
import com.ultragreenenery.UltraGreenEnergy.exceptions.UserNotFoundException;
import com.ultragreenenery.UltraGreenEnergy.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public CustomUserDetailService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public void saveUserData(com.ultragreenenery.UltraGreenEnergy.model.user.User userDto) {
        System.out.println("userDto.getLoginName()=="+userDto.getLoginName());
        User checkuser = userRepository.findByUsername(userDto.getLoginName());
        if (checkuser != null) {
            throw new UserAlreadyExistException("The Username "+userDto.getLoginName() +" is already taken. Please try different Username");
        }
        User user=new User();
        user.setUsername(userDto.getLoginName());
        user.setEmailId(userDto.getEmailId());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setMobileNo(userDto.getMobileNo());
        user.setRegisterDate(LocalDate.now());
        user.setEnabled(true);
        user.setLoginStatus("N");
        userRepository.save(user);
    }

    public String updateUser(com.ultragreenenery.UltraGreenEnergy.model.user.User userDto) {

        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(userDto.getLoginName()));
        Update update=new Update();
        update.set("emailId",userDto.getEmailId());
        update.set("name",userDto.getName());
        update.set("password",passwordEncoder.encode(userDto.getPassword()));
        update.set("mobileNo",userDto.getMobileNo());
        User existingUser = mongoTemplate.findAndModify(query,update,new FindAndModifyOptions().returnNew(true), User.class);
        System.out.println("existingUser updated ="+existingUser);
        if(existingUser==null){
            throw new UserNotFoundException(userDto.getLoginName()+" User not found for update operation");
        }
        return userDto.getLoginName()+" User Updated Successfully..";
    }
}
