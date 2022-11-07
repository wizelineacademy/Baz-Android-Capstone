package wizeline.crypto.currency.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import wizeline.crypto.currency.data.repositories.CryptoCurrenciesRepositoryImp
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RespositoriesModule {
    @Binds
    abstract fun bindAvailableBooksRepository(iml: CryptoCurrenciesRepositoryImp):CryptoCurrenciesRepository
}