package br.com.rafaelgabriel.presentation

import br.com.rafaelgabriel.model.Member

interface MembersView: BaseView {
    var isUpdating: Boolean
    fun onUpdate(members: List<Member>)
}