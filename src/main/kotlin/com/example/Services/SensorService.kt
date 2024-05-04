package com.example.Services

import com.example.dao.Sensors.SensorDaoFacade
import com.example.dao.Sensors.sensorDao
import com.example.models.Request_Sensor
import com.example.models.Sensor

class SensorService(private val SensorRepository:SensorDaoFacade) {
    suspend fun createSensor(sensor: Request_Sensor):Sensor? {
        return SensorRepository.createSensor(sensor)
    }
    suspend fun findById(sensorId: Int):Sensor? {
        return SensorRepository.findById(sensorId)
    }
    suspend fun deleteSensor(sensorId: Int):Boolean {
        return SensorRepository.deleteSensor(sensorId)
    }
    suspend fun allSensors():List<Sensor>? {
        return SensorRepository.allSensors()
    }
    suspend fun editSensor(sensor: Sensor):Boolean {
        return SensorRepository.editSensor(sensor)
    }
}
val sensorService=SensorService(sensorDao)