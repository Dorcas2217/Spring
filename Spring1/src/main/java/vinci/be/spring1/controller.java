package vinci.be.spring1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

  private static  final List<Produit> produits = new ArrayList<>();
    static {
      produits.add(new Produit(1, "test", "test", 111));
      produits.add(new Produit(2, "test 2 ", "test 3", 222));
      produits.add(new Produit(3, "tst", "test", 555));
    }

  /**
   * Find the index in the list of videos of the video with a certain hash
   * @param name the hash to search for
   * @return the index of the video with this hash, or -1 if none is found
   */
  private int findIndex(String name ) {
    return produits.stream().map(Produit::getName).toList().indexOf(name);
  }
  /**
   * Check if a video with a certain hash exists
   * @param name the hash to search for
   * @return true if the video with this hash exists, false otherwise
   */
  private boolean exists(String name) {
    return findIndex(name) != -1;
  }

    /**
     * Create a product
     * @request POST /create
     * @body video to create
     * @response 209: video already exists, 201: returns created video */
    @PostMapping("/produit")
  public ResponseEntity<Produit> createOne(@RequestBody Produit produit ){
      if(exists(produit.getName()))return new ResponseEntity<>(HttpStatus.CONFLICT);

      produits.add(produit);
      return new ResponseEntity<>(produit, HttpStatus.CREATED);

    }

  /**
   * Read all videos
   * @request GET /produit
   * @response 200: returns all products
   */
  @GetMapping("/produit")
  public Iterable<Produit> readAll() {
    return produits;
  }

  /**
   * Read a produits
   * @request GET /produits/{produit}
   * @response 404: video not found, 200: returns found product
   */
  @GetMapping("/produit/{name}")
  public ResponseEntity<Produit> readOne(@PathVariable String name) {
    Produit produit = produits.stream().filter(elt -> elt.getName().equals(name)).findFirst().orElse(null);

    if(produit == null ) return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    else return  new ResponseEntity<>(produit, HttpStatus.OK);

  }


  /**
   * Update a produit
   * @request PUT /produit/{name}
   * @body new value of the product
   * @response 400: produit does not match hash, 404: produit to not found, 200: returns old value of product
   */
  @PutMapping("/produit/{name}")
  public ResponseEntity<Produit> updateOne(@PathVariable String name, @RequestBody Produit produit) {
    if (!produit.getName().equals(name)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    int index = findIndex(name);
    if (index == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    Produit oldProduct = produits.set(index, produit);
    return new ResponseEntity<>(oldProduct, HttpStatus.OK);
  }


  /**
   * Delete all products
   * @request DELETE /products
   * @response 200: all videos are deleted
   */
  @DeleteMapping("/produits")
  public void deleteAll() {
    produits.clear();
  }

  /**
   * Delete a product
   * @request  DELETE /product/{name}
   * @response 404: video not found, 200: returns deleted produit
   */
  @DeleteMapping("/produits/{name}")
  public ResponseEntity<Produit> deleteOne(@PathVariable String name) {
    int index = findIndex(name);
    if (index == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    Produit produit = produits.remove(index);
    return new ResponseEntity<>(produit, HttpStatus.OK);
  }


}
