package ua.hillelit.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {@link ProductDto}
 *
 * @author Dmytro Trotsenko on 1/25/23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer id;

    private String name;

    private Double price;

}
