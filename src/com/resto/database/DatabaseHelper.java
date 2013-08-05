package com.resto.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String CLASS_NAME = DatabaseHelper.class.getName();
	private static final String DATABASE_NAME = "resto.db";
	private static final int DATABASE_VERSION = 1;

	private RuntimeExceptionDao<Restaurant, Integer> restaurantRuntimeDao = null;
    private RuntimeExceptionDao<Menu, Integer> menuRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(CLASS_NAME, "onCreate");
			TableUtils.createTable(connectionSource, Restaurant.class);
            TableUtils.createTable(connectionSource, Menu.class);
		} catch (SQLException e) {
			Log.e(CLASS_NAME, "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(CLASS_NAME, "onUpgrade");
			TableUtils.dropTable(connectionSource, Restaurant.class, true);
            TableUtils.dropTable(connectionSource, Menu.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(CLASS_NAME, "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our Restaurant class. It will create it or just give the cached
	 * value.
	 */
//	public Dao<Restaurant, Integer> getDao() throws SQLException {
//		if (simpleDao == null) {
//			simpleDao = getDao(Restaurant.class);
//		}
//		return simpleDao;
//	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Restaurant class. It will
	 * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     * @return restaurantRuntimeDao runtime DAO object for restaurant class
	 */
	public RuntimeExceptionDao<Restaurant, Integer> getRestaurantRuntimeDao() {
		if (restaurantRuntimeDao == null) {
			restaurantRuntimeDao = getRuntimeExceptionDao(Restaurant.class);
		}
		return restaurantRuntimeDao;
	}

    /**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Menu class. It will
	 * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     * @return menuRuntimeDao runtime DAO object for menu class
	 */
	public RuntimeExceptionDao<Menu, Integer> getMenuRuntimeDao() {
		if (menuRuntimeDao == null) {
			menuRuntimeDao = getRuntimeExceptionDao(Menu.class);
		}
		return menuRuntimeDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		restaurantRuntimeDao = null;
        menuRuntimeDao = null;
	}
}
