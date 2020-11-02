package com.portal.charter.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CharterDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> getRestaurants(String name, String state, String genre) {
		try {
			StringBuilder query = new StringBuilder("select * from restaurant ");
			if (name != null) {
				query.append("where lower(name) like lower('%" + name + "%') ");
			}
			if (state != null) {
				if (query.indexOf("where") != -1)
					query.append("and lower(state) like lower('%" + state + "%') ");
				else
					query.append("where lower(state) like lower('%" + state + "%') ");
			}
			if (genre != null) {
				if (query.indexOf("where") != -1)
					query.append("and lower(genre) like lower('%" + genre + "%') ");
				else
					query.append("where lower(genre) like lower('%" + genre + "%') ");
			}

			return jdbcTemplate.queryForList(query.toString(), new MapSqlParameterSource());
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getCount() {
		try {
			return jdbcTemplate.queryForObject("select count(*) from restaurant", new MapSqlParameterSource(),
					Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
