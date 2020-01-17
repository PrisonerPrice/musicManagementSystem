/*
 *  Copyright 2019, Liwei Wang <daveywang@live.com>.
 *  All rights reserved.
 *  Author: Liwei Wang
 *  Date: 06/2019
 */

package com.prisonerprice.controller;

import com.prisonerprice.model.User;
import com.prisonerprice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequestMapping(value = {"/auth"})
public class AuthController {

    private AuthService authService;
    public static String userEmail;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestBody User user) {
        Map<String, String> token = authService.authenticate(user);
        if (token != null) userEmail = user.getEmail();
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity authenticate(@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return authenticate(user);
    }
}
