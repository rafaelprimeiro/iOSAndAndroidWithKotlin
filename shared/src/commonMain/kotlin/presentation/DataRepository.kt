package br.com.rafaelgabriel.presentation

import br.com.rafaelgabriel.model.Member

interface DataRepository {
    var members: List<Member>?
    var onRefreshListeners: List<() -> Unit>

    suspend fun update()
}