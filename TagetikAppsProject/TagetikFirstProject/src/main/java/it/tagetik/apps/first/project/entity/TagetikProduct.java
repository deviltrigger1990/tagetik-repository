package it.tagetik.apps.first.project.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TagetikProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;



}
