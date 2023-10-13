package com.abc.hotelsys.domain;

import lombok.Data;

@Data
public class Product extends ValueObject{
    Integer productId;
    String productName;
    Double productPrice;
    Integer productCount;
    String productType;
}
