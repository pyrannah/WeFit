package io.muic.ssc.WeFit.dao;

import java.util.List;

public interface AppRoleDao {
    List<String> getRoleNames(Long userId);
}
