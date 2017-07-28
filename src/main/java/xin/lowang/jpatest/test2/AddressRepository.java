
package xin.lowang.jpatest.test2;

import org.springframework.stereotype.Repository;

/**
 * 用户信息数据层.
 *
 * @author Wang.ch
 */
@Repository("addressRepository2")
public interface AddressRepository extends BaseRepository<Address, Long> {}
