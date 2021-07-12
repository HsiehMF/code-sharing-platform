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
    private long originTimeLimit;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean secret;

    private String code;
    private String date;
    private int views;
    private long time;

    public Code(String id, String title, String code, String date, int views, long time, long originTimeLimit, boolean secret) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.date = date;
        this.views = views;
        this.time = time;
        this.originTimeLimit = originTimeLimit;
        this.secret = secret;
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

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public boolean getSecret() {
        return secret;
    }
}
