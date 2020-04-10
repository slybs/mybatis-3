package com.lege.officialcn.domain.resultMapTestDomain;

import org.apache.commons.collections.CollectionUtils;

/**
 * @Author 了个
 * @date 2020/1/13 10:11
 */
public class MapperOrder {
    private int OrderId;
    private int UserId;
    private int ProductId;
    private MapperUser mapperUser;


    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public MapperUser getMapperUser() {
        return mapperUser;
    }

    public void setMapperUser(MapperUser mapperUser) {
        this.mapperUser = mapperUser;
    }

    @Override
    public String toString() {
        return "MapperOrder{" +
                "OrderId=" + OrderId +
                ", UserId=" + UserId +
                ", ProductId=" + ProductId +
                ", mapperUser=" +( mapperUser == null ? mapperUser:mapperUser.toString())  +
                '}';
    }
}
