package io.muic.ssc.WeFit.mapper;

import io.muic.ssc.WeFit.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<AppUser> {


    public static final String BASE_SQL
            = "SELECT * From USER_APP u ";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId( rs.getLong("id"));
        appUser.setUsername(rs.getString("username"));
        appUser.setPassword(rs.getString("password"));
        appUser.setGender(rs.getString("gender"));
        appUser.setDisplayName(rs.getString("display_name"));
        appUser.setWeight(rs.getInt("weight"));
        appUser.setHeight(rs.getInt("height"));
        appUser.setAge(rs.getInt("age"));

        return appUser;
    }
}
