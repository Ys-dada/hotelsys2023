package com.abc.hotelsys.domain;

import lombok.Data;

@Data
public class Room extends ValueObject{

    private Integer roomId;
    private String roomNo;
    private String roomType;
    private String[] roomEquip; // 屋内设施
    private String roomEquipStr;
    private String roomStatus;
    private String roomMemo;
    private Hotel hotel; // 所属分店


    public void setRoomEquip(String[] roomEquip) {
        this.roomEquip = roomEquip;

        StringBuilder sb = new StringBuilder();
        for(String equip:this.roomEquip)
            sb.append(equip).append("|");

        if(sb.length()>0)
            sb.deleteCharAt(sb.length()-1);

        this.roomEquipStr = sb.toString();

    }
    public void setRoomEquipStr(String roomEquipStr) {
        this.roomEquip= roomEquipStr.split("\\|");
        this.roomEquipStr = roomEquipStr;
    }


}
