package net.nemesis.contacts.model.persistence

import androidx.room.*
import net.nemesis.contacts.model.Contact

@Dao
interface ContactDAO {

    @Query("SELECT * FROM contact")
    suspend fun getAll(): List<Contact>

    @Query("SELECT * FROM contact WHERE idContact=(:id)")
    fun getSingle(id: Int): Contact?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg contact: Contact)

    @Insert
    fun insertSingle(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("DELETE FROM contact")
    fun deleteAll()
}