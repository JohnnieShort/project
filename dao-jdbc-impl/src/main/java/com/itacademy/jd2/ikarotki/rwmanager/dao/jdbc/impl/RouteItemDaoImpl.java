package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Set;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IRouteItemDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IRouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.PassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.RouteItem;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Station;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {

	@Override
	public IRouteItem createEntity() {
		return new RouteItem();
	}

	@Override
	public void update(IRouteItem entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set passenger_route_id=?, updated=?, station_id_from=?, station_id_to=?, time=?, ordinal_num=? "
								+ "where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getPassengerRoute().getId());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getStationFrom().getId());
				pStmt.setInt(4, entity.getStationTo().getId());
				pStmt.setDouble(5, entity.getTime());
				pStmt.setInt(6, entity.getOrdinalNum());

				pStmt.setInt(7, entity.getId());
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
	public void insert(IRouteItem entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (passenger_route_id, created, updated, station_id_from, station_id_to, time, ordinal_num) "
								+ "values(?,?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getPassengerRoute().getId());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getStationFrom().getId());
				pStmt.setInt(5, entity.getStationTo().getId());
				pStmt.setDouble(6, entity.getTime());
				pStmt.setInt(7, entity.getOrdinalNum());

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

		return "route_item";
	}

	@Override
	protected IRouteItem parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IRouteItem entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setTime(resultSet.getDouble("time"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		entity.setOrdinalNum(resultSet.getInt("ordinal_num"));

		final Integer passengerRouteId = (Integer) resultSet.getObject("passenger_route_id");
		if (passengerRouteId != null) {
			final PassengerRoute passengerRoute = new PassengerRoute();
			passengerRoute.setId(passengerRouteId);
			if (columns.contains("passenger_route_from")) {
				passengerRoute.setFrom(resultSet.getString("passenger_route_from"));
			}
			if (columns.contains("passenger_route_to")) {
				passengerRoute.setFrom(resultSet.getString("passenger_route_to"));
			}
			entity.setPassengerRoute(passengerRoute);
		}
		final Integer stationIdFrom = (Integer) resultSet.getObject("station_id_from");
		if (stationIdFrom != null) {
			final Station stationFrom = new Station();
			stationFrom.setId(stationIdFrom);
			if (columns.contains("station_name_from")) {
				stationFrom.setName(resultSet.getString("station_name_from"));
			}

			entity.setStationFrom(stationFrom);
		}
		final Integer stationIdTo = (Integer) resultSet.getObject("station_id_to");
		if (stationIdTo != null) {
			final Station stationTo = new Station();
			stationTo.setId(stationIdTo);
			if (columns.contains("station_name_to")) {
				stationTo.setName(resultSet.getString("station_name_to"));
			}

			entity.setStationFrom(stationTo);
		}
		return entity;
	}
}
