package kevin.matt.models;

/**
 * Created by kmatthiesen on 12/25/16.
 */
public interface Commodity {

    void calculatePrice();
    void adjustQuantity(Double quantityAdjustment);
}
