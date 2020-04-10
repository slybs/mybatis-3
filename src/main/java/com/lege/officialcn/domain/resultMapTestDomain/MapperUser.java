package com.lege.officialcn.domain.resultMapTestDomain;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @Author 了个
 * @date 2020/1/13 10:10
 */
public class MapperUser {
    private int UserId;
    private String UserName;
    private String UserAddress;
    private List<MapperOrder> orders;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }


    public List<MapperOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MapperOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "MapperUser{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", UserAddress='" + UserAddress + '\'' +
                ", orders=" + ( CollectionUtils.isEmpty(orders) ? orders:orders.size()) +
                '}';
    }
}
