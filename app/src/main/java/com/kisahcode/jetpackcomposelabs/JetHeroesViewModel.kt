package com.kisahcode.jetpackcomposelabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kisahcode.jetpackcomposelabs.data.HeroRepository
import com.kisahcode.jetpackcomposelabs.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel for the JetHeroes app.
 *
 * This ViewModel is responsible for managing the hero data and preparing it for display.
 * It retrieves the heroes from the repository, sorts them by name, and groups them by the first letter of their name.
 *
 * @param repository The [HeroRepository] used to fetch hero data.
 */
class JetHeroesViewModel(private val repository: HeroRepository): ViewModel() {

    // Internal mutable state flow that holds the grouped hero data.
    private val _groupedHeroes = MutableStateFlow(
        repository.getHeroes()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    // Public state flow that exposes the grouped hero data.
    val groupedHeroes: StateFlow<Map<Char, List<Hero>>> get() = _groupedHeroes
}

/**
 * Factory class for creating instances of [JetHeroesViewModel].
 *
 * This factory is used to provide the [HeroRepository] dependency to the ViewModel.
 *
 * @param repository The [HeroRepository] used to fetch hero data.
 */
class ViewModelFactory(private val repository: HeroRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JetHeroesViewModel::class.java)) {
            return JetHeroesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}