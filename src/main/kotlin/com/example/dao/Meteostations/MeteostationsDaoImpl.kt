package com.example.dao.Meteostations

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.Measurements.MeasurementsDaoImpl
import com.example.models.*
import com.example.models.Measurements.defaultExpression
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class MeteostationsDaoImpl:MeteostationsDaoFacade {
    private fun resultMapper(row: ResultRow) = Meteostation(station_id = row[Meteostations.station_id], station_coord = row[Meteostations.station_coord], station_z = row[Meteostations.station_z],  station_address = row[Meteostations.station_address], station_name = row[Meteostations.station_name] )
    override suspend fun findByStation(): List<Meteostation> = dbQuery {
       Meteostations.selectAll().map (::resultMapper)
    }
    
    override suspend fun createStation(meteostation: Meteostation): Meteostation? = dbQuery{
        val insertStation = Meteostations.insert {
            it[Meteostations.station_id]=meteostation.station_id
            it[Meteostations.station_coord]=meteostation.station_coord
            it[Meteostations.station_z]=meteostation.station_z
            it[Meteostations.station_address]=meteostation.station_address
            it[Meteostations.station_name]=meteostation.station_name
        }
        insertStation.resultedValues?.singleOrNull()?.let {
            Meteostation(
                station_id = it[Meteostations.station_id],
                station_coord = it[Meteostations.station_coord],
                station_z = it[Meteostations.station_z],
                station_address = it[Meteostations.station_address],
                station_name = it[Meteostations.station_name]
            )
        }
    }

    override suspend fun deleteStation(stationId:Int): Boolean = dbQuery{
        Meteostations.deleteWhere { Meteostations.station_id eq stationId } > 0
    }

}
val meteostationDao= MeteostationsDaoImpl()