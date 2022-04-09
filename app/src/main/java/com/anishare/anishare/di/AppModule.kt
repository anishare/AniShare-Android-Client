package com.anishare.anishare.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.anishare.anishare.BaseApplication
import com.anishare.anishare.domain.AniShareDB
import com.anishare.anishare.domain.dao.UserDataDao
import com.anishare.anishare.domain.repository.UserDataRepo
import com.anishare.anishare.domain.repository.UserDataRepoImpl
import com.anishare.anishare.network.UserService
import com.anishare.anishare.ui.auth.AuthRepo
import com.anishare.anishare.ui.auth.AuthRepoImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun provideAuthRepo(firebaseAuth: FirebaseAuth): AuthRepo {
        return AuthRepoImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesDB(app: Application): AniShareDB {
        return Room.databaseBuilder(
            app,
            AniShareDB::class.java,
            "anisharedb"
        ).build()
    }

    @Provides
    @Singleton
    fun providesUserDataRepo(db: AniShareDB, userService: UserService): UserDataRepo {
        return UserDataRepoImpl(db.userDataDao, userService)
    }
}