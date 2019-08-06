package cywen.demo.springmvc.service.impl;

import cywen.demo.springmvc.common.MyContext;
import cywen.demo.springmvc.dao.HbOrderMapper;
import cywen.demo.springmvc.model.HbOrder;
import cywen.demo.springmvc.service.IndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("indexService")
@Transactional(rollbackFor = {Exception.class})
public class IndexServiceImpl implements IndexService {

    private static final Log log = LogFactory.getLog(IndexServiceImpl.class);

    @Autowired
    private HbOrderMapper hbOrderMapper;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Override
    public String test() {
        HbOrder hbOrder = hbOrderMapper.queryById(1L);
        try {
            hbOrder.setIsDelete(2);
            hbOrderMapper.update(hbOrder);
            int a= 1/0;
        } catch (Exception e){
            throw e;
        }
        return hbOrder.toString();
    }

    @Async
    @Override
    public String test21() {
        log.info("test21 : " + MyContext.getRequestParam());
        return null;
    }

    @Async
    @Override
    public String test22() {
        log.info("test22");
        return null;
    }

    @Async
    @Override
    public String test23() {
        log.info("test23");
        return null;
    }

}
