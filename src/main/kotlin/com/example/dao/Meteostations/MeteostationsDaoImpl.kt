package com.example.dao.Meteostations

import com.example.dao.DatabaseFactory.dbQuery
import com.example.models.Meteostation
import com.example.models.Meteostations
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class MeteostationsDaoImpl:MeteostationsDaoFacade {
    private fun resultMapper(row: ResultRow) = Meteostation(station_id = row[Meteostations.station_id], station_name = row[Meteostations.station_name], longitude = row[Meteostations.longitude], latitude = row[Meteostations.latitude], station_hight_above_sea = row[Meteostations.station_hight_above_sea], station_county = row[Meteostations.station_county])
    override suspend fun findByStation(stationId: Int): List<Meteostation> = dbQuery {
       Meteostations.selectAll().where { Meteostations.station_id eq stationId}.map { resultMapper(it) }
    }
    
    override suspend fun createStation(meteostation: Meteostation): Meteostation? = dbQuery{
        val insertStation = Meteostations.insert {
            it[Meteostations.station_id]=meteostation.station_id
            it[Meteostations.station_name]=meteostation.station_name
            it[Meteostations.longitude]=meteostation.longitude
            it[Meteostations.latitude]=meteostation.latitude
            it[Meteostations.station_hight_above_sea]=meteostation.station_hight_above_sea
            it[Meteostations.station_county]=meteostation.station_county
        }
        insertStation.resultedValues?.singleOrNull()?.let {
            Meteostation(
                station_id = it[Meteostations.station_id],
                station_name = it[Meteostations.station_name],
                longitude = it[Meteostations.longitude],
                latitude = it[Meteostations.latitude],
                station_hight_above_sea = it[Meteostations.station_hight_above_sea],
                station_county = it[Meteostations.station_county],
            )
        }
    }

    override suspend fun deleteStation(stationId:Int): Boolean = dbQuery{
        Meteostations.deleteWhere { Meteostations.station_id eq stationId } > 0
    }

}
val meteostationDao= MeteostationsDaoImpl()