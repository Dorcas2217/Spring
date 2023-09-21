package be.vinci.ipl.ex1amazing;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository ;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Creates a video in repository
     * @param product the video to create
     * @return true if the video was created, false if another video exists with same hash
     */
    public boolean createOne(Product product) {
      if(productRepository.existsById(product.getId())) return  false;
      productRepository.save(product);
        return true;
    }

    /**
     * Reads all videos in repository
     * @return all videos
     */
    public Iterable<Product> readAll() {
        return productRepository.findAll();
    }

    public Product readOne(Long id ){
        return productRepository.findById(id).orElse(null);
    }


    public Product updatOne(Product product){
        Product oldProduct = readOne(product.getId());
        if(oldProduct == null ) return null;
        productRepository.save(product);
        return  oldProduct;
    }

    /**
     * Deletes all products from repository
     */
    public void deleteAll() {
        productRepository.deleteAll();
    }

    /**
     * Deletes a video with a certain hash from repository
     * @param id the id of the video
     * @return the old values of the video, or null if the video couldn't be found
     */
    public Product deleteOne(Long id) {
        Product oldVideo = readOne(id);
        if (oldVideo == null) return null;
        productRepository.deleteById(id);
        return oldVideo;
    }

}
