package it.tagetik.apps.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Product extends TagetikEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;

    String description;
    Category category;
    int quantity;
    double price;


}
