package com.ptit.service;

import com.ptit.model.Cours;
import com.ptit.repo.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public Cours createCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public Optional<Cours> getCoursById(int id) {
        return coursRepository.findById(id);
    }

    public Iterable<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public Cours updateCours(int id, Cours coursDetails) {
        Optional<Cours> coursOptional = coursRepository.findById(id);
        if (coursOptional.isPresent()) {
            Cours cours = coursOptional.get();
            cours.setCourseName(coursDetails.getCourseName());
            cours.setCourseCode(coursDetails.getCourseCode());
            cours.setCredits(coursDetails.getCredits());
            return coursRepository.save(cours);
        }
        return null;
    }

    public void deleteCours(int id) {
        coursRepository.deleteById(id);
    }
}
