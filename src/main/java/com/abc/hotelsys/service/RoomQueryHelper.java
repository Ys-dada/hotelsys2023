package com.abc.hotelsys.service;

import com.abc.hotelsys.domain.ValueObject;

/**
 *
 * 查询助手类，主要用于收集查询条件
 *
 * @author joeyang ong
 *
 */
public class RoomQueryHelper extends ValueObject {

    private Integer qryHotelNo;
    private String qryRoomType;
    private String qryRoomStatus;

    public Integer getQryHotelNo() {
        return qryHotelNo;
    }

    public void setQryHotelNo(Integer qryHotelNo) {
        this.qryHotelNo = qryHotelNo;
    }

    public String getQryRoomType() {
        return qryRoomType;
    }

    public void setQryRoomType(String qryRoomType) {
        this.qryRoomType = qryRoomType;
    }

    public String getQryRoomStatus() {
        return qryRoomStatus;
    }

    public void setQryRoomStatus(String qryRoomStatus) {
        this.qryRoomStatus = qryRoomStatus;
    }

}
