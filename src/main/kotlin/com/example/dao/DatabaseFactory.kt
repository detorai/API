package com.example.dao

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseFactory {
    fun init(){
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://193.176.78.35:5433/user109?user=user109&password=ihAY"
        val database = Database.connect(jdbcURL, driverClassName)

}
    suspend fun <T> dbQuery(block:suspend ()->T) =  newSuspendedTransaction(Dispatchers.IO){block()}
}