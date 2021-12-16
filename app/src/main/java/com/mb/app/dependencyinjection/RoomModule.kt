package com.mb.app.dependencyinjection

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.mb.app.data.offline.AppDatabase
import com.mb.app.data.offline.DatabaseBuilder
import com.mb.app.data.offline.ProductDao
import com.mb.app.utils.CacheReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(app: MultiDexApplication?) {

    private var context: Context = app!!

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return DatabaseBuilder.buildDatabase(context)
    }

    @Singleton
    @Provides
    fun providesProductDao(database: AppDatabase): ProductDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun providesDataReader(productDao: ProductDao): CacheReader {
        return CacheReader(productDao)
    }

}