package com.developer.nikhil.fillbelly.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.developer.nikhil.fillbelly.FillBellyApplication;
import com.developer.nikhil.fillbelly.model.NearByBean;

import java.io.IOException;
import java.util.ArrayList;


public class Preferences {
    private static final String USERID = "user_id";
    private static final String USEREMAIL = "user_email";
    private static final String FNAME = "f_name";
    private static final String LNAME = "l_name";
    private static final String PHONE = "phone";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String ADDRESS = "address";
    private static final String FILTER_RATE = "filter_rate";
    private static final String FILTER_PRICE = "filter_price";
    private static final String FILTER_PRICE1 = "filter_price1";
    private static final String FILTER_PRICE2 = "filter_price2";
    private static final String FILTER_PRICE3 = "filter_price3";
    private static final String C1 = "c1";
    private static final String C2 = "c2";
    private static final String C3 = "c3";
    private static final String C4 = "c4";
    private static final String C5 = "c5";
    private static final String C6 = "c6";
    private static final String C7 = "c7";
    private static final String C8 = "c8";
    private static final String C9 = "c9";
    private static final String C10 = "c10";
    private static final String C11 = "c11";
    private static final String C12 = "c12";
    private static final String C13 = "c13";
    private static final String CUISINELIST = "cuisine_list";
    private static final String CUR_LATITUDE = "cur_latitude";
    private static final String CUR_LONGITUDE = "cur_longitude";
    private static final String FREQHOTELNAME = "freq_hotelname";
    private static final String GRAPHLIST = "graphlist";

    private static SharedPreferences get() {
        return FillBellyApplication.getAppContext().getSharedPreferences("FillBellyApplication", Context.MODE_PRIVATE);
    }

    public static String getUserId() {
        return get().getString(USERID, null);
    }

    public static void setUserId(String user_id) {
        get().edit().putString(USERID, user_id).commit();
    }

    public static String getFname() {
        return get().getString(FNAME, null);
    }

    public static void setFname(String fname) {
        get().edit().putString(FNAME, fname).commit();
    }

    public static String getLname() {
        return get().getString(LNAME, null);
    }

    public static void setLname(String lname) {
        get().edit().putString(LNAME, lname).commit();
    }

    public static String getUseremail() {
        return get().getString(USEREMAIL, null);
    }

    public static void setUseremail(String useremail) {
        get().edit().putString(USEREMAIL, useremail).commit();
    }

    public static String getPhone() {
        return get().getString(PHONE, null);
    }

    public static void setPhone(String phone) {
        get().edit().putString(PHONE, phone).commit();
    }

    public static String getLatitude() {
        return get().getString(LATITUDE, null);
    }

    public static void setLatitude(String latitude) {
        get().edit().putString(LATITUDE, latitude).commit();
    }

    public static String getLongitude() {
        return get().getString(LONGITUDE, null);
    }

    public static void setLongitude(String longitude) {
        get().edit().putString(LONGITUDE, longitude).commit();
    }

    public static String getCurLatitude() {
        return get().getString(CUR_LATITUDE, null);
    }

    public static void setCurLatitude(String curLatitude) {
        get().edit().putString(CUR_LATITUDE, curLatitude).commit();
    }

    public static String getCurLongitude() {
        return get().getString(CUR_LONGITUDE, null);
    }

    public static void setCurLongitude(String curLongitude) {
        get().edit().putString(CUR_LONGITUDE, curLongitude).commit();
    }

    public static String getAddress() {
        return get().getString(ADDRESS, null);
    }

    public static void setAddress(String address) {
        get().edit().putString(ADDRESS, address).commit();
    }

    public static String getFilterRate() {
        return get().getString(FILTER_RATE, null);
    }

    public static void setFilterRate(String filterRate) {
        get().edit().putString(FILTER_RATE, filterRate).commit();
    }

    public static String getFilterPrice() {
        return get().getString(FILTER_PRICE, null);
    }

    public static void setFilterPrice(String filterPrice) {
        get().edit().putString(FILTER_PRICE, filterPrice).commit();
    }

    public static String getFilterPrice1() {
        return get().getString(FILTER_PRICE1, null);
    }

    public static void setFilterPrice1(String filterPrice1) {
        get().edit().putString(FILTER_PRICE1, filterPrice1).commit();
    }

    public static String getFilterPrice2() {
        return get().getString(FILTER_PRICE2, null);
    }

    public static void setFilterPrice2(String filterPrice2) {
        get().edit().putString(FILTER_PRICE2, filterPrice2).commit();
    }

    public static String getFilterPrice3() {
        return get().getString(FILTER_PRICE3, null);
    }

