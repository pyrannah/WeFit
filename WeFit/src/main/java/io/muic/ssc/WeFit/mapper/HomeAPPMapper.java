package io.muic.ssc.WeFit.mapper;

//import ooc.squishtable.main.model.AppTask;
import io.muic.ssc.WeFit.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeAPPMapper implements RowMapper<AppUser> {

    public static final String BASE_SQL
            = "SELECT * From HOME_APP u ";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser homeAPP = new AppUser();
        homeAPP.setDate(rs.getDate("date"));
        homeAPP.setConsumedCal(rs.getInt("consumed_cal"));
        homeAPP.setBurnedCal(rs.getInt("burned_cal"));
        homeAPP.setSuggestedCal(rs.getInt("suggested_cal"));

        return homeAPP;
    }
}
