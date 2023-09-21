package be.vinci.ipl.ex1amazing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {
  private final ProductService productService;

  public ProductsController(ProductService productService) {
    this.productService = productService;
  }


  @PostMapping("/products")
  public ResponseEntity<Product> createOne(@RequestBody Product product) {
    if (product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    boolean created = productService.createOne(product);
    if(!created) return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @GetMapping("/products")
  public Iterable<Product> readAll(){
    return productService.readAll();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> readOne(@PathVariable Long id){
    if (id < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    Product pFound = productService.readOne(id);
    if(pFound == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(pFound, HttpStatus.OK);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product){
    if (id < 1 || product == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    Product pFound = productService.updatOne(product);
    if (pFound == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(pFound, HttpStatus.OK);
  }

  @DeleteMapping("/products")
  public ResponseEntity<Product> deleteAll(){
    productService.deleteAll();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Product> deleteOne(@PathVariable Long id){
    if (id < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
   Product productDeleted =  productService.deleteOne(id);
   if(productDeleted == null)   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(productDeleted, HttpStatus.OK);
  }


}
