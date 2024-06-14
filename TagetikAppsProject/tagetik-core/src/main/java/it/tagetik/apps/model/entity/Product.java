package it.tagetik.apps.model.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends TagetikEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int productId;

    String description;

    Category category;

    int quantity;

    double price;



}
