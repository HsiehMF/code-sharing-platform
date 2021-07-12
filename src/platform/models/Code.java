package platform.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Code")
public class Code {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String title;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long originTimeLimit;  // 解決時間相減問題的變數
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean timeLimit;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean viewsLimit;

    private String code;
    private String date;
    private int views;
    private long time;

    public Code(String id, String title, String code, String date, int views, long time, long originTimeLimit, boolean timeLimit, boolean viewsLimit) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.date = date;
        this.views = views;
        this.time = time;
        this.originTimeLimit = originTimeLimit;
        this.timeLimit = timeLimit;
        this.viewsLimit = viewsLimit;
    }

    public Code() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String updateCode) {
        this.code = updateCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViews() {
        return views;
    }

    public long getOriginTimeLimit() {
        return originTimeLimit;
    }

    public void setOriginTimeLimit(long originTimeLimit) {
        this.originTimeLimit = originTimeLimit;
    }

    public void setTimeLimit(boolean timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isTimeLimit() {
        return timeLimit;
    }

    public void setViewsLimit(boolean viewsLimit) {
        this.viewsLimit = viewsLimit;
    }

    public boolean isViewsLimit() {
        return viewsLimit;
    }
}
