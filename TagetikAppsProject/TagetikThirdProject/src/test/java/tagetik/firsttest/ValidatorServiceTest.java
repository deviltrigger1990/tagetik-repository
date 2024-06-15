package tagetik.firsttest;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.Application;
import it.tagetik.apps.third.project.exception.ProductNotFoundException;
import it.tagetik.apps.third.project.exception.ProductValidationException;
import it.tagetik.apps.third.project.validator.ProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"it.tageik.apps.model"})
public class ValidatorServiceTest {

    @Autowired
    private ProductValidator productValidator;

    @Test
    public void testProductWithNegativeQuantity(){


        ProductDto product = ProductDto.builder()
                .productId(2)
                .quantity(-4)
                .build();

        Throwable exception = assertThrows(ProductValidationException.class, () -> productValidator.validate(product));
        assertEquals("Quantity could not be a negative value", exception.getMessage());

    }

    @Test
    public void testProductWithNegativePrice(){

        ProductDto product = ProductDto.builder()
                .productId(2)
                .price(-2.3)
                .build();

        Throwable exception = assertThrows(ProductValidationException.class, () -> productValidator.validate(product));
        assertEquals("Price could not be a negative value", exception.getMessage());

    }


}
