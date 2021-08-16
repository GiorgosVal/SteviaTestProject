package utils;

import com.persado.oss.quality.stevia.selenium.core.WebComponent;

public class LogUtils {

    public static String errorMessage(WebComponent page, String msg) {

        return page.getClass().getSimpleName() + ": " + msg;
    }

    private LogUtils() {

    }
}
