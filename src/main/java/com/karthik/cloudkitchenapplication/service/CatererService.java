package com.karthik.cloudkitchenapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthik.cloudkitchenapplication.entity.Caterer;
import com.karthik.cloudkitchenapplication.repository.CatererRepository;

@Service
public class CatererService {
    @Autowired
    private CatererRepository catererRepository;

    public List<Caterer> getAllCaterers() {
        return catererRepository.findAll();
    }

    public Caterer getCatererById(Long id) {
        return catererRepository.findById(id).orElse(null);
    }

    public Caterer createCaterer(Caterer user) {
        return catererRepository.save(user);
    }

    public Caterer updateCaterer(Long id, Caterer user) {
        if (catererRepository.existsById(id)) {
            user.setId(id);
            return catererRepository.save(user);
        }
        return null;
    }

    public void deleteCaterer(Long id) {
        catererRepository.deleteById(id);
    }

    public static Caterer saveCaterer(Caterer caterer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCaterer'");
    }
 
}
