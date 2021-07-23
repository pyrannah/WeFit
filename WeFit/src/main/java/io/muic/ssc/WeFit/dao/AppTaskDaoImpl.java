package io.muic.ssc.WeFit.dao;

import io.muic.ssc.WeFit.mapper.HomeAPPMapper;
import io.muic.ssc.WeFit.model.AppTask;
import io.muic.ssc.WeFit.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;



@Repository
@Transactional
public class AppTaskDaoImpl extends JdbcDaoSupport implements AppTaskDao {

    private HomeAPPMapper mapper = new HomeAPPMapper();

    @Autowired
    public DataSource dataSource;

    @PostConstruct
    public void init(){
        setDataSource(dataSource);
    }

    @Override
    public List<AppUser> findAllUserTasks(long uid) {
        String sql = HomeAPPMapper.BASE_SQL + " where u.UID = ? ";

        Object[] params = new Object[] { uid };
        try {
            List<AppUser> tasks = getJdbcTemplate().query(sql, params, mapper);
            return tasks;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertTask(AppTask task) {
        String sqlForInsert1 = "insert into content_table(TITLE, DESCRIPTION, START_DATE, END_DATE, UID)\n" +
                "    value (?,?,?,?,?);";
        try{
            Object[] params = new Object[]{ task.getTitle(),task.getDescription(),
                    task.getDateStart(), task.getDateEnd(), task.getUid() };
            getJdbcTemplate().update(sqlForInsert1, params);
        } catch(EmptyResultDataAccessException e){
            System.out.println("Null!");
        }
    }

    @Override
    public void removeTask(AppTask task) {
        String sqlForInsert1 = "delete from content_table where TID = ?";
        try{
            Object[] params = new Object[]{ task.getTid() };
            getJdbcTemplate().update(sqlForInsert1, params);
        } catch(EmptyResultDataAccessException e){
            System.out.println("Null!");
        }
    }

    @Override
    public void updateTask(AppTask task){

        String sqlForUpdate = " update contene_table " +
                "set TITLE = ?, DESCRIPTION = ?, START_DATE = ?, END_DATE = ? " +
                "where tid = ?";
        try{
            Object[] params = new Object[]{ task.getTitle(),task.getDescription(),
                    task.getDateStart(), task.getDateEnd(), task.getTid() };
            getJdbcTemplate().update(sqlForUpdate, params);
//          getJdbcTemplate().update(sqlForRemove1, params);
        }catch(EmptyResultDataAccessException e){
            System.out.println("Null!");
        }
    }
}