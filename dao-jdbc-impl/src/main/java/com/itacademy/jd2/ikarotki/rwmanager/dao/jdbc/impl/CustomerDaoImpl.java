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

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.ICustomerDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.ICustomer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.filter.CustomerFilter;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.Customer;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

@Repository
public class CustomerDaoImpl extends AbstractDaoImpl<ICustomer, Integer> implements ICustomerDao {

	@Override
	public ICustomer createEntity() {

		return new Customer();
	}

	@Override
	public void update(ICustomer entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set user_account_id=?, updated=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);

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
	public void insert(ICustomer entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (user_account_id, created, updated) values(?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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

		return "customer";
	}

	@Override
	protected ICustomer parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final ICustomer entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer userAccountId = (Integer) resultSet.getObject("user_account_id");
		if (userAccountId != null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setId(userAccountId);
			if (columns.contains("e_mail")) {
				userAccount.setEMail(resultSet.getString("e_mail"));
			}
			if (columns.contains("password")) {
				userAccount.setPassword(resultSet.getString("passwordl"));
			}
			if (columns.contains("role")) {
				userAccount.setRole(Role.values()[resultSet.getInt("role")]);
			}
			if (columns.contains("first_name")) {
				userAccount.setFirstName(resultSet.getString("first_name"));
			}
			if (columns.contains("last_name")) {
				userAccount.setLastName(resultSet.getString("last_name"));
			}
			entity.setUserAccount(userAccount);
		}
		return entity;
	}

	@Override
	public List<ICustomer> find(CustomerFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		// appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}
	
	@Override
    public long getCount(final CustomerFilter filter) {
        return executeCountQuery("");
    }

	@Override
	public ICustomer getFullInfo(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}
}
