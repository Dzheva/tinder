package application.Utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class CookieManager {
    public static void setCookies(HttpServletResponse response, Cookie... cookies) {
        Arrays.stream(cookies).forEach(cookie -> {
            cookie.setMaxAge(43200);
            response.addCookie(cookie);
        });
    }

    public static Map<String, String> getCookies(HttpServletRequest request) {
        Map<String, String> hashMap = new HashMap<>();
        if (request.getCookies() != null) {
            Arrays.stream(request.getCookies()).forEach(cookie -> hashMap.put(cookie.getName(), cookie.getValue()));
        }
        return hashMap;
    }

    public static void removeCookies(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) return;
        Arrays.stream(request.getCookies()).forEach(cookie -> {
            cookie.setMaxAge(0);
            if (cookie.getPath() != null) cookie.setPath(cookie.getPath());
            response.addCookie(cookie);
        });
    }
}
