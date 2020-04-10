package com.lege.officialcn.dao;


import com.lege.officialcn.domain.TopicEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITopicDao {
    void addTopic(TopicEntity topicEntity);

    TopicEntity getTopicEntityById(@Param("topicId") int id);

    List<TopicEntity> getTopicByIds(@Param("topicIds") List<Integer> topicIds);

    boolean updateTopic(TopicEntity topicEntity);

    List<Integer> getTopicIds(@Param("recommend") int recommend, @Param("status") int status);

    List<TopicEntity> getSummaryByIdsAndTopicTypeAndRecommedAndStatus(@Param("topicIds") List<Integer> topicIds, @Param("topicType") int topicType, @Param("status") int status, @Param("recommend") int recommend);
    /**
     * 分页查询
     * @param recommend
     * @param status
     * @param limit
     * @return
     */
    List<TopicEntity> paginateTopic(@Param("recommend") int recommend, @Param("status") int status, @Param("start") int start, @Param("limit") int limit);

    int paginateTopicCount(@Param("recommend") int recommend, @Param("status") int status);
}
