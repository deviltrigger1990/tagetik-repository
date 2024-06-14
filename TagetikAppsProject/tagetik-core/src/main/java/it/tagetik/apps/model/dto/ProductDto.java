package it.tagetik.apps.model.dto;


import it.tagetik.apps.model.entity.Category;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    int productId;

    String description;

    String categoryDescription;

    int quantity;
    double price;


}
