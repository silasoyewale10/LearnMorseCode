package com.dass.LearnMorseCode.controllers;


import com.dass.LearnMorseCode.models.ApplicationUser;
import com.dass.LearnMorseCode.models.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.util.LinkedList;


@Controller
public class CreateController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationUserRepo applicationUserRepo;

    @GetMapping("/create")
    public String getCreate(){
        return "create";
    }

    @PostMapping("/create")
    public RedirectView registerUser(String username, String password){
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password));
        applicationUserRepo.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new LinkedList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }
}


