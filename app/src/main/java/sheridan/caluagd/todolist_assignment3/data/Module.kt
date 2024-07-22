package sheridan.caluagd.todolist_assignment3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sheridan.caluagd.todolist_assignment3.data.local.LocalDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object Module {

    @Singleton
    @Provides
    fun provideProductDatabase(@ApplicationContext context: Context): LocalDatabase = Room.databaseBuilder(context, LocalDatabase:: class.java, "todo_database").fallbackToDestructiveMigration().build()
}