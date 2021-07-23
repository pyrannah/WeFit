package io.muic.ssc.WeFit.controller;

import io.muic.ssc.WeFit.dao.Calculator;
import io.muic.ssc.WeFit.model.AppText;
import io.muic.ssc.WeFit.model.AppUser;
import io.muic.ssc.WeFit.services.AdminService;
import io.muic.ssc.WeFit.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/user/")

public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * Get user by using username
     * @param username
     * @return user
     */
    @GetMapping(path = "{username}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity getUserByUsername(@PathVariable("username") String username) {
        AppUser user = adminService.getUser(username);
        if (user != null)
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppText("User does not exist, try again."));
    }

    @PostMapping(path = "create/{username}/{date}/{consumed_cal}/")
    public ResponseEntity addInformationPerday(@PathVariable("date") String date, @PathVariable("consumed_cal") String consumedCal,@PathVariable("username") String username,@PathVariable("burncal") String burncal) {
        AppUser appUser = new AppUser();// have to add per day ///can use when already logged in
        appUser.setInputDateString(date);
        appUser.setConsumedCal(consumedCal);
        if (userService.addInformationPerDay(appUser, username))
            return ResponseEntity.status(HttpStatus.CREATED).body(appUser);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppText("Bad request, try again."));
    }

    @PostMapping(path = "exercise/{username}/{burncal}")
    public ResponseEntity addInformationPerday2(@PathVariable("burncal") String burncal, @PathVariable("username") String username) {
        AppUser appUser = new AppUser();// have to add per day


        appUser.setBurnedCal(burncal);// set burn

        // remember need already set up so we have information of suggest cal
        // compute
        Calculator calculator = new Calculator();
        int anssuggest = calculator.suggestedCal(appUser.getBurnedCal(),appUser.getConsumedCal(),appUser.getSuggestedCal());

        appUser.setSuggestedCal(anssuggestif (userService.addInformationPerDay(appUser, username))
        return ResponseEntity.status.CREATED).body(appUser);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppText("Bad request, try again."))

    }

    @GetMapping(path = "/summary/{username}/")
    public ResponseEntity displayAllSUMMARY(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllSummary(username));
    }
}
