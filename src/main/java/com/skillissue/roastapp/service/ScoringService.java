package com.skillissue.roastapp.service;


import com.skillissue.roastapp.model.Role;
import com.skillissue.roastapp.model.Subcategory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScoringService {

    public int calculateScore(Role role , Map<String ,Integer> ratings){
        double percentage = 0;
        for (Subcategory sub : role.subcategories()){
            percentage += sub.weight() * ratings.get(sub.name());
        }
        if(percentage * 9 < 10){
            return 10;
        }
        return (int) Math.round(percentage * 9);
    }
}
