package com.moviex.persistence.entity.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "imdbID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchMetadata {

    @Id
    @Setter(AccessLevel.PRIVATE)
    private String imdbID;

    private String title;

    private String year;
    private String type;
    private String poster;
    private String imdbRating;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Movie movie;

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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("poster")
    public String getPoster() {
        return poster;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return title;
    }
}
