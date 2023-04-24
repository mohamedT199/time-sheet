package com.example.Back.controller;

import com.example.Back.dto.SaveRequestDto;
import com.example.Back.entity.Requests;
import com.example.Back.entity.User;
import com.example.Back.secuirty.JwtTokenUtilies;
import com.example.Back.services.RequestServices;
import com.example.Back.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    RequestServices requestServices;
    @Autowired
    JwtTokenUtilies jwtTokenUtilies;
    @Autowired
    UserServices userServices;

    @PostMapping("/save")
    public Requests saveRequest(@RequestBody SaveRequestDto saveRequestDto, @RequestHeader HashMap headers){
        String auth = (String) headers.get("authorization");
        String email = jwtTokenUtilies.getEmailFromToken(auth.substring("Bearer ".length()));
        User user = userServices.findByEmail(email);
        Requests requests = new Requests(saveRequestDto.getFromDate(), saveRequestDto.getToDate(), saveRequestDto.getRequestType(), user);
        return requestServices.save(requests);
    }
}
