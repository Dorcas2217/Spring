package be.vinci.ipl.ex1amazing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "products")
public class Product {
  private String name;
  private String category;
  private double price;

  @Id private Long id;
}
