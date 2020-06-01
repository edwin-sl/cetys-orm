package com.cetys.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on May, 2020
 */
@Controller
//@RequestMapping("/users")
public class UserController {
    // "Model" Layer
//    List<User> users = new ArrayList<>();
//    {
//        users.add(new User(1, "Edwin", "edwn@gmail.com", 30));
//        users.add(new User(2, "Dania", "dania@gmail.com", 27));
//        users.add(new User(3, "Daniel", "daniel@gmail.com", 23));
//    }
    // end "Model Layer
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

        userRepository.save(new User("Edwin", "edwn@gmail.com", 30));
        userRepository.save(new User("Dania", "dania@gmail.com", 27));
        userRepository.save(new User("Daniel", "daniel@gmail.com", 23));
    }

    @GetMapping("/")
    String listUsers(
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "age", defaultValue = "0") int age,
            Model model
    ){
//        if (email.isEmpty()){
//            model.addAttribute("users", userRepository.findAll());
//        } else {
//            model.addAttribute("users", userRepository.findByEmailContainingOrderByNameDesc(email));
//        }
//        if (age > 0) {
//            model.addAttribute("users", userRepository.findByAgeGreaterThanEqual(age));
//        } else {
//            model.addAttribute("users", userRepository.findAll());
//        }
        if(!email.isEmpty() && age > 0) {
            model.addAttribute("users", userRepository.findByEmailContainingAndAgeGreaterThanEqual(email, age));
        } else if(age > 0){
            model.addAttribute("users", userRepository.findByAgeGreaterThanEqual(age));
        } else if(!email.isEmpty()) {
            model.addAttribute("users", userRepository.findByEmailContainsOrderByNameDesc(email));
        } else {
            model.addAttribute("users", userRepository.findAll());
        }

//        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }

    @GetMapping("/register")
    String registration(User user, Model model){
//        var id = users.size() + 1;
        model.addAttribute("user", user);
//        model.addAttribute("id", id);
        return "user-add";
    }

    @PostMapping("/add")
    String addUser(
            User user,
            Model model
    ) {
//        users.add(user);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }

    @GetMapping("/edit/{id}")
    String editUser(
            @PathVariable("id") int id,
            Model model
    ) {
//        var user = users.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .get();
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);

        return "user-edit";
    }

    @PostMapping("/update/{id}")
    String updateUser(
            @PathVariable("id") int id,
            User editedUser,
            Model model
    ) {
//        var user = users.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .get();
//        user.setName(editedUser.getName());
//        user.setEmail(editedUser.getEmail());
//        user.setAge(editedUser.getAge());
        userRepository.save(editedUser);
        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }

    @GetMapping("/delete/{id}")
    String deleteUser(
            @PathVariable("id") int id,
            Model model
    ) {
//        var user = users.stream()
//                .filter(u -> u.getId() == id)
//                .findFirst()
//                .get();
//        users.remove(user);
        userRepository.deleteById(id);
        model.addAttribute("users", userRepository.findAll());
        return "users-list";
    }
}
