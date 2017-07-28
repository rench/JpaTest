package xin.lowang.jpatest.test2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 用户信息数据层.
 *
 * @author Wang.ch
 */
@Repository("userRepository2")
public interface UserRepository extends BaseRepository<User, Long> {
  /**
   * 自定义扩展方法,针对这类方法，只需在接口申明,根据name查找对象,没有查询结果,返回空list
   *
   * @param name 名称
   * @return
   */
  public List<User> findByName(String name);
  /**
   * 根据name统计数量.
   *
   * @param name 名称
   * @return
   */
  public Long countByName(String name);
  /**
   * 根据name删除数据.
   *
   * @param name 名称
   * @return
   */
  public Long deleteByName(String name);
  /**
   * 根据name删除对象,并返回删除的对象.
   *
   * @param name 名称
   * @return
   */
  public List<User> removeByName(String name);
  /**
   * 根据id和name查找数据并按照name倒序.
   *
   * @param id 主键
   * @param name 名称
   * @return
   */
  public List<User> findByIdAndNameOrderByNameDesc(Long id, String name);
  /**
   * 查询被锁住的人数.
   *
   * @return
   */
  @Query(value = "select user from User user where user.locked=1")
  public List<User> countIsLocked();
}
