package org.apache.ibatis.aaletetest.dao;

import org.apache.ibatis.aaletetest.domain.TopicEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITopicDao {
    void addTopic(TopicEntity topicEntity);


    List<TopicEntity> getTopicByIds(@Param("topicIds") List<Integer> topicIds);
//
////    void updateRelation(TopicRelationEntity topicRelationEntity);
//
    boolean updateTopic(TopicEntity topicEntity);

    List<Integer> getTopicIds(@Param("recommend") int recommend,
                              @Param("status") int status);


    List<TopicEntity> getSummaryByIdsAndTopicTypeAndRecommedAndStatus(@Param("topicIds") List<Integer> topicIds,
                                                                      @Param("topicType") int topicType,
                                                                      @Param("status") int status,
                                                                      @Param("recommend") int recommend);

    /**
     * 分页查询
     * @param recommend
     * @param status
     * @param limit
     * @return
     */
    List<TopicEntity> paginateTopic(@Param("recommend") int recommend,
                                    @Param("status") int status,
                                    @Param("start") int start,
                                    @Param("limit") int limit);

    int paginateTopicCount(@Param("recommend") int recommend,
                           @Param("status") int status);
//
//    List<Integer> getTopicIds(@Param("recommend") int recommend,
//                              @Param("status")int status);
//
//    Integer getTopicIdByTitle(@Param("title") String title);
}
