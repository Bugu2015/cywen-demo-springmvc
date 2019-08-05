package cywen.demo.springmvc.dao;

import java.util.List;

public interface BaseMapper<T> {

    T queryById(Long id);

    List<T> queryAllList();

    Long update(T t);

}
