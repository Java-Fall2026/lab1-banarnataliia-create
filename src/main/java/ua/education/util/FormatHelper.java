package ua.education.util;

class FormatHelper {
    private FormatHelper() {}

    static String normalizeString(String value) {
        return value == null ? null : value.trim();
    }

    static String normalizeEmail(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }

    static String normalizeEnum(String value) {
        if (value == null) return null;
        return value.trim().toUpperCase();
    }

    static String formatMoney(double price) {
        return String.format("%.2f", price);
    }
}