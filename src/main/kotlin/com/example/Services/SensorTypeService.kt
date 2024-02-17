package com.example.Services

import com.example.dao.SensorTypes.TypeDaoFacade
import com.example.dao.SensorTypes.sensorTypeDao
import com.example.models.SensorType

class SensorTypeService(private val SensorTypeRepository:TypeDaoFacade) {
        suspend fun findByModel(model:Int): List<SensorType>? {
        return SensorTypeRepository.findByModel(model)
    }
    suspend fun createSensorType(sensorType: SensorType):SensorType?{
        return SensorTypeRepository.createSensorType(sensorType)
    }
    suspend fun deleteSensorType(model: Int):Boolean {
        return SensorTypeRepository.deleteSensorType(model)
    }
}
val sensorTypeService=SensorTypeService(sensorTypeDao)
