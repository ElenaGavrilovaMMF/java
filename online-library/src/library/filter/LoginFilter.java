package library.filter;


import library.dto.user.LoginUserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"Create","BookScearch","CreateBook","BookFullInf",
"BookList","SaveBookFile","SaveBook","ChapterFullInfo","BookWholeFullInfo","BookmarkFullInf"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            if (isUserExists(servletRequest) || isLoginPage(servletRequest)) {
                allowAccess(servletRequest, servletResponse, filterChain);
            } else {
                goBack(servletResponse);
            }
        } else {
            allowAccess(servletRequest, servletResponse, filterChain);
        }
    }

    private boolean isLoginPage(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getRequestURI().contains("login");
    }

    private void goBack(ServletResponse servletResponse) throws IOException {
        ((HttpServletResponse) servletResponse).sendRedirect("/login");
    }

    private void allowAccess(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isUserExists(ServletRequest servletRequest) {
        LoginUserDto currentUser = (LoginUserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("currentUser");
        return currentUser != null;
    }
}
