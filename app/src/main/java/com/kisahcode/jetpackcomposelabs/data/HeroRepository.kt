package com.kisahcode.jetpackcomposelabs.data

import com.kisahcode.jetpackcomposelabs.model.Hero
import com.kisahcode.jetpackcomposelabs.model.HeroesData

/**
 * A repository class that provides hero data.
 *
 * This class is responsible for retrieving the list of heroes. In a real application, this could be fetching
 * data from a local database, a remote server, or any other data source.
 */
class HeroRepository {

    /**
     * Retrieves the list of heroes.
     *
     * @return A list of [Hero] objects.
     */
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }

    /**
     * Searches for heroes whose names match the given query.
     *
     * This function filters the list of heroes to only include those whose names contain the
     * specified query string, ignoring case.
     *
     * @param query The search query string.
     * @return A list of [Hero] objects that match the search query.
     */
    fun searchHeroes(query: String): List<Hero> {
        return HeroesData.heroes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}