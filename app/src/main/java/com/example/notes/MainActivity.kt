package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.startup.AppInitializer
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.di.KoinInit
import com.example.notes.presentation.event.NoteUiEvent
import com.example.notes.presentation.state.NoteUiState
import com.example.notes.ui.adapter.NoteAdapter
import com.example.notes.ui.base.BaseActivity
import com.example.notes.ui.dialog.DialogAddNoteFragment
import com.example.notes.viewmodel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.Koin
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    KoinScopeComponent {
    override val scope: Scope by lazy {
        createScope(this)
    }
    private val myKoin by lazy {
        AppInitializer.getInstance(this)
            .initializeComponent(KoinInit::class.java)
    }

    override fun getKoin(): Koin = myKoin

    private val mViewModel: NoteViewModel by viewModel()

    override fun onViewReady(p0: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {
            mViewModel.state.collect { state ->
                when (state) {
                    is NoteUiState.OnShowNote -> {
                        val adapter = NoteAdapter()
                        binding.recyclerview.layoutManager = GridLayoutManager(this@MainActivity, 2)
                        binding.recyclerview.adapter = adapter
                        adapter.setData(state.data)
                    }
                }


            }
        }
        binding.add.setOnClickListener {
            val dialogFragment = DialogAddNoteFragment() {
                mViewModel.onEvent(NoteUiEvent.OnAddNote(it))
            }
            val bundle = Bundle()
//            bundle.putString("title", state.title)
//            bundle.putString("subTitle", state.subTitle)
//            bundle.putBoolean("type", state.type)
//            dialogFragment.arguments = bundle
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                ft.remove(prev)
            }

            ft.addToBackStack(null)
            dialogFragment.show(ft, "dialog")
        }
    }
}