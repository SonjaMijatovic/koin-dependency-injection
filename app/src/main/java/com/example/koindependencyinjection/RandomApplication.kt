package com.example.koindependencyinjection

import android.app.Application
import com.example.koindependencyinjection.repository.NumberRepository
import com.example.koindependencyinjection.repository.NumberRepositoryImpl
import com.example.koindependencyinjection.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

class RandomApplication : Application() {

    private val appModule = module {
        single {
            Random()
        }
        single<NumberRepository> {
            NumberRepositoryImpl(get())
        }
    }

    private val mainModule = module {
        scope(named<MainActivity>()) {
            scoped {
                MainViewModel(get())
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RandomApplication)
            modules(listOf(appModule, mainModule))
        }
    }
}