package com.lege.extend.use.domain;

import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/8 12:36
 */
public class Hobby {
    private Long id;
    private List<String> hobbys;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<String> getHobbys() {
        return hobbys;
    }
    public void setHobbys(List<String> hobbys) {
        this.hobbys = hobbys;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", hobbys=" + hobbys +
                '}';
    }
}
