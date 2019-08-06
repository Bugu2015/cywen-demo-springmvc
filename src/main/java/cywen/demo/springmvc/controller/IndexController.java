package cywen.demo.springmvc.controller;

import cywen.demo.springmvc.BaseResult;
import cywen.demo.springmvc.MyContext;
import cywen.demo.springmvc.PreventRepeat;
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

    @PreventRepeat
    @RequestMapping("/")
    public String home(){
        log.info("info");
        log.debug("debug");
        return "Hello World";
    }

    @RequestMapping("/test")
    public BaseResult test(){
        return BaseResult.buildSuccess(indexService.test());
    }

    @RequestMapping("/test2")
    public BaseResult test2(){
        indexService.test21();
        indexService.test22();
        indexService.test23();
        return BaseResult.buildSuccess(MyContext.getRequestParam());
    }



}
