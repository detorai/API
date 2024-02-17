package com.example.dao.SensorTypes

import com.example.models.Sensor
import com.example.models.SensorType
import javax.swing.text.StyledEditorKit.BoldAction

interface TypeDaoFacade {

    suspend fun findByModel(model:Int): List<SensorType>?
    suspend fun createSensorType(sensorType: SensorType):SensorType?
    suspend fun deleteSensorType(model: Int): Boolean
}
