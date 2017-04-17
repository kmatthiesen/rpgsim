package org.matthiesen.commodities;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by round on 1/4/2017.
 */
public class CommodityHolder {

    private static CommodityHolder ourInstance;
    private static List<Commodity> commodities;

    public static CommodityHolder getInstance() {
        if (ourInstance == null) {
            ourInstance = new CommodityHolder();
        }
        return ourInstance;
    }

    private CommodityHolder() {
        commodities = new ArrayList<>();

        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        Resource[] resources;

        try {
            resources = resolver.getResources("classpath*:/models/*.properties");

            for (Resource resource: resources){
                commodities.add(new Commodity(resource));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public Commodity getCommodity(String name) {
        for (Commodity commodity : commodities) {
            if (commodity.getName().equals(name)) {
                return commodity;
            }
        }
        return null;
    }

    public Commodity editQuantity(String name, Double quantity) {
        for (Commodity commodity : commodities) {
            if (commodity.getName().equals(name)) {
                commodity.adjustQuantity(quantity);
                return commodity;
            }
        }
        return null;
    }
}
