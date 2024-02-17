package com.example.dao.Measurements

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Measurement
import com.example.models.Measurements
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MeasurementsDaoImpl:MeasurementsDaoFacade {
    private fun resultRowToMeasurements(row: ResultRow) = Measurement(station = row[Measurements.station], measurement_type = row[Measurements.measurement_type], measurement_value =  row[Measurements.measurement_value],  sensor_model = row[Measurements.sensor_model], measurement_time = row[Measurements.measurement_time].toString())
    override suspend fun findByStation(station: Int): List<Measurement> =dbQuery {
        Measurements.select{Measurements.station eq station}.map(::resultRowToMeasurements)
    }
    override suspend fun createMeasurement(measurement: Measurement): Measurement? = dbQuery {
        val insertMeasurement = Measurements.insert {
            it[Measurements.station]=measurement.station
            it[Measurements.measurement_type]=measurement.measurement_type
            it[Measurements.measurement_value]=measurement.measurement_value
            it[Measurements.sensor_model]=measurement.sensor_model
            it[Measurements.measurement_time]= LocalDateTime.parse(measurement.measurement_time, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant()
        }
        insertMeasurement.resultedValues?.singleOrNull()?.let (::resultRowToMeasurements)
    }

    override suspend fun deleteMeasurement(station: Int): Boolean = dbQuery {
        Measurements.deleteWhere { Measurements.station eq station } > 0
    }


}
val measurementsDao=MeasurementsDaoImpl()