package com.example.debttrackerapp.total_debt_simple

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DebtDatabase {
        return Room.databaseBuilder(
            context,
            DebtDatabase::class.java,
            "debt_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDebtDao(db: DebtDatabase): DebtDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideAdvanceDebtDao(db: DebtDatabase): AdvanceDebtDao {
        return db.AdvDebtDao
    }
}