package com.travelagency.controller;

import com.travelagency.model.User;
import com.travelagency.model.Reservation;
import com.travelagency.service.UserService;
import com.travelagency.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

        // Password complexity validation
        if (!isPasswordComplex(user.getPassword())) {
            model.addAttribute("error", "Lozinka mora imati najmanje 8 karaktera, uključujući jedno veliko slovo i jedan broj.");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/user/login";
    }

    private boolean isPasswordComplex(String password) {
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find();
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
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
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

        if (!passwordEncoder.matches(oldPassword, loggedUser.getPassword())) {
            model.addAttribute("error", "Stara lozinka nije ispravna.");
            return "profile";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Nove lozinke se ne poklapaju.");
            return "profile";
        }

        loggedUser.setPassword(passwordEncoder.encode(newPassword));
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

        List<Reservation> pastReservations = reservations.stream()
                .filter(r -> r.getTravel().getDepartureDate().before(new Date()))
                .collect(Collectors.toList());

        List<Reservation> upcomingReservations = reservations.stream()
                .filter(r -> r.getTravel().getDepartureDate().after(new Date()))
                .collect(Collectors.toList());

        model.addAttribute("pastReservations", pastReservations);
        model.addAttribute("upcomingReservations", upcomingReservations);
        return "reservations";
    }

    @PostMapping("/profile/cancel-reservation/{id}")
    public String cancelReservation(@PathVariable("id") Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return "redirect:/user/profile/reservations";
    }
}
