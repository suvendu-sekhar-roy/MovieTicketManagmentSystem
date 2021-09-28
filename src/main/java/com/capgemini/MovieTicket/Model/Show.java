package com.capgemini.MovieTicket.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

    @Data
    @NoArgsConstructor
    @Entity
    @Table(name = "show")
    public class Show {
        @Id

        @GeneratedValue(strategy = GenerationType.AUTO)
        private int showId;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime showStartTime;

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime showEndTime;

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate showDate;

        private String showName;
        @OneToOne(mappedBy = "show")
        private Movie movie;

        @JsonIgnore
        @ManyToOne
        private Screen screen;

        @JsonIgnore
        @ManyToOne
        private Theatre theatre;

        @JsonIgnore
        @OneToOne
        private Booking booking;

    }
