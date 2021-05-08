package com.gvdw.discogsviewer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Gullian Van Der Walt
 * Created at 09:58 on May, 2021
 */
public class Release {
    private String id;
    private String title;
    private String country;
    private String releaseType;
    private int releaseYear;
    private String formatType;
    private String formatDesc;
    private String label;
    private String thumbnailPath;

    public Release() {
    }

    public Release(String id, String title, String country, String releaseType, int releaseYear, String formatType,
                   String formatDesc, String label, String thumbnailPath) {
        this.id = id;
        this.title = title;
        this.country = country;
        this.releaseType = releaseType;
        this.releaseYear = releaseYear;
        this.formatType = formatType;
        this.formatDesc = formatDesc;
        this.label = label;
        this.thumbnailPath = thumbnailPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getFormatDesc() {
        return formatDesc;
    }

    public void setFormatDesc(String formatDesc) {
        this.formatDesc = formatDesc;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
