package com.codevivus.b2b.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String image;

    private String url;

    private Date startDate;

    private Date endDate;

    private String category;

    private String location;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}


