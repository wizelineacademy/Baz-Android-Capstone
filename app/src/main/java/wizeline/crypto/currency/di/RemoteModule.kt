package wizeline.crypto.currency.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wizeline.crypto.currency.data.resource.remote.CryptoCurrenciesApi
import wizeline.crypto.currency.utils.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun providersAvailableBooks(): CryptoCurrenciesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoCurrenciesApi::class.java)
    }

}