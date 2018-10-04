package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IPassengerRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IPassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Frequency;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.PassengerRouteType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Locomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.PassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Station;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Train;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;
@Repository
public class PassengerRouteDaoImpl extends AbstractDaoImpl<IPassengerRoute, Integer> implements IPassengerRouteDao {

	@Override
	public IPassengerRoute createEntity() {

		return new PassengerRoute();
	}

	@Override
	public void update(IPassengerRoute entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c
						.prepareStatement(String.format(
								"update %s set from=?, to=?, updated=?, departure=?, arrival=?, passenger_route_type=?,"
										+ "train_id=?, is_actual=?, frequency=?, places=?  where id=?",
								getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getFrom().getId());
				pStmt.setInt(2, entity.getTo().getId());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getDeparture(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getArrival(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getPassengerRoutetype().ordinal());
				pStmt.setInt(7, entity.getTrain().getId());
				pStmt.setBoolean(8, entity.getIsActual());
				pStmt.setInt(9, entity.getFrequency().ordinal());
				pStmt.setInt(10, entity.getPlaces());

				pStmt.setInt(11, entity.getId());

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
	public void insert(IPassengerRoute entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (from, to, created, updated, departure, arrival, passenger_route_type,"
								+ "train_id, is_actual, frequency, places) values(?,?,?,?,?,?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getFrom().getId());
				pStmt.setInt(2, entity.getTo().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getDeparture(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getArrival(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getPassengerRoutetype().ordinal());
				pStmt.setInt(8, entity.getTrain().getId());
				pStmt.setBoolean(9, entity.getIsActual());
				pStmt.setInt(10, entity.getFrequency().ordinal());
				pStmt.setInt(11, entity.getPlaces());

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

		return "passenger_route";
	}

	@Override
	protected IPassengerRoute parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IPassengerRoute entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		entity.setDeparture(resultSet.getTimestamp("departure"));
		entity.setArrival(resultSet.getTimestamp("arrival"));
		entity.setPassengerRoutetype(PassengerRouteType.values()[resultSet.getInt("passenger_route_type")]);
		entity.setIsActual(resultSet.getBoolean("is_actual"));
		entity.setFrequency(Frequency.values()[resultSet.getInt("frequency")]);
		entity.setPlaces(resultSet.getInt("places"));

		final Integer trainId = (Integer) resultSet.getObject("train_id");
		if (trainId != null) {
			final Train train = new Train();
			train.setId(trainId);
			if (columns.contains("locomotive_id")) {
				Locomotive locomotive = new Locomotive();
				locomotive.setId(resultSet.getInt("locomotive_id"));
				train.setLocomotive(locomotive);
			}
			entity.setTrain(train);
		}
		final Integer fromId = (Integer) resultSet.getObject("from_id");
		if (fromId != null) {
			Station from = new Station();
			from.setId(fromId);
			if (columns.contains("from_name")) {
				from.setName(resultSet.getString("from_name"));
			}
			if (columns.contains("from_longitude")) {
				from.setLongitude(resultSet.getDouble("from_longitude"));
			}
			if (columns.contains("from_latitude")) {
				from.setLatitude(resultSet.getDouble("from_latitude"));
			}
			entity.setFrom(from);
		}
		final Integer toId = (Integer) resultSet.getObject("to_id");
		if (toId != null) {
			Station to = new Station();
			to.setId(toId);
			if (columns.contains("to_name")) {
				to.setName(resultSet.getString("to_name"));
			}
			if (columns.contains("to_longitude")) {
				to.setLongitude(resultSet.getDouble("to_longitude"));
			}
			if (columns.contains("to_latitude")) {
				to.setLatitude(resultSet.getDouble("to_latitude"));
			}
			entity.setFrom(to);
		}
		return entity;
	}
}
