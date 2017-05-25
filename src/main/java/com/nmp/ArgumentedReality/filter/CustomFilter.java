package com.nmp.ArgumentedReality.filter;

import com.nmp.ArgumentedReality.dao.daoImpl.UserDaoImpl;
import com.nmp.ArgumentedReality.entity.User;
import com.nmp.ArgumentedReality.security.JwtTokenUtil;
import com.nmp.ArgumentedReality.service.Impl.UserServiceImpl;
import com.nmp.ArgumentedReality.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Dominik on 2017-05-25.
 */
@Component
public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    @Autowired
    UserDaoImpl userDao = new UserDaoImpl();

    @Value("${jwt.header:Authorization}")
    private String tokenHeader = "Authorization";

    @Override
    public void destroy() {}


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader(this.tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        logger.info("checking authentication for user " + username);
response.addHeader("lol","dsds");
//        List<GrantedAuthority> authorities = buildUserAuthority(mapUserRolesToRoles(user));
//        return buildUserForAuthentication(user, authorities);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);


        chain.doFilter(request,response);
    }
}