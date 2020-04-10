package com.lege.officialcn.dao.useGeneratedKeysAndSelectKeyDao;

import com.lege.officialcn.domain.useGeneratedKeysAndSelectKeyDomain.Author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 了个
 * @date 2020/1/13 16:48
 */
public interface AuthorDao {
    public void insertAuthor1(Author author);
    public int insertAuthor2(Author author);
    public int insertAuthor21(Author author);
    public int insertAuthor3Batch(List<Author> authorList);


    public List<Author> batchGetInParamList(List<Integer> idList);
    public List<Author> batchGetInParamArray(int[] ids);
    public List<Author> batchGetInParamMap(Map<String, Object> idsMap);
}
