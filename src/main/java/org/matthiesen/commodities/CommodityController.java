package org.matthiesen.commodities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by round on 1/4/2017.
 */
@Controller
public class CommodityController {



    @RequestMapping(value = "/commodities", method = RequestMethod.GET)
    public String commodities(){
        return "webpages/commodities.html";
    }
}
