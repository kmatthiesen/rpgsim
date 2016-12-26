package kevin.matt.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by kmatthiesen on 12/25/16.
 */
public class AbstractCommodity implements Commodity {

    protected static final String baseQuantityName = "base.quantity";
    protected static final String basePriceName = "base.price";
    protected static final String surplusPriceScalingName = "surplus.price.scaling";
    protected static final String deficitPriceScalingName = "deficit.price.scaling";

    protected ResourceBundle properties;

    private String name;
    private Double baseQuantity;
    private Double currentQuantity;
    private Double basePrice;
    private Double currentPrice;
    private Double surplusPriceScaling;
    private Double deficitPriceScaling;

    public AbstractCommodity() {
        throw new RuntimeException("Must use non default Constructor");
    }

    public AbstractCommodity(String name) {
        properties = ResourceBundle.getBundle(name);
        this.name = name;
        this.baseQuantity = Double.parseDouble(properties.getString(basePriceName));
        this.currentQuantity = baseQuantity;
        this.basePrice = Double.parseDouble(properties.getString(baseQuantityName));
        this.currentPrice = basePrice;
        this.surplusPriceScaling = Double.parseDouble(properties.getString(surplusPriceScalingName));
        this.deficitPriceScaling = Double.parseDouble(properties.getString(deficitPriceScalingName));
    }

    public void adjustQuantity(Double quantityAdjustment) {
        currentQuantity += quantityAdjustment;
        calculatePrice();
    }

    public void calculatePrice() {
        Double newPrice;
        if (currentQuantity > baseQuantity * 1.2) {
            newPrice = basePrice - (basePrice * surplusPriceScaling);
            if (newPrice < basePrice / 3) {
                currentPrice = basePrice / 3;
            } else {
                currentPrice = newPrice;
            }
        } else if (currentQuantity < baseQuantity * 1.2) {
            newPrice = basePrice + (basePrice * deficitPriceScaling);
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