    public static void setFilterPrice3(String filterPrice3) {
        get().edit().putString(FILTER_PRICE3, filterPrice3).commit();
    }

    public static String getFreqhotelname() {
        return get().getString(FREQHOTELNAME, null);
    }

    public static void setFreqhotelname(String freqhotelname) {
        get().edit().putString(FREQHOTELNAME, freqhotelname).commit();
    }

    public static String getC1() {
        return get().getString(C1, null);
    }

    public static void setC1(String c1) {
        get().edit().putString(C1, c1).commit();
    }

    public static String getC2() {
        return get().getString(C2, null);
    }

    public static void setC2(String c2) {
        get().edit().putString(C2, c2).commit();
    }

    public static String getC3() {
        return get().getString(C3, null);
    }

    public static void setC3(String c3) {
        get().edit().putString(C3, c3).commit();
    }

    public static String getC4() {
        return get().getString(C4, null);
    }

    public static void setC4(String c4) {
        get().edit().putString(C4, c4).commit();
    }

    public static String getC5() {
        return get().getString(C5, null);
    }

    public static void setC5(String c5) {
        get().edit().putString(C5, c5).commit();
    }

    public static String getC6() {
        return get().getString(C6, null);
    }

    public static void setC6(String c6) {
        get().edit().putString(C6, c6).commit();
    }

    public static String getC7() {
        return get().getString(C7, null);
    }

    public static void setC7(String c7) {
        get().edit().putString(C7, c7).commit();
    }

    public static String getC8() {
        return get().getString(C8, null);
    }

    public static void setC8(String c8) {
        get().edit().putString(C8, c8).commit();
    }

    public static String getC9() {
        return get().getString(C9, null);
    }

    public static void setC9(String c9) {
        get().edit().putString(C9, c9).commit();
    }

    public static String getC10() {
        return get().getString(C10, null);
    }

    public static void setC10(String c10) {
        get().edit().putString(C10, c10).commit();
    }

    public static String getC11() {
        return get().getString(C11, null);
    }

    public static void setC11(String c11) {
        get().edit().putString(C11, c11).commit();
    }

    public static String getC12() {
        return get().getString(C12, null);
    }

    public static void setC12(String c12) {
        get().edit().putString(C12, c12).commit();
    }

    public static String getC13() {
        return get().getString(C13, null);
    }

    public static void setC13(String c13) {
        get().edit().putString(C13, c13).commit();
    }

    public static void setCuisinelist(ArrayList<String> listType) throws IOException
    {
        get().edit().putString(CUISINELIST, ObjectSerializer.serialize(listType)).commit();
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<String> getCuisinelist() throws ClassNotFoundException, IOException
    {
        return (ArrayList<String>) ObjectSerializer.deserialize(get().getString(CUISINELIST, ObjectSerializer.serialize(null)));
    }

	public static void setGraphlist(ArrayList<NearByBean> listType) throws IOException
	{
		get().edit().putString(GRAPHLIST, ObjectSerializer.serialize(listType)).commit();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<NearByBean> getGraphlist() throws ClassNotFoundException, IOException
	{
		return (ArrayList<NearByBean>) ObjectSerializer.deserialize(get().getString(GRAPHLIST, ObjectSerializer.serialize(null)));
	}

/*	public static void setItemList(ArrayList<WishlistBean> listType) throws IOException
	{
		get().edit().putString(WISHLISTITEMLIST, ObjectSerializer.serialize(listType)).commit();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<WishlistBean> getItemList() throws ClassNotFoundException, IOException
	{
		return (ArrayList<WishlistBean>) ObjectSerializer.deserialize(get().getString(WISHLISTITEMLIST, ObjectSerializer.serialize(null)));
	}

	public static void setProductdetaillist(ArrayList<HomeItemBean> listType) throws IOException
	{
		get().edit().putString(PRODUCTDETAILLIST, ObjectSerializer.serialize(listType)).commit();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<HomeItemBean> getProductdetaillist() throws ClassNotFoundException, IOException
	{
		return (ArrayList<HomeItemBean>) ObjectSerializer.deserialize(get().getString(PRODUCTDETAILLIST, ObjectSerializer.serialize(null)));
	}*/

	/*public static void setBuyvideolist(ArrayList<FavBean> listType) throws IOException
	{
		get().edit().putString(BUYVIDEOLIST, ObjectSerializer.serialize(listType)).commit();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<FavBean> getBuyVideolist() throws ClassNotFoundException, IOException
	{
		return (ArrayList<FavBean>) ObjectSerializer.deserialize(get().getString(BUYVIDEOLIST, ObjectSerializer.serialize(null)));
	}*/

}
