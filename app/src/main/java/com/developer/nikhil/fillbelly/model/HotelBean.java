package com.developer.nikhil.fillbelly.model;

import java.io.Serializable;



public class HotelBean implements Serializable {

    public String tvDetail;
    public String tvRating;
    public String tvHotelName;
    public String ivHotelPic;

    public String locationlink;
    public String city_name;
    public String country_name;
    public String title;
    public String average_cost_for_two;
    public String cuisines;
    public String currency;
    public String featured_image;
    public String has_online_delivery;
    public String has_table_booking;
    public String id;
    public String is_delivering_now;
    public String address;
    public String city_id;
    public String locality;
    public String locality_verbose;
    public String menu_url;
    public String name;
    public String offers;
    public String photos_url;
    public String price_range;
    public String thumb;
    public String user_rating;
    public String rating_text;
    public String votes;




    public HotelBean() {
    }

    public HotelBean(String tvDetail, String tvRating, String tvHotelName, String ivHotelPic, String locationlink,
                     String city_name, String country_name, String title, String average_cost_for_two,
                     String cuisines, String currency, String featured_image, String has_online_delivery,
                     String has_table_booking, String id, String is_delivering_now, String address,
                     String city_id, String locality, String locality_verbose, String menu_url,
                     String name, String offers, String photos_url, String price_range, String thumb,
                     String user_rating, String rating_text, String votes) {

        this.tvDetail = tvDetail;
        this.tvRating = tvRating;
        this.tvRating = tvRating;
        this.tvHotelName = tvHotelName;
        this.ivHotelPic = ivHotelPic;

        this.locationlink = locationlink;
        this.city_name = city_name;
        this.country_name = country_name;
        this.title = title;
        this.average_cost_for_two = average_cost_for_two;
        this.cuisines = cuisines;
        this.currency = currency;
        this.featured_image = featured_image;
        this.has_online_delivery = has_online_delivery;
        this.has_table_booking = has_table_booking;
        this.id = id;
        this.is_delivering_now = is_delivering_now;
        this.address = address;
        this.city_id = city_id;
        this.locality = locality;
        this.locality_verbose = locality_verbose;
        this.menu_url = menu_url;
        this.name = name;
        this.offers = offers;
        this.photos_url = photos_url;
        this.price_range = price_range;
        this.thumb = thumb;
        this.user_rating = user_rating;
        this.rating_text = rating_text;
        this.votes = votes;

    }

    public String getTvDetail() {
        return tvDetail;
    }

    public void setTvDetail(String tvDetail) {
        this.tvDetail = tvDetail;
    }

    public String getTvRating() {
        return tvRating;
    }

    public void setTvRating(String tvRating) {
        this.tvRating = tvRating;
    }

    public String getTvHotelName() {
        return tvHotelName;
    }

    public void setTvHotelName(String tvHotelName) {
        this.tvHotelName = tvHotelName;
    }

    public String getIvHotelPic() {
        return ivHotelPic;
    }

    public void setIvHotelPic(String ivHotelPic) {
        this.ivHotelPic = ivHotelPic;
    }

    public String getLocationlink() {
        return locationlink;
    }

    public void setLocationlink(String locationlink) {
        this.locationlink = locationlink;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public void setAverage_cost_for_two(String average_cost_for_two) {
        this.average_cost_for_two = average_cost_for_two;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }

    public String getHas_online_delivery() {
        return has_online_delivery;
    }

    public void setHas_online_delivery(String has_online_delivery) {
        this.has_online_delivery = has_online_delivery;
    }

    public String getHas_table_booking() {
        return has_table_booking;
    }

    public void setHas_table_booking(String has_table_booking) {
        this.has_table_booking = has_table_booking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_delivering_now() {
        return is_delivering_now;
    }

    public void setIs_delivering_now(String is_delivering_now) {
        this.is_delivering_now = is_delivering_now;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocality_verbose() {
        return locality_verbose;
    }

    public void setLocality_verbose(String locality_verbose) {
        this.locality_verbose = locality_verbose;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getPhotos_url() {
        return photos_url;
    }

    public void setPhotos_url(String photos_url) {
        this.photos_url = photos_url;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getRating_text() {
        return rating_text;
    }

    public void setRating_text(String rating_text) {
        this.rating_text = rating_text;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}
