package dev.passerby.seven_winds_test.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import dev.passerby.seven_winds_test.data.models.db.PointDbModel

class JsonConverters {

    @TypeConverter
    fun pointToJson(value: PointDbModel): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToPoint(value: String): PointDbModel = Gson().fromJson(value, PointDbModel::class.java)
}