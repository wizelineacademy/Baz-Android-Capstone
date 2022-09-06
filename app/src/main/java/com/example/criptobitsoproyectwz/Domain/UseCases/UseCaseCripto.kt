package com.example.criptobitsoproyectwz.data.Repository

import com.example.criptobitsoproyectwz.data.model.BaseResult

class useCaseCripto {
    /** Casos de Uso
     * Lógica del negocio, una unica acción
     */
    suspend operator fun invoke(): BaseResult? = RepositoryCripto().getAllCriptos()
}