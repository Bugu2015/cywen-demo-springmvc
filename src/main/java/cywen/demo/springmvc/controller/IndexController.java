package cywen.demo.springmvc.controller;

import cywen.demo.springmvc.IndexModel;
import cywen.demo.springmvc.service.IndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    private IndexService indexService;

    @RequestMapping("/")
    public String home(){
        log.info("info");
        log.debug("debug");
        return "Hello World";
    }

    @RequestMapping("/test")
    public IndexModel test(){
        IndexModel indexModel = new IndexModel();
        indexModel.setData(indexService.test());
        return indexModel;
    }



}
