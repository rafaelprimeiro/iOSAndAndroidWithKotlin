package br.com.rafaelgabriel.model

import br.com.rafaelgabriel.api.GitHubAPI
import br.com.rafaelgabriel.api.UpdateProblem
import br.com.rafaelgabriel.presentation.DataRepository

class MembersDataRepository(
    private val api: GitHubAPI
): DataRepository {
    override var members: List<Member>? = null
    override var onRefreshListeners: List<() -> Unit> = emptyList()

    override suspend fun update() {
        var newMembers = try {
            api.getMembers()
        } catch (cause: Throwable) {
            throw UpdateProblem()
        }

        if (newMembers != members) {
            members = newMembers
            callRefreshListeners()
        }
    }

    private fun callRefreshListeners() {
        onRefreshListeners.forEach { it() }
    }
}