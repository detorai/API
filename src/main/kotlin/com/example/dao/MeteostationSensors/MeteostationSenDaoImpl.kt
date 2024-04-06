package com.example.dao.MeteostationSensors

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Meteostations_Sensors
import com.example.models.Meteostations_sensors
import com.example.models.Request_Meteostations_Sensors
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MeteostationSenDaoImpl:MeteostationSenDaoFacade {

    private fun resultToRowInvNumb(row: ResultRow) = Meteostations_Sensors(
        station_id = row[Meteostations_sensors.station_id],
        sensor_id = row[Meteostations_sensors.sensor_id],
        added_ts = row[Meteostations_sensors.added_ts].toString(),
        removed_ts = row[Meteostations_sensors.removed_ts].toString(),
        sensor_inventory_number = row[Meteostations_sensors.sensor_inventory_number]
    )

    override suspend fun findByInvNumb(sensor_inv_numb: Int): List<Meteostations_Sensors> = dbQuery{
        Meteostations_sensors.selectAll().where { Meteostations_sensors.sensor_inventory_number eq sensor_inv_numb }.map {resultToRowInvNumb(it)}
    }

    override suspend fun createInvNumb(meteostationsSensors: Request_Meteostations_Sensors): Meteostations_Sensors? = dbQuery {
        val insertInvNumb = Meteostations_sensors.insert {
            it[Meteostations_sensors.station_id]=meteostationsSensors.station_id
            it[Meteostations_sensors.sensor_id]=meteostationsSensors.sensor_id
            /*it[Meteostations_sensors.added_ts]= LocalDateTime.parse(meteostationsSensors.added_ts, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()
            it[Meteostations_sensors.removed_ts]= LocalDateTime.parse(meteostationsSensors.removed_ts, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()
    */    }
        insertInvNumb.resultedValues?.singleOrNull()?.let (::resultToRowInvNumb)

    }

    override suspend fun deleteInvNumb(sensor_inv_numb: Int): Boolean = dbQuery{
        Meteostations_sensors.deleteWhere { Meteostations_sensors.sensor_inventory_number eq sensor_inv_numb } > 0
    }
}

val meteostationSensDao = MeteostationSenDaoImpl()