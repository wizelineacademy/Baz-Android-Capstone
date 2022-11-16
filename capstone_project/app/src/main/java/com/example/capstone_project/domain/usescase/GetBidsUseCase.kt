package com.example.capstone_project.domain.usescase

import com.example.capstone_project.data.Resource
import com.example.capstone_project.data.local.entities.toDatabase
import com.example.capstone_project.data.repository.CriptoRepository
import com.example.capstone_project.domain.model.BidDomain
import com.example.capstone_project.presentation.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetBidsUseCase @Inject constructor(private val repository: CriptoRepository) {

    suspend operator fun invoke(book: String): Flow<Resource<List<BidDomain>>> =
        flow<Resource<List<BidDomain>>> {
            emit(Resource.loading())
            try {
                val bid = if (Util.isNetworkEnabled()) repository.getBidsApi(book) else repository.getAllBidsLocal()
                if (bid.isNotEmpty()) {
                    repository.insertBidLocal(bid.map { it.toDatabase() })
                }
                emit(Resource.Success(bid))
            } catch (e: Exception) {
                e.message?.let { Resource.error<String>("Error en la peticion") }
            }
        }.flowOn(Dispatchers.IO)
}
