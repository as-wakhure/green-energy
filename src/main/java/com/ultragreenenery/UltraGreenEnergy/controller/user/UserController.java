package com.ultragreenenery.UltraGreenEnergy.controller.user;

import com.ultragreenenery.UltraGreenEnergy.model.user.User;
import com.ultragreenenery.UltraGreenEnergy.service.user.CustomUserDetailService;
import com.ultragreenenery.UltraGreenEnergy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @Autowired
//    CustomUserDetailService customUserDetailService;

    @GetMapping("/dashboard")
    public String userDashboard(){
        return "user_dashboard";

    }

    @PostMapping("/add")
    public String saveUserData(@RequestBody User userDto){
        userService.saveUserData(userDto);
        return "homepage";
    }
    @GetMapping("/home")
    public String homepage(ModelMap model){
        model.addAttribute("loginUser","Ashok");
        return "homepage";

    }


//    @GetMapping("/getAllUsers")
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> userList= userService.getAllUsers();
//        return ResponseEntity.ok(userList);
//    }
    @GetMapping("/getAllUsers")
    public String getAllUsers(ModelMap model){
        List<User> userList= userService.getAllUsers();
        model.put("userList",userList);
        return "viewAllUsers";

    }

    @GetMapping("/getUser/{name}")
    public ResponseEntity<User>  getUser(@PathVariable("name") String userName){
        User user=userService.getUserByName(userName);
        return ResponseEntity.ok(user);

    }

//    @PutMapping("/update")
//    public String updateUser(@RequestBody User userDto){
//        return  userService.updateUser(userDto);
//
//    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User userDto){
        return  userService.updateUser(userDto);

    }

    @DeleteMapping("/delete/{name}")
    public String deleteUser(@PathVariable("name") String userName){
        return userService.deleteUserByName(userName);

    }
}
