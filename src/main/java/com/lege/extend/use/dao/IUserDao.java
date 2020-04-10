package com.lege.extend.use.dao;

//import com.lege.domain.User;



import com.lege.extend.use.domain.User;
import com.lege.extend.use.plugins.customPlugin02_shardtable01.DateTableShardStrategy;
import com.lege.extend.use.plugins.customPlugin02_shardtable01.TableShard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户的持久层接口
 */
@TableShard(tableName = "user", shardStrategy = DateTableShardStrategy.class)
public interface IUserDao {
    User getUserById(@Param("id") int id);
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();
}
