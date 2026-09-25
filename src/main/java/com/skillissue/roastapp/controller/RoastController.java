package com.skillissue.roastapp.controller;

import com.skillissue.roastapp.exception.InvalidRoleException;
import com.skillissue.roastapp.model.RoastRequest;
import com.skillissue.roastapp.model.RoastResponse;
import com.skillissue.roastapp.model.Role;
import com.skillissue.roastapp.service.ScoringService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoastController {

    private final Map<String , Role> roles;
    private final ScoringService scoringService;

    public RoastController(Map<String ,Role> roles ,ScoringService scoringService){
        this.roles = roles;
        this.scoringService = scoringService;
    }

    @PostMapping("/roast")
    public RoastResponse roast(@Valid @RequestBody RoastRequest request) {
        Role role = roles.get(request.role());
        if (role == null) {
            throw new InvalidRoleException("Invalid input");
        }

        int percentage = scoringService.calculateScore(role, request.ratings());

        return new RoastResponse(percentage, "");
    }
}
