package com.nasyxnadeem.capstoneapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.nasyxnadeem.capstoneapp.network.BooksApi
import com.nasyxnadeem.capstoneapp.repository.FireRepository
import com.nasyxnadeem.capstoneapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Singleton
    @Provides
    fun provideFireBookRepository() = FireRepository(queryBook = FirebaseFirestore.getInstance().collection("books"))

    @Provides
    @Singleton
    fun provideBookApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }
}