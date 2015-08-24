package com.moviex.persistence.entity.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "\"MovieSearchMetadata\"")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "imdbID")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchMetadata {

    @Id
    @Setter(AccessLevel.PRIVATE)
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

    @Column(name = "\"ImdbRating\"")
    @Setter
    private String imdbRating;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Setter
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
}
