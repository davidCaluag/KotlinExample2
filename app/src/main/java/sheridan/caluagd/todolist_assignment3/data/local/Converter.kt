package sheridan.caluagd.todolist_assignment3.data.local

import androidx.room.TypeConverter
import sheridan.caluagd.todolist_assignment3.domain.Category
import java.util.Date

class Converters {

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromConditionToInt(condition: Category): Int{
        return condition.ordinal
    }

    @TypeConverter
    fun fromIntToCondition(code: Int): Category {
        

        return Category.entries[code]
    }
}