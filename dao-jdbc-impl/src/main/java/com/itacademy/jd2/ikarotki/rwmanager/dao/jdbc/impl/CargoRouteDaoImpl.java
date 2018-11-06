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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICargoRouteDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CargoRouteFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.CargoRoute;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Locomotive;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Train;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class CargoRouteDaoImpl extends AbstractDaoImpl<ICargoRoute, Integer> implements ICargoRouteDao {

	@Override
	public ICargoRoute createEntity() {

		return new CargoRoute();
	}

	@Override
	public void update(ICargoRoute entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set  updated=?, train_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {

				pStmt.setObject(1, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(2, entity.getTrain().getId());
				

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
	public void insert(ICargoRoute entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (created, updated, train_id) values(?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {

				pStmt.setObject(1, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getTrain().getId());
				

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

		return "cargo_route";
	}

	@Override
	protected ICargoRoute parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ICargoRoute entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		/*
		 * final Integer cargoOrderId = (Integer) resultSet.getObject("cargo_order_id");
		 * if (cargoOrderId != null) { final CargoOrder cargoOrder = new CargoOrder();
		 * cargoOrder.setId(cargoOrderId);
		 * 
		 * if (columns.contains("customer_id")) { Customer customer = new Customer();
		 * customer.setId(resultSet.getInt("customer_id"));
		 * cargoOrder.setCustomer(customer); } if (columns.contains("cargo_type")) {
		 * 
		 * cargoOrder.setCargoType(CargoType.values()[resultSet.getInt("cargo_type")]);
		 * } if (columns.contains("station_from_id")) {
		 * 
		 * final Integer fromId = (Integer) resultSet.getObject("station_from_id"); if
		 * (fromId != null) { Station from = new Station(); from.setId(fromId);
		 * cargoOrder.setStationFrom(from); }
		 * 
		 * } if (columns.contains("station_to_id")) {
		 * 
		 * final Integer toId = (Integer) resultSet.getObject("station_to_id"); if (toId
		 * != null) { Station to = new Station(); to.setId(toId);
		 * cargoOrder.setStationFrom(to); }
		 * 
		 * } if (columns.contains("date")) {
		 * 
		 * final Date date = resultSet.getTimestamp("date"); if (date != null) {
		 * 
		 * cargoOrder.setDate(date); }
		 * 
		 * } if (columns.contains("weight")) {
		 * 
		 * final Double weight = resultSet.getDouble("weight"); if (weight != null) {
		 * 
		 * cargoOrder.setWeight(weight); }
		 * 
		 * } entity.setCargoOrder(cargoOrder); }
		 */
		final Integer trainId = (Integer) resultSet.getObject("train_id");
		Train train = new Train();
		train.setId(trainId);
		if (columns.contains("locomotive_id")) {

			final Integer locomotiveId = resultSet.getInt("locomotive_id");
			if (locomotiveId != null) {

				Locomotive loc = new Locomotive();
				loc.setId(locomotiveId);
				train.setLocomotive(loc);
			}

		}
		entity.setTrain(train);
		return entity;
	}

	@Override
	public List<ICargoRoute> find(CargoRouteFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		// appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}
	@Override
    public long getCount(final CargoRouteFilter filter) {
        return executeCountQuery("");
    }
}
