package com.example.Services

import com.example.dao.Measurements.MeasurementsDaoFacade
import com.example.dao.Measurements.measurementsDao
import com.example.models.Measurement
class MeasurementService(private val MeasurementRepository:MeasurementsDaoFacade) {
    suspend fun findByInvNumb(sensor_inv_numb:Int): List<Measurement>?{
        return MeasurementRepository.findByInvNumb(sensor_inv_numb)
    }
    suspend fun createMeasurement(measurement: Measurement): Measurement?{
        return MeasurementRepository.createMeasurement(measurement)
    }
    suspend fun deleteMeasurement(sensor_inv_numb: Int): Boolean{
        return MeasurementRepository.deleteMeasurement(sensor_inv_numb)
    }

}
val measurementService = measurementsDao