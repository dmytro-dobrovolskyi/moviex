package com.moviex.persistence.entity.movie;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "\"Movie\"")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "imdbID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @Column(name = "\"Id\"")
    @Setter(AccessLevel.PRIVATE)
    private String imdbID;

    @Column(name = "\"Runtime\"")
    private String runtime;

    @Column(name = "\"Genre\"")
    private String genre;

    @Column(name = "\"Director\"")
    @Lob
    private String director;

    @Column(name = "\"Writer\"")
    @Lob
    private String writer;

    @Column(name = "\"Actors\"")
    @Lob
    private String actors;

    @Column(name = "\"ImdbVotes\"")
    @Setter
    private String imdbVotes;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movie")
    @Setter
    private MovieSearchMetadata movieSearchMetadata;

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
}
