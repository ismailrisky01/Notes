package com.example.notes.di


import androidx.room.Room
import com.example.notes.data.local.NoteDao
import com.example.notes.data.local.NoteDatabase
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.domain.feature.AddNote
import com.example.notes.domain.feature.DeleteNote
import com.example.notes.domain.feature.GetNote
import com.example.notes.domain.feature.UpdateNote
import com.example.notes.domain.repository.NoteRepository
import com.example.notes.domain.usecase.NoteUseCase
import com.example.notes.viewmodel.NoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModules = module {
    single {
        Room.databaseBuilder(
            get(),
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    single {
        get<NoteDatabase>().noteDao
    }

}
val repositoryModule = module {
    single<NoteRepository> { NoteRepositoryImpl(get()) }
}

val viewModel = module {
    viewModel { NoteViewModel(get()) }
}

val useCaseModules = module {
    single {
        NoteUseCase(
            getNote = GetNote(get()),
            addNote = AddNote(get()),
            deleteNote = DeleteNote(get()),
            updateNote = UpdateNote(get())
        )
    }
}
