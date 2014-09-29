package com.xsc.lottery.yeepay.util;

import com.xsc.lottery.entity.enumerate.Bank;

public class YeeBankToStr
{
    static public String getBankCode(Bank bank)
    {
        switch (bank) {
        case 工商银行:
            return "ICBC-NET";
        case 招商银行:
            return "CMBCHINA-NET";
        case 农业银行:
            return "ABC-NET";
        case 建设银行:
            return "CCB-NET";
        case 中国银行:
            return "BOC-NET";
        case 北京银行:
            return "BCCB-NET";
        case 交通银行:
            return "BOCO-NET";
        case 兴业银行:
            return "CIB-NET";

        case 民生银行:
            return "CMBC-NET";
        case 光大银行:
            return "CEB-NET";

        case 华夏银行:
            return "HXB-NET";

        case 中信银行:
            return "ECITIC-NET";

        case 上海浦东发展银行:
            return "SPDB-NET";
        case 中国邮政储蓄:
            return "POST-NET";

        }
        return null;
    }
}
