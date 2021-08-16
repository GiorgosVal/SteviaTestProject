package utils;

public class PricesUtils {

    private PricesUtils() {

    }

    /**
     * <p>
     * Formats a price in the form of 'amount.decimal currency'. Examples:
     * <ul>
     *     <li>'$ 50' will return '50.00 $'</li>
     *     <li>'USD 50.1' will return '50.10 USD'</li>
     * </ul>
     * </p>
     */
    public static String normalizedPrice(String price) {

        return getIntegralAmount(price)
                + "."
                + getCents(price)
                + " "
                + getCurrency(price);
    }

    /**
     * Retrieves the value of a price after deleting any currency symbol (like $ or USD).
     */
    private static String rawValue(String price) {

        return price.replaceAll("[a-zA-Z\\p{Sc}\\s]", "");
    }

    /**
     * Retrieves the currency of a price which can be a symbol like ($) or name like (USD).
     */
    private static String getCurrency(String price) {

        return price
                .replaceAll("\\s", "")
                .replaceAll("[^a-zA-Z\\p{Sc}]", "");
    }

    /**
     * Retrieves the decimal amount of a price and fills missing zeros.
     */
    private static String getCents(String price) {

        String cents = "00";
        String[] parts = getPriceSplit(price);
        if (parts.length > 1) {
            String possiblyCents = parts[parts.length - 1];
            if (possiblyCents.length() == 1) {
                cents = possiblyCents + "0";
            } else if (possiblyCents.length() == 2) {
                cents = possiblyCents;
            }
        }

        return cents;
    }

    /**
     * Retrieves the integral part of a price, having removed any commas (,) or dots (.).
     */
    private static String getIntegralAmount(String price) {

        String integralAmount;
        String[] parts = getPriceSplit(price);
        StringBuilder builder = new StringBuilder();
        if (parts.length > 1) {
            String lastElement = parts[parts.length - 1];
            if (lastElement.length() < 3) {
                for (int i = 0; i < parts.length - 1; i++) {
                    builder.append(parts[i]);
                }
            } else {
                for (String part : parts) {
                    builder.append(part);
                }
            }
            integralAmount = builder.toString();
        } else {
            integralAmount = parts[0];
        }
        return integralAmount;
    }

    /**
     * Splits a price if it contains commas (.) or dots (,) and returns it as array.
     */
    private static String[] getPriceSplit(String price) {

        String raw = rawValue(price);
        if (raw.contains(".") || raw.contains(",")) {
            return raw.split("[.,]");
        } else {
            return new String[] {raw};
        }
    }
}
