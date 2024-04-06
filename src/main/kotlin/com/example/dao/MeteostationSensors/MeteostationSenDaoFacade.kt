package com.example.dao.MeteostationSensors

import com.example.models.Meteostations_Sensors
import com.example.models.Request_Meteostations_Sensors

interface MeteostationSenDaoFacade {
    suspend fun findByInvNumb(sensor_inv_numb: Int): List<Meteostations_Sensors>?

    suspend fun createInvNumb(meteostationsSensors: Request_Meteostations_Sensors): Meteostations_Sensors?

    suspend fun deleteInvNumb(sensor_inv_numb: Int): Boolean
}