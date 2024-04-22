package com.THBS.cloudkitchenapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.THBS.cloudkitchenapplication.DTO.CatererOrdersDTO;
import com.THBS.cloudkitchenapplication.entity.Caterer;
import com.THBS.cloudkitchenapplication.service.CatererService;
@RestController
@RequestMapping("/caterers")
public class CatererController {
    @Autowired
    private CatererService userService;

    @GetMapping("/")
    public List<Caterer> getAllCaterers() {
        return userService.getAllCaterers();
    }

    @GetMapping("/{id}")
    public Caterer getCatererById(@PathVariable Long id) {
        return userService.getCatererById(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Caterer user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userService.createCaterer(user);
        return new ResponseEntity<>("User signed up successfully!", HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCaterer(@PathVariable Long id, @RequestBody Caterer user) {
        Caterer updatedCaterer = userService.updateCaterer(id, user);
        if (updatedCaterer == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User details updated successfully!", HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCaterer(@PathVariable Long id) {
        userService.deleteCaterer(id);
    }

    @GetMapping("/orderdetails/{catererId}")
    public List<CatererOrdersDTO> getOrdersByCustomerAndCaterer(
            @PathVariable("catererId") Long catererId) {
        return userService.getOrdersByCaterer(catererId);
    }
}
