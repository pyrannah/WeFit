package io.muic.ssc.WeFit.controller;

import io.muic.ssc.WeFit.model.AppText;
import io.muic.ssc.WeFit.model.AppUser;
import io.muic.ssc.WeFit.services.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    public static final String SECURED_TEXT = "Hello Admin!";

    @Autowired
    private AdminService adminService;

    @GetMapping(path = "/userlist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")


    public ResponseEntity displayAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
    }

    @PostMapping(path = "/remove/{username}")
    public ResponseEntity removeUser(@PathVariable("username") String username){
        AppUser userToRemove = adminService.getUser(username);
        if(adminService.removeUser(userToRemove)) return ResponseEntity.status(HttpStatus.FOUND).body("User has been removed.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppText("User has been found."));
    }

    /// admin access
    @GetMapping(path = "/secured")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public @ResponseBody ResponseEntity getSecured() {
        LOG.info("GET successfully called, so you can go to /admin.");
        return ResponseEntity.ok(new AppText(SECURED_TEXT)
}
