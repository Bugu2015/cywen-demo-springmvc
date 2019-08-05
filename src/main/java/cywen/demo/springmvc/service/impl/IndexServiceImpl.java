package cywen.demo.springmvc.service.impl;

import cywen.demo.springmvc.dao.HbOrderMapper;
import cywen.demo.springmvc.model.HbOrder;
import cywen.demo.springmvc.service.IndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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
}
