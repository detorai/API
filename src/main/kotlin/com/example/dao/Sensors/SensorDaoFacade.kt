package com.example.dao.Sensors

import com.example.models.Sensor

interface SensorDaoFacade {
    suspend fun createSensor(sensor: Sensor):Sensor?
    suspend fun findByModel(model:String):Sensor?
    suspend fun deleteSensor(sensorId: Int):Boolean


}