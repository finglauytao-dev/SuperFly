package com.finglauytao.zlib.util;

import java.util.Random;

/**
 * Create on 2018/10/19
 *
 * @author finglauytao
 * @version 1.0.0
 **/
public class MathUtil {

    public static int randomInt(int value) {
        Random random = new Random();
        return random.nextInt(value);
    }

}
