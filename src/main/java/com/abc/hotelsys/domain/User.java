package com.abc.hotelsys.domain;

import lombok.Data;

@Data
public class User extends ValueObject{
    private String userNo;
    private String userPwd;
    private String userName;

}
