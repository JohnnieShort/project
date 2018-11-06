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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITrainDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.TrainFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Locomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Train;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class TrainDaoImpl extends AbstractDaoImpl<ITrain, Integer> implements ITrainDao {

	@Override
	public ITrain createEntity() {

		return new Train();
	}

	@Override
	public void update(ITrain entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set updated=?, locomotive_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {

				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(2, entity.getLocomotive().getId());
				pStmt.setInt(3, entity.getId());
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
	public void insert(ITrain entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (created, updated, locomotive_id) values(?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {

				pStmt.setObject(1, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getLocomotive().getId());

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

		return "train";
	}

	@Override
	protected ITrain parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ITrain entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer locomotiveId = (Integer) resultSet.getObject("locomotive_id");
		if (locomotiveId != null) {
			final Locomotive locomotive = new Locomotive();
			locomotive.setId(locomotiveId);
			if (columns.contains("locomotive_name")) {
				locomotive.setName(resultSet.getString("locomotive_name"));
			}
			entity.setLocomotive(locomotive);
			;
		}
		return entity;
	}

	@Override
	public List<ITrain> find(TrainFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		// appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}
	
	@Override
    public long getCount(final TrainFilter filter) {
        return executeCountQuery("");
    }
}
