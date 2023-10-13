package com.abc.hotelsys.domain;

import lombok.Data;

@Data
public class Hotel extends ValueObject{

    private int hotelId;
    private String hotelName;
    private String hotelAddr;
    private String hotelPhone;
    private int hotelRoomCount;
    /** 分店照片 */
    private byte[]  hotelPic;

}
