package com.example.Services

import com.example.dao.Sensors.SensorDaoFacade
import com.example.dao.Sensors.sensorDao
import com.example.models.Sensor

class SensorService(private val SensorRepository:SensorDaoFacade) {
    suspend fun createSensor(sensor: Sensor):Sensor? {
        return SensorRepository.createSensor(sensor)
    }
    suspend fun findById(sensorId: Int):Sensor? {
        return SensorRepository.findById(sensorId)
    }
    suspend fun deleteSensor(sensorId: Int):Boolean {
        return SensorRepository.deleteSensor(sensorId)
    }

}
val sensorService=SensorService(sensorDao)