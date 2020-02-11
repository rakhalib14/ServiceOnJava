package storage.entity.point;

import storage.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Point extends BaseEntity {
    private String title;
    private String content;
    private Boolean active;
    private Date createDate;

}
