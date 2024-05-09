package helpers.Order;

import org.apache.commons.lang3.RandomStringUtils;

public class OrderRandomValues {

    public static String orderRandom() {
        return RandomStringUtils.randomAlphabetic(5, 10);
    }
}
