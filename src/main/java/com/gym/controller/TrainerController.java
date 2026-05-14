package com.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.entity.Trainer;
import com.gym.service.TrainerService;



@RestController
@RequestMapping("/trainers")
public class TrainerController {
	@Autowired	
 TrainerService  trainerService;
	
      @PostMapping
	public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {  // ADMIN: add trainer
		Trainer t=trainerService.createTrainer(trainer);
		return ResponseEntity.ok(t);
	}

      @GetMapping
	public ResponseEntity<List<Trainer>> getAllTrainer(){  
		List<Trainer> list=trainerService.getAllTrainer();
		return ResponseEntity.ok(list);
	}
	
	// ADMIN: update availability
	  @PutMapping("/{id}/availability")
	  public Trainer update(@PathVariable Long id,
	                        @RequestParam boolean available) {

	    Trainer t = trainerService.findById(id,available);
	    t.setAvailable(available);
	    return trainerService.createTrainer(t);
	  }
}
