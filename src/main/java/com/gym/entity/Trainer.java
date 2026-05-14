package com.gym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trainer")
public class Trainer {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long id;
private String name;
private String specialization; // Yoga, Zumba, CrossFit
private int experience;
private boolean available;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSpecialization() {
	return specialization;
}
public void setSpecialization(String specialization) {
	this.specialization = specialization;
}
public int getExperience() {
	return experience;
}
public void setExperience(int experience) {
	this.experience = experience;
}
public boolean isAvailable() {
	return available;
}
public void setAvailable(boolean available) {
	this.available = available;
}


}
