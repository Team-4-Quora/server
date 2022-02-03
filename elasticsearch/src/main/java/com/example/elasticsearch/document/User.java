package com.example.elasticsearch.document;


import com.example.elasticsearch.helper.Indices;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = Indices.User_index)
@Setting(settingPath = "static/es-setting.json")
public class User {

    @Id
    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type=FieldType.Text)
    private Long points;

    @Field(type=FieldType.Text)
    private String level;

    @Field(type=FieldType.Text)
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
