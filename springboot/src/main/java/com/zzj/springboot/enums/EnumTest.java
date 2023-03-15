package com.zzj.springboot.enums;

public enum EnumTest {
    /**
     * 01 - 卡号黑名单
     **/
    CARD("01", "卡号黑名单"),
    /**
     * 02 - 商户号黑名单
     **/
    MERNUMBER("02", "商户号黑名单"),
    /**
     * 03 - 商户名称黑名单
     */
    MERNAME("03","商户名称黑名单"),
    /**
     * 04 - 国家黑名单
     */
    COUNTRY("04", "国家黑名单"),
    /**
     * 05 - 地区黑名单
     */
    REGION("05","地区黑名单");

    /**
     * 枚举值
     */
    private String dictCode;
    /**
     * 枚举含义
     */
    private String dictName;

    /**
     * 构造方法
     *
     * @param dictCode String
     * @param dictName String
     */
    EnumTest(String dictCode, String dictName) {
        this.dictCode = dictCode;
        this.dictName = dictName;
    }

    /**
     * 获取枚举值
     *
     * @return dictCode
     */
    public String getCode() {
        return this.dictCode;
    }

    /**
     * 获取枚举信息
     *
     * @return dictName
     */
    public String getDescription() {
        return this.dictName;
    }
}
