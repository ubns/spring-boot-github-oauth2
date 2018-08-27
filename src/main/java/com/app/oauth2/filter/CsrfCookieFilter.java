package com.app.oauth2.filter;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {

    /** CookieにCSRFを設定する際の名称 */
    private static final String CSRF_COOKIE_NAME = "_ctkn";

    /** CookieにCSRFを設定する際の有効範囲 */
    private static final String CSRF_COOKIE_PATH = "/login";


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        final CsrfToken csrf = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());

        if (csrf != null) {
            final String token = csrf.getToken();
            Cookie cookie = WebUtils.getCookie(httpServletRequest, CSRF_COOKIE_NAME);
            if (cookie != null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(CSRF_COOKIE_NAME, token);
                cookie.setPath(CSRF_COOKIE_PATH);
                httpServletResponse.addCookie(cookie);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
