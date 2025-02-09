package org.dacss.projectinitai.system;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SystemSettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String value;
    private String userId;
    private int version;
    private String category;
    private String group;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}

    public String getValue() {return value;}

    public void setValue(String value) {this.value = value;}

    public String getUserId() {return userId;}

    public void setUserId(String userId) {this.userId = userId;}

    public int getVersion() {return version;}

    public void setVersion(int version) {this.version = version;}

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public String getGroup() {return group;}

    public void setGroup(String group) {this.group = group;}
}
