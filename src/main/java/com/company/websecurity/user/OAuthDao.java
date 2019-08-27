package com.company.websecurity.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class OAuthDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserEntity getUserDetails(String username) {
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";

		List<UserEntity> users = jdbcTemplate.query(userSQLQuery,
				new String[]{username}, (ResultSet rs, int rowNum) -> {
					return UserEntity.builder().username(username).password(rs.getString("PASSWORD")).build();
				});

		if (!users.isEmpty()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
					"ROLE_SYSTEMADMIN");
			grantedAuthoritiesList.add(grantedAuthority);
			users.get(0).setGrantedAuthoritiesList(grantedAuthoritiesList);
			return users.get(0);
		}
		return null;
	}
}