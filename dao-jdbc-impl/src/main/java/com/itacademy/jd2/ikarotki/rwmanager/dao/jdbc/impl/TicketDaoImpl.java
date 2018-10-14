package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Passenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.PassengerRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Station;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Ticket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.StatementAction;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<ITicket, Integer> implements ITicketDao {

	@Override
	public ITicket createEntity() {
		return new Ticket();
	}

	@Override
	protected String getTableName() {
		return "ticket";
	}

	@Override
	public void update(ITicket entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set passenger_id=?, updated=?, passenger_route_id=?, price=?, "
								+ "st_from=?, st_to=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getPassenger().getId());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getPassengerRoute().getId());
				pStmt.setDouble(4, entity.getPrice());
				pStmt.setInt(5, entity.getFrom().getId());
				pStmt.setInt(6, entity.getTo().getId());

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
	public void insert(ITicket entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("insert into %s (passenger_id, created, updated, passenger_route_id, price, "
								+ "st_from, st_to) values(?,?,?,?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getPassenger().getId());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getPassengerRoute().getId());
				pStmt.setDouble(5, entity.getPrice());
				pStmt.setInt(6, entity.getFrom().getId());
				pStmt.setInt(7, entity.getTo().getId());
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
	public ITicket get(final Integer id) {
		StatementAction<ITicket> action = (statement) -> {
			statement.executeQuery("select * from " + getTableName() + " where id=" + id);
			/*
			 * "SELECT t.id as ticket_id, t.price as ticket_price, t.created as
			 * ticket_created, t.updated as ticket_updated,
			 * 
			 * st.id as ticket_station_from_id, st.name as ticket_station_from_name,
			 * st.longitude as ticket_station_from_longitude, st.latitude as
			 * ticket_station_from_latitude, st.created as ticket_station_from_created,
			 * st.updated as ticket_station_from_updated,
			 * 
			 * st1.id as ticket_station_to_id, st1.name as ticket_station_to_name,
			 * st1.longitude as ticket_station_to_longitude, st1.latitude as
			 * ticket_station_to_latitude, st1.created as ticket_station_to_created,
			 * st1.updated as ticket_station_to_updated,
			 * 
			 * p.id as passenger_id, p.created as passenger_created, p.updated as
			 * passenger_updated,
			 * 
			 * ua.id as user_account_id, ua.e_mail as user_account_e_mail, ua.password as
			 * user_account_password, ua.role as user_account_role, ua.first_name as
			 * user_account_first_name, ua.last_name as user_account_last_name, ua.created
			 * as user_account_created, ua.updated as user_account_updated,
			 * 
			 * pr.id as passenger_route_id, pr.departure as as passenger_route_depatture,
			 * pr.arrival as passenger_route_arrival, pr.passenger_route_type as
			 * passenger_route_route_type, pr.is_actual as passenger_route_is_actual,
			 * pr.created as passenger_route_created, pr.updated as passenger_route_updated,
			 * pr.frequency, pr.places, st2.id as pasenger_route_from_id, st2.name as
			 * pasenger_route_from_name, st2.longitude as pasenger_route_from_longitude,
			 * st2.latitude as pasenger_route_from_latitude, st2.created as
			 * pasenger_route_from_created, st2.updated as pasenger_route_from_updated,
			 * 
			 * st3.id as pasenger_route_to_id, st3.name as pasenger_route_to_name,
			 * st3.longitude as pasenger_route_to_longitude, st3.latitude as
			 * pasenger_route_to_latitude, st3.created as pasenger_route_to_created,
			 * st3.updated as pasenger_route_to_updated,
			 * 
			 * tr.id as train_id, tr.created as train.created, tr.updated as train_updated,
			 * 
			 * loc.id as locomotive_id, loc.name as locomotive_name, loc.created as
			 * locomotive_created, loc.updated as locomotive_updated
			 * 
			 * 
			 * JOIN station st ON t.id =" + id " AND st.id = t.from JOIN station st2 ON t.id
			 * =" + id " AND st2.id = t.to JOIN passenger p ON t.id =" + id " AND p.id =
			 * t.passenger_id JOIN user_account ua ON t.id =" + id " AND t.passenger_id =
			 * p.id AND p.user_account_id = ua.id JOIN passenger_route pr ON t.id =" + id "
			 * AND t.passenger_route_id = pr.id JOIN station st2 ON t.id =" + id " AND
			 * t.passenger_route = pr.id AND pr.from = st2.id JOIN station st3 ON t.id
			 * =" + id " AND t.passenger_route = pr.id AND pr.to = st3.id JOIN train tr ON
			 * t.id =" + id " AND t.passenger_route = pr.id AND pr.train_id = tr.id JOIN
			 * locomotive loc ON t.id =" + id " AND t.passenger_route = pr.id AND
			 * pr.train_id = tr.id AND tr.locomotive_id = loc.id
			 */
			final ResultSet resultSet = statement.getResultSet();
			final Set<String> columns = resolveColumnNames(resultSet);

			final boolean hasNext = resultSet.next();
			ITicket result = null;
			if (hasNext) {
				result = parseRow(resultSet, columns);
			}

			resultSet.close();
			return result;
		};
		ITicket entityById = executeStatement(action);
		return entityById;
	}

	private Set<String> resolveColumnNames(final ResultSet resultSet) throws SQLException {
		final ResultSetMetaData rsMetaData = resultSet.getMetaData();
		final int numberOfColumns = rsMetaData.getColumnCount();
		final Set<String> columns = new HashSet<>();
		for (int i = 1; i <= numberOfColumns; i++) {
			columns.add(rsMetaData.getColumnName(i));
		}
		return columns;
	}

	@Override
	protected ITicket parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ITicket entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		entity.setPrice(resultSet.getDouble("price"));

		Integer passengerId = resultSet.getInt("passenger_id");
		if (passengerId != null) {
			Passenger passenger = new Passenger();
			passenger.setId(passengerId);
			entity.setPassenger(passenger);

		}
		Integer passengerRouteId = resultSet.getInt("passenger_route_id");
		if (passengerRouteId != null) {
			PassengerRoute passengerRoute = new PassengerRoute();
			passengerRoute.setId(passengerRouteId);
			entity.setPassengerRoute(passengerRoute);

		}
		final Integer fromId = (Integer) resultSet.getObject("st_from");
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
		final Integer toId = (Integer) resultSet.getObject("st_to");
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
