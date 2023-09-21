package vinci.be.spring1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Produit {
  private  int id ;
  private  String name;
  private  String category;

  private long price ;
}
