package kevin.matt.commodities;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by round on 1/4/2017.
 */
@RestController
@RequestMapping(value = "/commodity")
public class CommodityService {

    private CommodityHolder commodityHolder;

    public CommodityService() {
        commodityHolder = CommodityHolder.getInstance();
    }

    @RequestMapping(value = "/view-all", method = RequestMethod.GET)
    public List<Commodity> getCommodities() {
        return commodityHolder.getCommodities();
    }

    @RequestMapping(value = "/view/{name}", method = RequestMethod.GET)
    public Commodity getCommodity(@PathVariable String name) {
        return commodityHolder.getCommodity(name);
    }

    @RequestMapping(value = "/{name}/{quantity}", method = RequestMethod.PUT)
    public Commodity editCommodityQuantity(@PathVariable String name, @PathVariable Double quantity) {
        return commodityHolder.editQuantity(name, quantity);
    }


}
