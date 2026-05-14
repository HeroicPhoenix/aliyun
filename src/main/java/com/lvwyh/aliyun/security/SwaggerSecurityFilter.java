package com.lvwyh.aliyun.security;

import com.lvwyh.aliyun.config.SwaggerSecurityProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class SwaggerSecurityFilter extends OncePerRequestFilter {

    private static final Logger log = LogManager.getLogger(SwaggerSecurityFilter.class);

    private static final String LOGIN_PATH = "/swagger-auth/login";

    private static final String LOGOUT_PATH = "/swagger-auth/logout";

    private static final String SESSION_AUTH_KEY = "SWAGGER_AUTHENTICATED";

    private final SwaggerSecurityProperties properties;

    public SwaggerSecurityFilter(SwaggerSecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!Boolean.TRUE.equals(properties.getEnabled())) {
            filterChain.doFilter(request, response);
            return;
        }

        String path = normalizePath(request);
        if (!isProtectedPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientIp = getClientIp(request);
        if (!isAllowedIp(clientIp)) {
            log.warn("Swagger access denied by whitelist: ip={}, path={}", clientIp, path);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Swagger access denied");
            return;
        }

        if (LOGIN_PATH.equals(path)) {
            handleLogin(request, response);
            return;
        }
        if (LOGOUT_PATH.equals(path)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect(LOGIN_PATH);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null && Boolean.TRUE.equals(session.getAttribute(SESSION_AUTH_KEY))) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect(LOGIN_PATH);
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            writeLoginPage(response, null);
            return;
        }
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (equalsValue(properties.getUsername(), username) && equalsValue(properties.getPassword(), password)) {
            request.getSession(true).setAttribute(SESSION_AUTH_KEY, Boolean.TRUE);
            response.sendRedirect("/swagger-ui/index.html");
            return;
        }
        log.warn("Swagger login failed: username={}, ip={}", username, getClientIp(request));
        writeLoginPage(response, "用户名或密码错误");
    }

    private boolean isProtectedPath(String path) {
        return LOGIN_PATH.equals(path)
                || LOGOUT_PATH.equals(path)
                || "/swagger-ui.html".equals(path)
                || path.startsWith("/swagger-ui/")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars/swagger-ui/");
    }

    private String normalizePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        if (StringUtils.hasText(contextPath) && requestUri.startsWith(contextPath)) {
            return requestUri.substring(contextPath.length());
        }
        return requestUri;
    }

    private String getClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasText(forwardedFor)) {
            return forwardedFor.split(",")[0].trim();
        }
        String realIp = request.getHeader("X-Real-IP");
        if (StringUtils.hasText(realIp)) {
            return realIp.trim();
        }
        return request.getRemoteAddr();
    }

    private boolean isAllowedIp(String clientIp) {
        List<String> allowedIps = parseAllowedIps();
        for (String allowedIp : allowedIps) {
            if ("*".equals(allowedIp) || allowedIp.equals(clientIp) || matchesCidr(clientIp, allowedIp)) {
                return true;
            }
        }
        return false;
    }

    private List<String> parseAllowedIps() {
        List<String> result = new ArrayList<String>();
        if (!StringUtils.hasText(properties.getAllowedIps())) {
            return result;
        }
        String[] values = properties.getAllowedIps().split(",");
        for (String value : values) {
            if (StringUtils.hasText(value)) {
                result.add(value.trim());
            }
        }
        return result;
    }

    private boolean matchesCidr(String clientIp, String cidr) {
        if (!cidr.contains("/") || clientIp.contains(":")) {
            return false;
        }
        try {
            String[] cidrParts = cidr.split("/");
            long client = ipv4ToLong(clientIp);
            long network = ipv4ToLong(cidrParts[0]);
            int prefix = Integer.parseInt(cidrParts[1]);
            if (prefix < 0 || prefix > 32) {
                return false;
            }
            long mask = prefix == 0 ? 0L : 0xFFFFFFFFL << (32 - prefix) & 0xFFFFFFFFL;
            return (client & mask) == (network & mask);
        } catch (Exception e) {
            return false;
        }
    }

    private long ipv4ToLong(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IPv4 address");
        }
        long result = 0L;
        for (String part : parts) {
            int value = Integer.parseInt(part);
            if (value < 0 || value > 255) {
                throw new IllegalArgumentException("Invalid IPv4 segment");
            }
            result = (result << 8) + value;
        }
        return result;
    }

    private boolean equalsValue(String expected, String actual) {
        return expected != null && expected.equals(actual);
    }

    private void writeLoginPage(HttpServletResponse response, String errorMessage) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html;charset=UTF-8");
        String error = StringUtils.hasText(errorMessage)
                ? "<p style=\"color:#b91c1c;margin:0 0 12px;\">" + errorMessage + "</p>"
                : "";
        response.getWriter().write("<!doctype html><html><head><meta charset=\"UTF-8\"><title>Swagger Login</title>"
                + "<style>body{font-family:Arial,sans-serif;background:#f6f7f9;margin:0;}"
                + ".box{width:360px;margin:12vh auto;background:#fff;border:1px solid #ddd;padding:24px;}"
                + "label{display:block;margin:12px 0 6px;}input{width:100%;box-sizing:border-box;padding:8px;}"
                + "button{margin-top:16px;width:100%;padding:10px;background:#2563eb;color:#fff;border:0;cursor:pointer;}</style>"
                + "</head><body><div class=\"box\"><h2>Swagger Login</h2>" + error
                + "<form method=\"post\" action=\"" + LOGIN_PATH + "\">"
                + "<label>Username</label><input name=\"username\" autocomplete=\"username\">"
                + "<label>Password</label><input name=\"password\" type=\"password\" autocomplete=\"current-password\">"
                + "<button type=\"submit\">Login</button></form></div></body></html>");
    }
}
