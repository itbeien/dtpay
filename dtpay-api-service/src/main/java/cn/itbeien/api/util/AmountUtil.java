package cn.itbeien.api.util;

/**
 * @author beien
 * @date 2024-04-04 11:13
 * Copyright© 2024 beien
 */
public class AmountUtil {
    public static long yuanToFen(double amount) {
        return (long) (amount * 100);
    }

    public static double fenToYuan(long amount) {
        return  amount / 100.00;
    }
}
