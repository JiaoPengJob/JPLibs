package com.jiaop.libs.utils;

/**
 * Created by JiaoP
 * 银行卡帮助类
 */
public class JPBankCardUtil {

    /**
     * 格式化银行卡 加*
     * 3749 **** **** 330
     *
     * @param cardNo 银行卡
     * @return 3749 **** **** 330
     */
    public static String formatCard(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card = cardNo.substring(0, 4) + " **** **** ";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 银行卡后四位
     *
     * @param cardNo
     * @return
     */
    public static String formatCardEnd4(String cardNo) {
        if (cardNo.length() < 8) {
            return "银行卡号有误";
        }
        String card = "";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

}
