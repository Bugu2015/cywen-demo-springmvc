package cywen.demo.springmvc.model;

import java.io.Serializable;
import java.util.Date;

public class HbOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    // 主键，自增id
    private Long id;
    // 是否删除 0否/1是
    private Integer isDelete;
    // 标识流水号
    private String serialNo;
    // 添加时间
    private Date addTime;
    // 更新时间
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "HbOrder{" +
                "id=" + id +
                ", isDelete=" + isDelete +
                ", serialNo='" + serialNo + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}