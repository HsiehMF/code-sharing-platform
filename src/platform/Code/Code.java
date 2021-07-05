package platform.Code;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String title;
    private String code;
    private String date;

    public Code(int id, String title, String code, String date) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.date = date;
    }

    public Code() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String updateCode) {
        this.code = updateCode;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String updateTitle) {
        this.title = updateTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
