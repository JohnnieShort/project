package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IStationDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IStation;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.StationFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Station;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;
@Repository
public class StationDaoImpl extends AbstractDaoImpl<IStation, Integer> implements IStationDao {

	@Override
	public IStation createEntity() {
		return new Station();
	}

	@Override
	public void update(IStation entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set name=?, updated=?, longitude=?, latitude=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setDouble(3, entity.getLongitude());
				pStmt.setDouble(4, entity.getLatitude());
				pStmt.setInt(5, entity.getId());
				pStmt.executeUpdate();
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}

	@Override
	public void insert(IStation entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (name, created, updated, longitude, latitude) values(?,?,?,?,?)",
								getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setDouble(4, entity.getLongitude());
				pStmt.setDouble(5, entity.getLatitude());

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	protected String getTableName() {

		return "station";
	}

	@Override
	protected IStation parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IStation entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		entity.setLongitude(resultSet.getDouble("longitude"));
		entity.setLatitude(resultSet.getDouble("latitude"));

		return entity;
	}

	@Override
	public List<IStation> find(StationFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
        appendSort(filter, sqlTile);
        appendPaging(filter, sqlTile);
        return executeFindQuery(sqlTile.toString());
	}
	@Override
    public long getCount(final StationFilter filter) {
        return executeCountQuery("");
    }

	@Override
	public IStation getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
