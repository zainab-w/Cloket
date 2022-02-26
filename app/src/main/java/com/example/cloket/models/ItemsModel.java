package com.example.cloket.models;

public class ItemsModel
{
    String id, name, description, categoryId,date,URL;
    long timestamp;

    //constructor
    public ItemsModel() {
    }



    public ItemsModel(String id, String name, String description, String categoryId, String date, String URL, long timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.date = date;
        this.URL = URL;
        this.timestamp = timestamp;
    }
        //getters and setters
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
    }

