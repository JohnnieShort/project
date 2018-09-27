package com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Set;

import com.itacademy.jd2.ikarotki.rwmanager.dao.api.IUserAccountDao;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.IUserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.api.entity.base.enums.Role;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.ikarotki.rwmanager.dao.jdbc.impl.util.SQLExecutionException;

public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	@Override
	public IUserAccount createEntity() {

		return new UserAccount();
	}

	@Override
	public void update(IUserAccount entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"update %s set e_mail=?, updated=?, password=?, role=?, first_name=?, last_name=? where id=?",
						getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getEMail());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(3, entity.getPassword());
				pStmt.setInt(4, entity.getRole().ordinal());
				pStmt.setString(5, entity.getFirstName());
				pStmt.setString(6, entity.getLastName());

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
	public void insert(IUserAccount entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (e_mail, created, updated, password, role, first_name, last_name) values(?,?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getEMail());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setString(4, entity.getPassword());
				pStmt.setInt(5, entity.getRole().ordinal());
				pStmt.setString(6, entity.getFirstName());
				pStmt.setString(7, entity.getLastName());

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

		return "user_account";
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setEMail(resultSet.getString("e_mail"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		entity.setPassword(resultSet.getString("password"));
		entity.setRole(Role.values()[resultSet.getInt("role")]);
		entity.setFirstName(resultSet.getString("first_name"));
		entity.setLastName(resultSet.getString("last_name"));

		return entity;
	}
}
