package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IWagonDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITrain;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IWagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.WagonType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.WagonFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Train;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Wagon;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class WagonDaoImpl extends AbstractDaoImpl<IWagon, Integer> implements IWagonDao {

	@Override
	public IWagon createEntity() {

		return new Wagon();
	}

	@Override
	public void update(IWagon entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set wagon_type=?, updated=?, train_id=?, freight_price=?, capacity=? where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getWagonType().ordinal());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getTrain().getId());
				pStmt.setDouble(4, entity.getFreightPrice());
				pStmt.setDouble(5, entity.getCapacity());

				pStmt.setInt(6, entity.getId());
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
	public void insert(IWagon entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (wagon_type, created, updated, train_id, freight_price, capacity) values(?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getWagonType().ordinal());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getTrain().getId());
				pStmt.setDouble(5, entity.getFreightPrice());
				pStmt.setDouble(6, entity.getCapacity());

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

		return "wagon";
	}

	@Override
	protected IWagon parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IWagon entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setWagonType(WagonType.values()[resultSet.getInt("wagon_type")]);
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		entity.setFreightPrice(resultSet.getDouble("freight_price"));
		entity.setCapacity(resultSet.getDouble("capacity"));

		final Integer trainId = (Integer) resultSet.getObject("train_id");
		if (trainId != null) {
			final Train train = new Train();
			train.setId(trainId);
			if (columns.contains("train_name")) {
				// train.setName(resultSet.getString("train_name"));
			}
			entity.setTrain(train);
		}
		return entity;
	}

	@Override
	public List<IWagon> find(WagonFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		// appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}
	
	@Override
    public long getCount(final WagonFilter filter) {
        return executeCountQuery("");
    }

	@Override
	public IWagon getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Map<Integer, Integer> getPlacesByTrain(List<ITrain> trainsIds) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
