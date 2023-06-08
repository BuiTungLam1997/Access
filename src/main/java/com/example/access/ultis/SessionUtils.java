package com.example.access.ultis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    private static SessionUtils sessionUtils = null;

    public static SessionUtils getInstance() {
        if (sessionUtils == null) {
            sessionUtils = new SessionUtils();
        }
        return sessionUtils;
    }

    public void putValue(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(true);
        return session.getAttribute(key);
    }

    public void removeValue(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(key);
    }
}
