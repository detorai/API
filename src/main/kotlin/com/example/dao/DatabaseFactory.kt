package com.example.dao

import com.example.models.Sensor
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {
    fun init(){
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://5.35.94.60:5432/dsp?user=postgres&password=123"
        val database = Database.connect(jdbcURL, driverClassName)

}
    suspend fun <T> dbQuery(block:suspend ()->T) =  newSuspendedTransaction(Dispatchers.IO){block()}
}