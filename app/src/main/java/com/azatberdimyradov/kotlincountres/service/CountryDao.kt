package com.azatberdimyradov.kotlincountres.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.azatberdimyradov.kotlincountres.model.Country

@Dao
interface CountryDao {

    // Data access object

    @Insert
    suspend fun insetAll(vararg countries: Country): List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCounties(): List<Country>

    @Query("SELECT * FROM country WHERE uuid  = :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()


}