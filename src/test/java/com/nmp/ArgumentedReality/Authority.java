package com.nmp.ArgumentedReality;

import com.nmp.ArgumentedReality.entity.UserRole;
import com.nmp.ArgumentedReality.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik on 2017-05-26.
 */
public class Authority {

    @Autowired
    UserService userService;

    @Test
    public void buildUserAuthority() {

        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole("Philips7", "ROlE_ADMIN"));
        roles.add(new UserRole("Philips7", "ROlE_USER"));

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

        for(UserRole role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        System.out.println(authorityList.get(0).getAuthority());
        System.out.println(authorityList.get(1).getAuthority());
        System.out.println("END");
    }

}
