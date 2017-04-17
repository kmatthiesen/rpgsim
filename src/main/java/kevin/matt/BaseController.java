package kevin.matt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by round on 1/4/2017.
 */
@Controller
public class BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "webpages/home.html";
    }
}
