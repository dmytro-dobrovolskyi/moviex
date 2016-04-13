package com.moviex.imgservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Image {

    @Id
    @NonNull
    private String id;

    @Setter
    @Column(nullable = false)
    @JsonIgnore
    private LocalTime lastTimeRequested;
}
