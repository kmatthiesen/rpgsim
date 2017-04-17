package kevin.matt.commodities;

import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 * Created by kmatthiesen on 12/25/16.
 */
public class Commodity {

    private static final String nameName = "name";
    private static final String baseQuantityName = "base.quantity";
    private static final String basePriceName = "base.price";
    private static final String surplusPriceScalingName = "surplus.price.scaling";
    private static final String deficitPriceScalingName = "deficit.price.scaling";

    private String name;
    private Double baseQuantity;
    private Double currentQuantity;
    private Double basePrice;
    private Double currentPrice;
    private Double surplusPriceScaling;
    private Double deficitPriceScaling;

    public Commodity(Resource resource) {

        String fileName = resource.getFilename();
        String bundleName = "models/" + fileName.substring(0,fileName.indexOf('.'));

        ResourceBundle properties = ResourceBundle.getBundle(bundleName);
        this.name = properties.getString(nameName);
        this.baseQuantity = Double.parseDouble(properties.getString(baseQuantityName));
        this.currentQuantity = baseQuantity;
        this.basePrice = Double.parseDouble(properties.getString(basePriceName));
        this.currentPrice = basePrice;
        this.surplusPriceScaling = Double.parseDouble(properties.getString(surplusPriceScalingName));
        this.deficitPriceScaling = Double.parseDouble(properties.getString(deficitPriceScalingName));
    }

    public void adjustQuantity(Double quantityAdjustment) {
        currentQuantity += quantityAdjustment;
        if (currentQuantity < 0) {
            currentQuantity = 0.0;
        }
        calculatePrice();
    }

    public void calculatePrice() {
        Double newPrice;
        if (currentQuantity > baseQuantity * 1.2 ) {
            newPrice = currentPrice - (basePrice * surplusPriceScaling);
            if (newPrice < basePrice / 3) {
                currentPrice = basePrice / 3;
            } else {
                currentPrice = newPrice;
            }
        } else if (currentQuantity < baseQuantity * 1.2) {
            newPrice = currentPrice + (basePrice * deficitPriceScaling);
            if (newPrice > basePrice * 3) {
                currentPrice = basePrice * 3;
            } else {
                currentPrice = newPrice;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public Double getBaseQuantity() {
        return baseQuantity;
    }

    public void setBaseQuantity(Double baseQuantity) {
        this.baseQuantity = baseQuantity;
    }

    public Double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }


    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getSurplusPriceScaling() {
        return surplusPriceScaling;
    }

    public void setSurplusPriceScaling(Double surplusPriceScaling) {
        this.surplusPriceScaling = surplusPriceScaling;
    }

    public Double getDeficitPriceScaling() {
        return deficitPriceScaling;
    }

    public void setDeficitPriceScaling(Double deficitPriceScaling) {
        this.deficitPriceScaling = deficitPriceScaling;
    }
}