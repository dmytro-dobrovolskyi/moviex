package com.moviex.persistence.entity.movie;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "\"Movie\"")
public class Movie {

    @Id
    @Column(name = "\"Id\"")
    @Setter(AccessLevel.PRIVATE)
    private String id = UUID.randomUUID().toString();

    @Column(name = "\"Runtime\"")
    private String runtime;

    @Column(name = "\"Genre\"")
    private String genre;

    @Column(name = "\"Director\"")
    private String director;

    @Column(name = "\"Writer\"")
    private String writer;

    @Column(name = "\"Actors\"")
    private String actors;

    @Column(name = "\"ImdbRating\"")
    @Setter
    private String imdbRating;

    @Column(name = "\"Poster\"")
    private String poster;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"SearchMetadataId\"")
    @Setter
    private MovieSearchMetadata movieSearchMetadata;

    private Movie() {}

    @JsonProperty("Runtime")
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("Genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @JsonProperty("Director")
    public void setDirector(String director) {
        this.director = director;
    }

    @JsonProperty("Actors")
    public void setActors(String actors) {
        this.actors = actors;
    }

    @JsonProperty("Writer")
    public void setWriter(String writer) {
        this.writer = writer;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setMovieSearchMetadata(MovieSearchMetadata movieSearchMetadata) {
        this.movieSearchMetadata = movieSearchMetadata;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
