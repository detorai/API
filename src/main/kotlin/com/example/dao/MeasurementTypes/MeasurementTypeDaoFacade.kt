package com.example.dao.MeasurementTypes

import com.example.models.Measurement_type

interface MeasurementTypeDaoFacade {
    suspend fun findByTypeId(type_id:Int): Measurement_type?
    suspend fun createMeasurementType(measurement_type: Measurement_type): Measurement_type?
    suspend fun deleteMeasurementType(type_id: Int): Boolean
}