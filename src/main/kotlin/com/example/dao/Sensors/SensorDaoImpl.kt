package com.example.dao.Sensors

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Request_Sensor
import com.example.models.Sensor
import com.example.models.Sensors
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class SensorDaoImpl: SensorDaoFacade {
    private fun resultRowToSensor(row:ResultRow) = Sensor(sensor_id =row[Sensors.sensor_id], sensor_name = row[Sensors.sensor_name])

    override suspend fun createSensor(sensor: Request_Sensor): Sensor?=dbQuery {
        val insertStatement = Sensors.insert {
            it[Sensors.sensor_name]=sensor.sensor_name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToSensor)
    }

    override suspend fun findById(sensorId: Int): Sensor?=dbQuery{
        Sensors.select {Sensors.sensor_id eq sensorId}.map(::resultRowToSensor).singleOrNull()

    }

    override suspend fun allSensors(): List<Sensor>? = dbQuery{
        Sensors.selectAll().map(::resultRowToSensor)
    }


    override suspend fun deleteSensor(sensorId: Int): Boolean = dbQuery{
        Sensors.deleteWhere { Sensors.sensor_id eq sensorId } > 0
    }

    override suspend fun editSensor(sensor: Sensor): Boolean = dbQuery {
        Sensors.update({Sensors.sensor_id eq sensor.sensor_id}){
            it[Sensors.sensor_name]=sensor.sensor_name
        } > 0
    }


}
val sensorDao = SensorDaoImpl()