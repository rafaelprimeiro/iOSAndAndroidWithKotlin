package br.com.rafaelgabriel.githubkmp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rafaelgabriel.Greeting
import br.com.rafaelgabriel.api.UpdateProblem
import br.com.rafaelgabriel.model.Member
import br.com.rafaelgabriel.presentation.MembersPresenter
import br.com.rafaelgabriel.presentation.MembersView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MembersView {

    private val repository by lazy {
        (application as GitHubKMPApplication).dataRepository
    }
    private val presenter by lazy {
        MembersPresenter(this, repository = repository)
    }

    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greeting.text = Greeting().greeting()

        setupRecyclerView()

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override var isUpdating = false

    override fun onUpdate(members: List<Member>) {
        adapter.members = members
        runOnUiThread {
            adapter.notifyDataSetChanged()
        }
    }

    override fun showError(error: Throwable) {
        val errorMessage = when (error) {
            is UpdateProblem -> getString(R.string.update_problem)
            else -> getString(R.string.unknow_error)
        }

        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        memberRecycleView.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter(listOf())
        memberRecycleView.adapter = adapter
    }
}
