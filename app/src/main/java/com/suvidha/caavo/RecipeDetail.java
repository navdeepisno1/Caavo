package com.suvidha.caavo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//This is the Entity or Table structure

//Annotate the class with @Entity and here we are giving the table name as Recipes
@Entity(tableName = "Recipes")
public class RecipeDetail {
    //To create primary key
    @PrimaryKey(autoGenerate = true)
    private int rid;

    //To create column name called ID
    @ColumnInfo(name = "ID")
    private String id;
    @ColumnInfo(name = "NAME")
    private String name;
    @ColumnInfo(name = "IMAGE")
    private String image;
    @ColumnInfo(name = "PRICE")
    private String price;

    public RecipeDetail()
    {

    }

    public RecipeDetail(int rid, String id, String name, String image, String price) {
        this.rid = rid;
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
