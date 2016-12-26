package kevin.matt.models;

import java.util.ResourceBundle;

/**
 * Created by kmatthiesen on 12/25/16.
 */
public class Grain extends AbstractCommodity {

    private Grain() {
        throw new RuntimeException("Must use name for commodity constructors");
    }

    public Grain(String name) {
        super(name);
    }
}
