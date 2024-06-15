package it.tagetik.apps.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;


@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @JsonProperty("productId")
    int productId;

    @JsonProperty("description")
    String description;

    @JsonProperty("categoryDescription")
    String categoryDescription;

    @JsonProperty("quantity")
    int quantity;

    @JsonProperty("price")
    double price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;


}
