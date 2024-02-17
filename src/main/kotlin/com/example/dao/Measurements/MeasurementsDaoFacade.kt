package com.example.dao.Measurements

import com.example.models.Measurement
import com.example.models.Measurement_type
import com.example.models.Measurement_types
import org.jetbrains.exposed.sql.ResultRow

interface MeasurementsDaoFacade {
    suspend fun findByStation(station:Int): List<Measurement>?
    suspend fun createMeasurement(measurement: Measurement): Measurement?
    suspend fun deleteMeasurement(station: Int): Boolean
}