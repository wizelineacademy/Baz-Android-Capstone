package com.example.cryptocurrency_challenge.utils


data class ErrorModel (
    var title: String,
    var error: Data
)

sealed class Data{
    object ConectionError   : Data()
    object NoError          : Data()
    object AnotherError     : Data()
    object Loading          : Data()

    data class Success(
        var data: String
    )                       : Data()

    data class Error(
        var data: String
    )                       : Data()

    data class VersionError(
        var data: String
    )                       : Data()
}


fun getData(): Data{
    return Data.Success((100..1000).random().toString())
}


/*
val data = getData()
when(data.){
    is Data.Success -> {}
    is Data.Error -> {}
}
*/


enum class EnumError{
    SUCCESS,
    ERROR,
    CONECTION_ERROR,

}