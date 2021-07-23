package io.muic.ssc.WeFit.services;

import io.muic.ssc.WeFit.dao.AppRoleDao;
import io.muic.ssc.WeFit.dao.AppUserDao;
import io.muic.ssc.WeFit.model.AppUser;
import io.muic.ssc.WeFit.utilities.EncryptorUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    AppRoleDao appRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AppUser> findAll() {
        return this.appUserDao.getAllUsers();
    }


    @Override
    public AppUser getUser(String username) {
        return this.appUserDao.findUserAccount(username);
    }


@Override
    public Boolean addNewUser(AppUser user) {
        /**
         * Check whether user already exists or not
         */
        if(checkExistedUser(user)){
            return false;
        }
        user.setPassword(EncryptorUtils.encryptPassword(user.getPassword()));
        user.setGender(user.getDisplayName());
        user.setUsername(user.getUsername());
        user.setDisplayName(user.getDisplayName());
        user.setHeight(user.getHeight());
        user.setWeight(user.getWeight());
        user.setAge(user.getAge());


        this.appUserDao.insertUser(user,0);
        return true;

    }



    @Override
    public Boolean addNewAdmin(AppUser user) {
        if(checkExistedUser(user)){
            return false;
        }
        user.setPassword(EncryptorUtils.encryptPassword(user.getPassword()));
        this.appUserDao.insertUser(user,1);
        return true;
    }


    @Override
    public Boolean updateUserInfo(String userName, AppUser user) {
        Boolean result = true;
        AppUser appUser = this.appUserDao.findUserAccount(userName);
        if(!user.getUsername().isEmpty() && !checkExistedUser(user)){    // Check the existence of user
            appUser.setUsername(user.getUsername());
        }
        else{     // User does not exist
            result = false;
        }
        if(!user.getUsername().isEmpty()) appUser.setUsername(user.getUsername());
        if(!user.getDisplayName().isEmpty()) appUser.setDisplayName(user.getDisplayName());
        if(passwordEncoder.matches(user.getConfirmPassword(), appUser.getPassword())) this.appUserDao.updateUser(appUser);
        return result;
    }


    @Override
    public AppUser getCurrentInfo(String username) {
        return this.appUserDao.findUserAccount(username);
    }


    @Override
    public Boolean checkMatching(AppUser user) {
        return passwordEncoder.matches(user.getConfirmPassword(), passwordEncoder.encode(user.getPassword()));
    }


    @Override
    public Boolean removeUser(AppUser user) {
        System.out.println(user);

        if(checkExistedUser(user)){
            this.appUserDao.removeUser(user);
            return true;
        }
        return false;
    }




    @Override
    public Boolean checkExistedUser(AppUser user){
        if(this.appUserDao.findUserAccount(user.getUsername()) != null){
            return true;
        }
        return false;
    }



    @Override
    public List<String> getRoles(AppUser user){
        return this.appRoleDao.getRoleNames(this.appUserDao.findUserAccount(user.getUsername()).getId());
    }
}
