package cywen.demo.springmvc.service.impl;

import cywen.demo.springmvc.dao.HbOrderMapper;
import cywen.demo.springmvc.model.HbOrder;
import cywen.demo.springmvc.service.IndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

    private static final Log log = LogFactory.getLog(IndexServiceImpl.class);

    @Autowired
    private HbOrderMapper hbOrderMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public String test() {
        HbOrder hbUser = hbOrderMapper.queryById(1L);
        return hbUser.toString();
    }
}
