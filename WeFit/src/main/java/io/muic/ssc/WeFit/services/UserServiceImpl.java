package io.muic.ssc.WeFit.services;

import io.muic.ssc.WeFit.dao.AppTaskDao;
import io.muic.ssc.WeFit.dao.AppUserDao;
import io.muic.ssc.WeFit.model.AppTask;
import io.muic.ssc.WeFit.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppTaskDao taskDao;

    @Autowired
    private AppUserDao userDao;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.Date utilDateStart, utilDateEnd;

    @Override
    public Boolean addInformationPerDay(AppUser appUser, String username) {
        return null;
    }

    @Override
    public Boolean removeTask(AppUser task) {
        return null;
    }


    @Override
    public Boolean updateTask(AppUser task) {
        return null;
    }


    public Boolean addTask(AppTask task, String username) {
        Boolean success = false;
        AppUser user = this.userDao.findUserAccount(username);
        task.setUid(user.getId());
        try {
            taskDateSe
            if (utilDateEnd.after(utilDateStart)) {
                success = true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (success) this.taskDao.insertTask(task);
        }

        return success;
    }

    /**
     * Remove user's task
     *
     * @param task
     * @return true if task is removed successfully, otherwise false
     */
//    @Override
//    public Boolean removeTask(AppTask task) {
//        if (this.taskDao.findTask(task.getTid()) != null) taskDao.removeTask(task);
//        else return false;
//        return true;
//    }

    /*
    ? Must observe how user will edit the task
     */

    // compute first
    // before call this

//    @Override
//    public Boolean updateTask(AppTask task, long tid) {
//        Boolean success = false;
//        AppTask old = this.taskDao.findTask(tid);
//        if (old == null) return false;
//        try {
//            taskDateSetter(task);
//            old.setDateStart(task.getDateStart());
//            old.setDateEnd(task.getDateEnd());
//            old.setDescription(task.getDescription());
//            old.setTitle(task.getTitle());
//            // End date need to be before start date
//            if (utilDateEnd.after(utilDateStart)) {
//                success = true;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        } finally {
//            if (success) this.taskDao.updateTask(old);
//        }
//        return success;
//    }

//    @Override
//    public Boolean updateInformation(HomeAPP task) {
//
//        Boolean success = false;
//        AppTask old = this.taskDao.findTask(tid);
//        if (old == null) return false;
//        try {
//            taskDateSetter(task);
//            old.setDateStart(task.getDateStart());
//            old.setDateEnd(task.getDateEnd());
//            old.setDescription(task.getDescription());
//            old.setTitle(task.getTitle());
//            // End date need to be before start date
//            if (utilDateEnd.after(utilDateStart)) {
//                success = true;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        } finally {
//            if (success) this.taskDao.updateTask(old);
//        }
//        return success;
//    }


    // แปลงเวลา
    @Override
    public void taskDateSetter(AppTask task) throws ParseException {

        utilDateStart = format.parse(task.getInputDateStart());
        task.setDateStart(new Date(utilDateStart.getTime()));
        utilDateEnd = format.parse(task.getInputDateEnd());
        task.setDateEnd(new Date(utilDateEnd.getTime()));
    }

//    @Override
//    public List<AppUser> getAllSummary(String username) {
//        return null;
//    }

//    @Override
//    public void taskDateSetter(HomeAPP task) throws ParseException {
//
//        utilDateStart = format.parse(task.getInputDateStart());
//        task.setDateStart(new Date(utilDateStart.getTime()));
//        utilDateEnd = format.parse(task.getInputDateEnd());
//        task.setDateEnd(new Date(utilDateEnd.getTime()));
//    }

    /**
     * Get all user's tasks
     *
     * @param username
     * @return All user's tasks
     */
    @Override
    public List<AppUser> getAllSummary(String username) {
        AppUser user = this.userDao.findUserAccount(username);
        return this.taskDao.findAllUserTasks(user.getId());
    }
}
