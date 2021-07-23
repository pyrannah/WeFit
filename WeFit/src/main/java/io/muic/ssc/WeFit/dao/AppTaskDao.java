package io.muic.ssc.WeFit.dao;

import io.muic.ssc.WeFit.model.AppTask;
import io.muic.ssc.WeFit.model.AppUser;

import java.util.List;

public interface AppTaskDao {

    List<AppUser> findAllUserTasks(long uid);

    void insertTask(AppTask task);

    void removeTask(AppTask task);

    void updateTask(AppTask task);
}
