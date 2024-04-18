package com.THBS.cloudkitchenapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.THBS.cloudkitchenapplication.entity.Caterer;
import com.THBS.cloudkitchenapplication.repository.CatererRepository;

@Service
public class CatererService {
    @Autowired
    private  CatererRepository catererRepository;

    public List<Caterer> getAllCaterers() {
        return catererRepository.findAll();
    }

    @SuppressWarnings("null")
    public Caterer getCatererById(Long id) {
        return catererRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Caterer createCaterer(Caterer user) {
        user.setRole("Caterer");
        return catererRepository.save(user);
    }

    @SuppressWarnings("null")
    public Caterer updateCaterer(Long id, Caterer user) {
        if (catererRepository.existsById(id)) {
            user.setId(id);
            return catererRepository.save(user);
        }
        return null;
    }

    @SuppressWarnings("null")
    public void deleteCaterer(Long id) {
        catererRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return catererRepository.findByUsername(username) != null;
    }

    

}
