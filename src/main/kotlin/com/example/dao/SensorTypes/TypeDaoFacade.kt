package com.example.dao.SensorTypes

import com.example.models.SensorMeasurement

interface TypeDaoFacade {

    suspend fun findById(sensorId:Int): List<SensorMeasurement>?
    suspend fun createSensorMeasurement(sensorMeasurement: SensorMeasurement):SensorMeasurement?
    suspend fun deleteSensorMeasurement(sensorId: Int): Boolean
}
