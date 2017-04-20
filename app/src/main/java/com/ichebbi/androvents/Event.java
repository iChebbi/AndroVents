package com.ichebbi.androvents;

import java.io.Serializable;

/**
 * Created by ichebbi on 20/04/17.
 */

public class Event implements Serializable {
    private String start_time;
    private String venue_address;
    private String venue_name;
    private String city_name;
    private String region_name;
    private String postal_code;
    private String title;
    private String id;

    public Event(String start_time, String venue_address, String venue_name, String city_name, String region_name, String postal_code, String title, String id) {
        this.start_time = start_time;
        this.venue_address = venue_address;
        this.venue_name = venue_name;
        this.city_name = city_name;
        this.region_name = region_name;
        this.postal_code = postal_code;
        this.title = title;
        this.id = id;
    }

    public Event() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
}
