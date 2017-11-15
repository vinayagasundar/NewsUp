package com.devknightzzz.newsup.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.devknightzzz.newsup.database.entity.Source

/**
 * @author vinayagasundar
 */
@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sourceList: List<Source>)
}