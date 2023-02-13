package ua.hillelit.lms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * {@link Product}
 *
 * @author Dmytro Trotsenko on 1/31/23
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(schema = "my_store")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Double price;

}
