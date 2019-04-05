package br.com.rafaelgabriel.githubkmp

import android.app.Application
import br.com.rafaelgabriel.api.GitHubAPI
import br.com.rafaelgabriel.model.MembersDataRepository
import br.com.rafaelgabriel.presentation.DataRepository

class GitHubKMPApplication: Application() {

    val dataRepository: DataRepository by lazy {
        MembersDataRepository(GitHubAPI())
    }

}