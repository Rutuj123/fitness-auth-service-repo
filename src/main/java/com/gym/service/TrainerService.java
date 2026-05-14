package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.entity.Trainer;
import com.gym.repository.TrainerRepository;


@Service
public class TrainerService {
@Autowired	
private TrainerRepository repo;

public Trainer createTrainer(Trainer trainer) {
	return repo.save(trainer);
}

public List<Trainer> getAllTrainer(){
	return repo.findAll();
}

public Trainer findById(Long id,boolean available) {
	  Trainer t = repo.findById(id).orElseThrow();
	    t.setAvailable(available);
	return repo.save(t);
}
}
