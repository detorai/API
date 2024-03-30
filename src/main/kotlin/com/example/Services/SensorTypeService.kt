package com.example.Services

import com.example.dao.SensorTypes.TypeDaoFacade
import com.example.dao.SensorTypes.sensorTypeDao
import com.example.models.SensorMeasurement

class SensorTypeService(private val SensorTypeRepository:TypeDaoFacade) {
        suspend fun findByModel(sensorId: Int): List<SensorMeasurement>? {
        return SensorTypeRepository.findById(sensorId)
    }
    suspend fun createSensorMeasurement(sensorMeasurement: SensorMeasurement):SensorMeasurement?{
        return SensorTypeRepository.createSensorMeasurement(sensorMeasurement)
    }
    suspend fun deleteSensorType(sensorId:Int):Boolean {
        return SensorTypeRepository.deleteSensorMeasurement(sensorId)
    }
}
val sensorTypeService=SensorTypeService(sensorTypeDao)
