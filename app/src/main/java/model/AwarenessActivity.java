package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class AwarenessActivity {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("institute_type")
    @Expose
    private String instituteType;
    @SerializedName("awareness_by")
    @Expose
    private String awarenessBy;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("create_time")
    @Expose
    private String createTime;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("modify_date")
    @Expose
    private String modifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }

    public String getAwarenessBy() {
        return awarenessBy;
    }

    public void setAwarenessBy(String awarenessBy) {
        this.awarenessBy = awarenessBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
