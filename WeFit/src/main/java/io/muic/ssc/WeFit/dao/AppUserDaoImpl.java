package io.muic.ssc.WeFit.dao;


import io.muic.ssc.WeFit.mapper.HomeAPPMapper;
import io.muic.ssc.WeFit.mapper.UserMapper;
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
public class AppUserDaoImpl extends JdbcDaoSupport implements AppUserDao {

    private UserMapper userMapper = new UserMapper();

    private HomeAPPMapper homeAPPMapper = new HomeAPPMapper();

    @Autowired
    public DataSource dataSource;

    @PostConstruct
    public void init() {
        setDataSource(dataSource);
    }

    /**
     * Find user account
     *
     * @param userName
     * @return user information
     */

    //only find user
    @Override
    public AppUser findUserAccount(String userName) {
        String sql = UserMapper.BASE_SQL + " where u.username = ? ";

        Object[] params = new Object[]{userName};
        try {
            AppUser appUserInfo = getJdbcTemplate().queryForObject(sql, params, userMapper);
            return appUserInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Get all users
     *
     * @return list of user
     */
    @Override
    public List<AppUser> getAllUsers() {
        String sql = UserMapper.BASE_SQL;
        List<AppUser> resultList;
        try {
            resultList = getJdbcTemplate().query(sql, userMapper);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Null!");
            resultList = null;
        }
        return resultList;
    }

    /*
     * find total count and then insert to every table
     */
    @Override
    public void insertUser(AppUser user, int authority) {

        String sqlForCount = "select max(id) from USER_APP";
        String sqlForCount2 = "select max(ID) from USER_ROLE";
        String sqlForInsert1 = "insert into USER_APP (id, " +
                "username, " +
                "ENCRYPTED_PASSWORD, enable, " +
                "gender, " +
                "display_name, " +
                "weight, " +
                "height, " +
                "age)\n" +
                "values (? , ? , ? , 1, ? , ?, ?, ?, ?)";

        String sqlForInsert2 = "insert into USER_ROLE (ID, USER_ID, ROLE_ID)\n" +
                "values (?, ?, ?)";
        try {
            Long lastId = getJdbcTemplate().queryForObject(sqlForCount, Long.class) + 1;
            Long lastRole = getJdbcTemplate().queryForObject(sqlForCount2, Long.class) + 1;
            Object[] params = new Object[]{lastId, user.getUsername(), user.getPassword(),
                    user.getGender(), user.getDisplayName(), user.getWeight(), user.getHeight(), user.getAge()};
            getJdbcTemplate().update(sqlForInsert1, params);

            if (authority > 0) {
                params = new Object[]{lastRole, lastId, 1};
                getJdbcTemplate().update(sqlForInsert2, params);
                lastRole++;
            }
            params = new Object[]{lastRole, lastId, 2};
            getJdbcTemplate().update(sqlForInsert2, params);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Null!");
        }
    }

    /*
     * Remove user from both tables
     */
    @Override
    public void removeUser(AppUser user) {

        String sqlForRemove1 = "delete from USER_APP where id = ?;";
        String sqlForRemove2 = "delete from USER_ROLE where USER_ID = ?;";
        try {
            Object[] params = new Object[]{user.getId()};
            getJdbcTemplate().update(sqlForRemove2, params);
            getJdbcTemplate().update(sqlForRemove1, params);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Null!");
        }
    }

    /*
     * Update user info
     */
    // for admin
    @Override
    public void updateUser(AppUser user) {

        String sqlForUpdate = "update USER_APP set username = ?, displayname = ?, weight = ?, height = ?" +
                "where USER_ID = ?";
        try {
            Object[] params = new Object[]{user.getUsername(),
                    user.getDisplayName(), user.getWeight(), user.getHeight(), user.getId()};
            getJdbcTemplate().update(sqlForUpdate, params);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Null!");
        }
    }

    // insert information
    public void insertTask(AppUser task) {
        String sqlForInsert1 = "insert into USER_APP(consumed_cal,burned_cal ,DATE)\n" +
                "    value (?,?,?);";
        try {
            Object[] params = new Object[]{task.getConsumedCal(), task.getBurnedCal(), task.getDate()};
            getJdbcTemplate().update(sqlForInsert1, params);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Null!");
        }
    }
}

