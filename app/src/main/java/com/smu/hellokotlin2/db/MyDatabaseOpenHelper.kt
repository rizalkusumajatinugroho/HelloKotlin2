package com.smu.hellokotlin2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.smu.hellokotlin2.model.DataDetailTeam
import com.smu.hellokotlin2.model.DataFavorites
import org.jetbrains.anko.db.*

/**
 * Created by sapuser on 10/25/2018.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(DataFavorites.TABLE_FAVORITE, true,
                DataFavorites.idEvent to TEXT + PRIMARY_KEY,
                DataFavorites.strHomeTeam to TEXT,
                DataFavorites.strAwayTeam to TEXT,
                DataFavorites.intHomeScore to TEXT,
                DataFavorites.intAwayScore to TEXT,
                DataFavorites.dateEvent to TEXT)

        db.createTable(DataDetailTeam.TABLE_FAVORITE_TEAM, true,
                DataDetailTeam.idTeam to TEXT + PRIMARY_KEY,
                DataDetailTeam.strTeam to TEXT,
                DataDetailTeam.strBadge to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(DataFavorites.TABLE_FAVORITE, true)
        db.dropTable(DataDetailTeam.TABLE_FAVORITE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)