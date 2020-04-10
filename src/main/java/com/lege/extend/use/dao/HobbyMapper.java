package com.lege.extend.use.dao;

import com.lege.extend.use.domain.Hobby;

/**
 * @Author 了个
 * @date 2020/1/8 12:36
 */
public interface HobbyMapper {
    Hobby getHobbyById(long id);
    void insertHobby(Hobby hobby);

}
