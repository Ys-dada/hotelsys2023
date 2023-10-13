package com.abc.hotelsys.service;

import com.abc.hotelsys.domain.ValueObject;
import lombok.Data;

/**
 *
 * 查询助手类，主要用于收集查询条件
 *
 * @author joeyang ong
 *
 */
@Data
public class ProductQueryHelper extends ValueObject {
    private String qryProductType;

}
