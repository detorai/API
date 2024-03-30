package com.example.dao.Measurements

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Measurement
import com.example.models.Measurements
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MeasurementsDaoImpl:MeasurementsDaoFacade {
    private fun resultRowToMeasurements(row: ResultRow) = Measurement(
        measurement_value =  row[Measurements.measurement_value],
        measurement_type = row[Measurements.measurement_type],
        measurement_ts= row[Measurements.measurement_time].toString(),
        sensor_inventory_number = row[Measurements.sensor_inventory_number]
    )
    override suspend fun findByInvNumb(sensor_inventory_number: Int): List<Measurement>? =dbQuery {
        Measurements.selectAll().where { Measurements.sensor_inventory_number eq sensor_inventory_number }.map { resultRowToMeasurements(it) }
    }
    override suspend fun createMeasurement(measurement: Measurement): Measurement? = dbQuery {
        val insertMeasurement = Measurements.insert {
            it[Measurements.measurement_value]=measurement.measurement_value
            it[Measurements.measurement_type]=measurement.measurement_type
            it[Measurements.measurement_time]= LocalDateTime.parse(measurement.measurement_ts, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()
            it[Measurements.sensor_inventory_number]=measurement.sensor_inventory_number
        }
        insertMeasurement.resultedValues?.singleOrNull()?.let (::resultRowToMeasurements)
    }

    override suspend fun deleteMeasurement(station: Int): Boolean = dbQuery {
        Measurements.deleteWhere { Measurements.sensor_inventory_number eq station } > 0
    }


}
val measurementsDao=MeasurementsDaoImpl()