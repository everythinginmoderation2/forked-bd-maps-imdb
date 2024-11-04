package com.amazon.ata.maps;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an actor in IMDB. Each actor's name must be unique.
 */
public class Actor {
    private final String name;
    private final LocalDate birthdate;
    private final String birthCity;
    private final Set<Movie> moviesAppearedIn = new HashSet<>();

    /**
     * Constructs a new actor with the specifid parameters.
     *
     * @param name Actor's name (must be unique)
     * @param birthdate Actor's birthdate. Can be null
     * @param birthCity Actor's birth city. Can be null
     */
    public Actor(String name, LocalDate birthdate, String birthCity) {
        this.name = name;
        this.birthdate = birthdate;
        this.birthCity = birthCity;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public Set<Movie> getMoviesAppearedIn() {
        return moviesAppearedIn;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Birthday: " + birthdate + "\n" + "Birth city: " + birthCity + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return Objects.equals(name, actor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
