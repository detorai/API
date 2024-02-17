package com.example.Services

import com.example.dao.MeasurementTypes.MeasurementTypeDaoFacade
import com.example.dao.MeasurementTypes.measurementDao
import com.example.models.Measurement_type
import com.example.models.Measurement_types
import com.example.models.SensorType

class MeasurementTypeService(private val MeasurementTypeRepository:MeasurementTypeDaoFacade) {
    suspend fun findByTypeId(type_id: Int): Measurement_type? {
        return MeasurementTypeRepository.findByTypeId(type_id)
    }
    suspend fun createMeasurementType(measurement_type: Measurement_type): Measurement_type? {
        return MeasurementTypeRepository.createMeasurementType(measurement_type)
    }
    suspend fun deleteMeasurementType(type_id: Int): Boolean {
        return MeasurementTypeRepository.deleteMeasurementType(type_id)
    }
}
val measurementTypeService = measurementDao