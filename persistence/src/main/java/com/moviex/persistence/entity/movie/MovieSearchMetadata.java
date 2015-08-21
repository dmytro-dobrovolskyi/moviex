package com.moviex.persistence.entity.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Data
@Table(name = "\"MovieSearchMetadata\"")
public class MovieSearchMetadata {

    @Id
    @Setter
    @Column(name = "\"Id\"")
    private String imdbID;

    @Column(name = "\"Title\"")
    private String title;

    @Column(name = "\"Year\"")
    private String year;

    @Column(name = "\"Type\"")
    private String type;

    @Column(name = "\"Poster\"")
    private String poster;

    @OneToOne(mappedBy = "movieSearchMetadata")
    @Setter
    private Movie movie;

    public String getImdbID() {
        return imdbID;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
