package com.example.dao.Measurements

import com.example.models.Measurement

interface MeasurementsDaoFacade {
    suspend fun findByInvNumb(sensor_inventory_number:Int): List<Measurement>?
    suspend fun createMeasurement(measurement: Measurement): Measurement?
    suspend fun deleteMeasurement(sensor_inventory_number: Int): Boolean
}