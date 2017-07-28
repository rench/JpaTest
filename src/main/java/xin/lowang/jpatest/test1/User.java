
package xin.lowang.jpatest.test1;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 用户信息.
 * 
 * @author Wang.ch
 *
 */
@Entity
@Table(name = "user", indexes = { @Index(name = "idx_name", columnList = "name"), @Index(name = "idx_locked", columnList = "locked") })
public class User extends BaseEntity {
    /**
     * 手机号.
     */
    @Column(name = "mobile")
    private String mobile;
    /**
     * 姓名.
     */
    @Column(name = "name")
    private String name;
    /**
     * 地址.
     */
    //mappedBy,表示当前表和address表的关系定义在Address中的user这个成员上.
    //@JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinTable(name = "user_address", joinColumns = { @JoinColumn(name = "ref_user_id") }, inverseJoinColumns = { @JoinColumn(name = "ref_address_id") })
    private List<Address> address;
    /**
     * 是否锁定.
     */
    @Column(name = "locked")
    private boolean locked;

    public User() {
        // TODO Auto-generated constructor stub
    }

    public User(String mobile, String name) {
        super();
        this.mobile = mobile;
        this.name = name;
    }

    public User(String mobile, String name, boolean locked) {
        super();
        this.mobile = mobile;
        this.name = name;
        this.locked = locked;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
