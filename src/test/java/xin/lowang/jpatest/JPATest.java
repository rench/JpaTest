
package xin.lowang.jpatest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import xin.lowang.jpatest.test2.Address;
import xin.lowang.jpatest.test2.User;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@Transactional(transactionManager="test2TransactionManager")
public class JPATest {
  @Resource(name = "userRepository2")
  private xin.lowang.jpatest.test2.UserRepository user;

  @Autowired
  @Qualifier("addressRepository2")
  private xin.lowang.jpatest.test2.AddressRepository addRep;

  @Test
  public void testQBE() {
    User u = new User();
    u.setMobile("adad");
    u.setName("aaa");
    ExampleMatcher matcher =
        ExampleMatcher.matching().withIncludeNullValues().withIgnorePaths("locked");
    user.findOne(Example.of(u, matcher));
  }

  //@Test
  public void testDelete() {
    addRep.delete(3l);
  }

  @Test
  public void test() {
    User u = new User();
    u.setName("1231231");
    Address add = new Address();
    add.setName("北京");
    add.setMoney(BigDecimal.valueOf(0.9954));
    u.setAddress(Collections.singletonList(add));
    u = user.save(u);
    System.out.println(u.getId());
  }

  @Test
  @Rollback(true)
  public void test2() {
    User u = new User();
    u.setName("1231231");
    Address add = new Address();
    add.setName("北京");
    add.setUser(u);
    add = addRep.save(add);
    System.out.println(u.getId());
  }
  //@Test
  //@Transactional(rollbackOn=Exception.class)
  public void get() {
    User u = user.findOne(3l);
    List<Address> list = u.getAddress();
    for (Address add : list) {
      User x = add.getUser();
      System.out.println(x.getName());
    }
  }
}
