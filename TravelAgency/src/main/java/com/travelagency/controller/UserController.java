package com.travelagency.controller;

import com.travelagency.model.User;
import com.travelagency.model.Reservation;
import com.travelagency.service.UserService;
import com.travelagency.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Korisnik sa ovom email adresom već postoji.");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, 
                            @RequestParam("password") String password, 
                            Model model, HttpSession session) {
        User user = userService.getUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Neispravan email ili lozinka");
            return "login";
        }

        session.setAttribute("loggedUser", user);
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/user/login";
        }

        model.addAttribute("user", loggedUser);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateUserProfile(@ModelAttribute("user") User user, 
                                    BindingResult result, 
                                    HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "profile";
        }

        User loggedUser = (User) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/user/login";
        }

        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());
        loggedUser.setDateOfBirth(user.getDateOfBirth());
        loggedUser.setJmbg(user.getJmbg());
        loggedUser.setAddress(user.getAddress());
        loggedUser.setPhoneNumber(user.getPhoneNumber());

        userService.saveUser(loggedUser);
        session.setAttribute("loggedUser", loggedUser);
        
        model.addAttribute("success", "Profil je uspešno ažuriran.");
        return "profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, 
                                 @RequestParam("newPassword") String newPassword, 
                                 @RequestParam("confirmPassword") String confirmPassword, 
                                 HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/user/login";
        }

        if (!loggedUser.getPassword().equals(oldPassword)) {
            model.addAttribute("error", "Stara lozinka nije ispravna.");
            return "profile";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Nove lozinke se ne poklapaju.");
            return "profile";
        }

        loggedUser.setPassword(newPassword);
        userService.saveUser(loggedUser);
        
        model.addAttribute("success", "Lozinka je uspešno promenjena.");
        return "profile";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    @GetMapping("/profile/reservations")
    public String showUserReservations(Model model, HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/user/login";
        }

        List<Reservation> reservations = reservationService.getReservationsByUser(loggedUser.getId());
        model.addAttribute("reservations", reservations);
        return "reservations";
    }
}
