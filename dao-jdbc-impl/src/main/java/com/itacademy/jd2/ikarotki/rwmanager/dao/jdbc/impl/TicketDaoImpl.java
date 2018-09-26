package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Set;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ITicketDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ITicket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Passenger;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Ticket;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

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
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set passenger_id=?, updated=?, passenger_route_id=?, price=? where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getPassenger().getId());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getPassengerRoute().getId());
				pStmt.setDouble(4, entity.getPrice());
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
	public void insert(ITicket entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (pasenger_id, created, updated, passenger_route_id, price) values(?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getPassenger().getId());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getPassengerRoute().getId());
				pStmt.setDouble(5, entity.getPrice());

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
	protected ITicket parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ITicket entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		// entity.setPassengerId((Integer) resultSet.getInt("passenger_id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		// entity.setPassengerRouteId((Integer) resultSet.getInt("passenger_route_id"));
		entity.setPrice((Double) resultSet.getDouble("price"));

		Integer passengerId = resultSet.getInt("passenger_id");
		if (passengerId != null) {
			Passenger passenger = new Passenger();
			passenger.setId(passengerId);
			if (columns.contains("passenger")) {

			}
			// final Integer brandId = (Integer) resultSet.getObject("brand_id");
			// if (brandId != null) {
			// final Brand brand = new Brand();
			// brand.setId(brandId);
			// if (columns.contains("brand_name")) {
			// brand.setName(resultSet.getString("brand_name"));
			// }
			// entity.setBrand(brand);
			// }

		}
		return entity;
	}

}
