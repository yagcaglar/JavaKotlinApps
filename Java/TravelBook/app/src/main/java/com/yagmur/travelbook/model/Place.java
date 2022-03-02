package com.yagmur.travelbook.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//if entity is specified, table name can be changed
@Entity
public class Place implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "lat")
    public Double lat;

    @ColumnInfo(name = "lng")
    public Double lng;

    public Place(String name, Double lat, Double lng){
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
}
