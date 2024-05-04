package com.example.dao.Sensors

import com.example.models.Request_Sensor
import com.example.models.Sensor

interface SensorDaoFacade {
    suspend fun createSensor(sensor: Request_Sensor):Sensor?
    suspend fun findById(sensorId: Int):Sensor?
    suspend fun allSensors():List<Sensor>?
    suspend fun deleteSensor(sensorId: Int):Boolean
    suspend fun editSensor(sensor: Sensor):Boolean

}