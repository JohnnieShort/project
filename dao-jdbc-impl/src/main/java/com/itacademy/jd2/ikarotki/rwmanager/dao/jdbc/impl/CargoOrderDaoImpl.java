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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoOrderDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.CargoType;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoOrderFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.CargoOrder;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.CargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Customer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Station;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class CargoOrderDaoImpl extends AbstractDaoImpl<ICargoOrder, Integer> implements ICargoOrderDao {

	@Override
	public ICargoOrder createEntity() {

		return new CargoOrder();
	}

	@Override
	public void update(ICargoOrder entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c
						.prepareStatement(String.format(
								"update %s set customer_id=?, updated=?, cargo_type=?, station_from=?,"
										+ " station_to=?, date=?, weight=?, cargo_route_id=?, price=? where id=?",
								getTableName()))) {
			c.setAutoCommit(false);
			try {

				pStmt.setInt(1, entity.getCustomer().getId());

				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getCargoType().ordinal());
				pStmt.setInt(4, entity.getStationFrom().getId());
				pStmt.setInt(5, entity.getStationTo().getId());
				pStmt.setObject(6, entity.getDate(), Types.TIMESTAMP);

				pStmt.setDouble(7, entity.getWeight());
				pStmt.setInt(8, entity.getCargoRoute().getId());
				pStmt.setDouble(9, entity.getPrice());
				pStmt.setInt(10, entity.getId());
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
	public void insert(ICargoOrder entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (customer_id, created, updated, cargo_type, station_from, station_to, date,"
								+ "weight, cargo_route_id, price) values(?,?,?,?,?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getCustomer().getId());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getCargoType().ordinal());
				pStmt.setInt(5, entity.getStationFrom().getId());
				pStmt.setInt(6, entity.getStationTo().getId());
				pStmt.setObject(7, entity.getDate(), Types.TIMESTAMP);
				pStmt.setDouble(8, entity.getWeight());
				pStmt.setInt(9, entity.getCargoRoute().getId());
				pStmt.setDouble(10, entity.getPrice());

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

		return "cargo_order";
	}

	@Override
	protected ICargoOrder parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ICargoOrder entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCargoType(CargoType.values()[resultSet.getInt("cargo_type")]);

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		entity.setDate(resultSet.getTimestamp("date"));
		entity.setWeight(resultSet.getDouble("weight"));
		entity.setPrice(resultSet.getDouble("price"));


		final Integer cargoRouteId = (Integer) resultSet.getObject("cargo_route_id");
		if (cargoRouteId != null) {
			final ICargoRoute cargoRoute = new CargoRoute();
			cargoRoute.setId(cargoRouteId);

			
			entity.setCargoRoute(cargoRoute);;
		}
		final Integer customerId = (Integer) resultSet.getObject("customer_id");
		if (customerId != null) {
			final Customer customer = new Customer();
			customer.setId(customerId);

			if (columns.contains("user_account_id")) {
				Integer userAccountId = (Integer) resultSet.getObject("user_account_id");
				if (userAccountId != null) {
					UserAccount userAI = new UserAccount();
					userAI.setId(userAccountId);
					customer.setUserAccount(userAI);
				}

			}
			entity.setCustomer(customer);
		}
		final Integer fromId = (Integer) resultSet.getObject("station_from");
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
			entity.setStationFrom(from);
		}
		final Integer toId = (Integer) resultSet.getObject("station_to");
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
			entity.setStationTo(to);
		}
		return entity;
	}
	@Override
	public List<ICargoOrder> find(CargoOrderFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
        appendSort(filter, sqlTile);
        // appendPaging(filter, sqlTile);
        return executeFindQuery(sqlTile.toString());
	}
	
	@Override
    public long getCount(final CargoOrderFilter filter) {
        return executeCountQuery("");
    }
}
