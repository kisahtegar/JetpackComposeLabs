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
}