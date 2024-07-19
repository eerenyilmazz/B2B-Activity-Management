package com.codevivus.b2b.dtos;

import com.codevivus.b2b.entity.Manager;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String url;
    private Date startDate;
    private Date endDate;
    private String category;
    private String location;
    private Manager manager;
}
