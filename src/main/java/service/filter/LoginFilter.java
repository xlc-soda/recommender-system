package service.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import redis.clients.jedis.Jedis;
import util.config.Configs;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends OncePerRequestFilter {

    private static String[] passFilter = new String[]{"/wx/auth/login", "/admin/auth/login", "/wx/auth/logout", "/admin/auth/logout"};

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();
        boolean doFilter = true;
        for(String pass: passFilter) {
            if(requestURI.contains(pass)) {
                doFilter = false;
                break;
            }
        }
        if(doFilter) {
            if(isLegalRequest(httpServletRequest)) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendRedirect("");
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private boolean isLegalRequest(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("X-Librecmall-Token");
        if(null == header) {
            header = httpServletRequest.getHeader("X-Librecmall-Admin-Token");
        }
        Jedis jedis = new Jedis(Configs.hosts.get("slave1"));
        header = jedis.get(header);
        jedis.close();
        return header != null;
    }
}
