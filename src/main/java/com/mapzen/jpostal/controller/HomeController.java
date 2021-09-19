package com.mapzen.jpostal.controller;

import com.mapzen.jpostal.AddressParser;
import com.mapzen.jpostal.ParsedComponent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HomeController {

    @RequestMapping("/hello")
    public String home() {
        String message = "Hello";
        return message;
    }

    @RequestMapping("/convert")
    public ResponseEntity<ParsedComponent[]> addressConverter(@RequestHeader(name="address") String address) {
        AddressParser parser = AddressParser.getInstance();
        ParsedComponent[] parsedComponents = parser.parseAddress(address);
        for (ParsedComponent c : parsedComponents) {
            System.out.printf("%s: %s\n", c.getLabel(), c.getValue());
        }
        return Objects.nonNull(parsedComponents) ? new ResponseEntity<>(parsedComponents, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
