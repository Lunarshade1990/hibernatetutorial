package com.lunarshade.h1_ono_to_one.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @NonNull
    @Column(name = "hobby")
    private String hobby;
}
