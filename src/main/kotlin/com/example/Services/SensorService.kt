package com.example.Services

import ch.qos.logback.core.model.Model
import com.example.dao.Sensors.SensorDaoFacade
import com.example.dao.Sensors.sensorDao
import com.example.models.Sensor

class SensorService(private val SensorRepository:SensorDaoFacade) {
    suspend fun createSensor(sensor: Sensor):Sensor? {
        return SensorRepository.createSensor(sensor)
    }
    suspend fun findByModel(model:String):Sensor? {
        return SensorRepository.findByModel(model)
    }
    suspend fun deleteSensor(sensorId: Int):Boolean {
        return SensorRepository.deleteSensor(sensorId)
    }

}
val sensorService=SensorService(sensorDao)