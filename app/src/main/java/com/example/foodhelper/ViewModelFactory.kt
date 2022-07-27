package com.example.foodhelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")

@Singleton
class ViewModelFactory @Inject constructor(
    private val providers:@JvmSuppressWildcards Map<Class<out ViewModel>,
             Provider<ViewModel>>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var provider: Provider<out ViewModel>? = providers[modelClass]
        if (provider == null) {
            for ((key, value) in providers) {
                if (modelClass.isAssignableFrom(key)) {
                    provider = value
                    break
                }
            }
        }
        if (provider == null) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
