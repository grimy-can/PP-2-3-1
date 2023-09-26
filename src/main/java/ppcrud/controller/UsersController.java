package ppcrud.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ppcrud.model.User;
import ppcrud.service.UserService;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping
public class UsersController {


    private UserService service;

    @GetMapping(value = "/")
    public String printTask() {
        return "index";
    }

    @GetMapping(value = "/users")
    public String printUsers(ModelMap model,  @RequestParam(value = "count", required = false) String number) {
        List<User> list = service.getUsers(number);
        model.addAttribute("users", list);
        return "users";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("user", service.getUser(Long.parseLong(id)));
        return "show";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("user") User user) {
        service.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/edit_user")
    public String editUser(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("user", service.getUser(Long.parseLong(id)));
        return "edit";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam(value = "id") String id, @ModelAttribute("user") User user) {
        service.updateUser(user, Long.parseLong(id));
        return "redirect:/users";
    }


    @PostMapping("/del_user")
    public String deleteUser(@RequestParam(value = "id") String id) {
        service.deleteUser(Long.parseLong(id));
        return "redirect:/users";
    }

}
