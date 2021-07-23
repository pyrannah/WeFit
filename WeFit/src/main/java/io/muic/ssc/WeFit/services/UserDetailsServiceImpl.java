package io.muic.ssc.WeFit.services;

import io.muic.ssc.WeFit.dao.AppRoleDao;
import io.muic.ssc.WeFit.dao.AppUserDao;
import io.muic.ssc.WeFit.model.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDao appUserDAO;

    @Autowired
    private AppRoleDao roleDao;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserDAO.findUserAccount(userName);

        if (appUser == null) {
            System.out.println("AppUser not found! " + userName);
            throw new UsernameNotFoundException("AppUser " + userName + " was not found in the database");
        }

        System.out.println("Found AppUser: " + appUser);


        List<String> roleNames = this.roleDao.getRoleNames(appUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {

                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new User(appUser.getUsername(), //
                appUser.getPassword(), grantList);

        return userDetails;
    }
}
