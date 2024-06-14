package it.tagetik.apps.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class TagetikEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;

    private LocalDateTime creationDate;

    private LocalDateTime lastModified;

}
