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
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String saveUserData(com.ultragreenenery.UltraGreenEnergy.model.user.User userDto) {
        System.out.println("userDto.getLoginName()=="+userDto.getLoginName());
        User checkuser = repository.findByUsername(userDto.getLoginName());
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
        repository.save(user);
        return userDto.getLoginName()+" User Created Successfully..";
    }

    @Override
    public List<com.ultragreenenery.UltraGreenEnergy.model.user.User> getAllUsers() {
        List<com.ultragreenenery.UltraGreenEnergy.model.user.User> userDtoList=new ArrayList<>();
       List<User> userList= repository.findAll();
        if(userList.isEmpty()){
            throw new UserNotFoundException("Users not found");
        }
            for (User user:userList){
                com.ultragreenenery.UltraGreenEnergy.model.user.User userDto=new com.ultragreenenery.UltraGreenEnergy.model.user.User();
                userDto.setLoginName(user.getUsername());
                userDto.setName(user.getName());
                userDto.setEmailId(user.getEmailId());
                userDto.setMobileNo(user.getMobileNo());
                userDto.setPassword("hidden");
                userDtoList.add(userDto);
            }


        return userDtoList;
    }

    @Override
    public com.ultragreenenery.UltraGreenEnergy.model.user.User getUserByName(String userName) {
        User user = repository.findByUsername(userName);

        if (user == null) {
            throw new UserNotFoundException(userName + " User not found");
        }
        com.ultragreenenery.UltraGreenEnergy.model.user.User userDto = new com.ultragreenenery.UltraGreenEnergy.model.user.User();
        userDto.setLoginName(user.getUsername());
        userDto.setName(user.getName());
        userDto.setEmailId(user.getEmailId());
        userDto.setMobileNo(user.getMobileNo());
        userDto.setPassword("hidden");


        return userDto;
    }

    @Override
    public String updateUser(com.ultragreenenery.UltraGreenEnergy.model.user.User userDto) {

        Query query=new Query();
        query.addCriteria(Criteria.where("loginName").is(userDto.getLoginName()));
//        User existingUser = mongoTemplate.findOne(query, User.class);
//        if(existingUser==null){
//
//            throw new UserNotFoundException("User not found for update operation");
//        }
        Update update=new Update();
        update.set("emailId",userDto.getEmailId());
        update.set("name",userDto.getName());
        update.set("password",passwordEncoder.encode(userDto.getPassword()));
        update.set("mobileNo",userDto.getMobileNo());
        update.set("enabled",true);
        update.set("loginStatus","N");
        User existingUser = mongoTemplate.findAndModify(query,update,new FindAndModifyOptions().returnNew(true), User.class);
        System.out.println("existingUser updated ="+existingUser);
        if(existingUser==null){
            throw new UserNotFoundException(userDto.getLoginName()+" User not found for update operation");
        }
        return userDto.getLoginName()+" User Updated Successfully..";
    }

    @Override
    public String deleteUserByName(String userName) {
        Query query=new Query();
        query.addCriteria(Criteria.where("loginName").is(userName));
        User existingUser = mongoTemplate.findOne(query, User.class);
        if(existingUser==null){

            throw new UserNotFoundException(userName+" User not found for delete operation");
        }
        repository.deleteByUsername(userName);
        return userName+" User Deleted Successfully..";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
