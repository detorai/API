package com.example.Services

import com.example.dao.Measurements.MeasurementsDaoFacade
import com.example.dao.Measurements.measurementsDao
import com.example.models.Measurement
class MeasurementService(private val MeasurementRepository:MeasurementsDaoFacade) {
    suspend fun findByStation(station:Int): List<Measurement>?{
        return MeasurementRepository.findByStation(station)
    }
    suspend fun createMeasurement(measurement: Measurement): Measurement?{
        return MeasurementRepository.createMeasurement(measurement)
    }
    suspend fun deleteMeasurement(station: Int): Boolean{
        return MeasurementRepository.deleteMeasurement(station)
    }

}
val measurementService = measurementsDao