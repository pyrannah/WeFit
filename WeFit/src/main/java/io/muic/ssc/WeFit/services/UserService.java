package io.muic.ssc.WeFit.services;

import io.muic.ssc.WeFit.model.AppTask;
import io.muic.ssc.WeFit.model.AppUser;

import java.text.ParseException;
import java.util.List;


public interface UserService {

    Boolean addInformationPerDay(AppUser appUser, String username);
    Boolean removeTask(AppUser task);
    Boolean updateTask(AppUser task);

    Boolean addTask(AppTask task, String username);
    void taskDateSetter(AppTask task) throws ParseException;

    List<AppUser> getAllSummary(String username);// user for checking

}