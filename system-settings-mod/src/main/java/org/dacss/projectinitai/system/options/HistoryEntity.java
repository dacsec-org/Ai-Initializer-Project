package org.dacss.projectinitai.system.options;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/**
 * <h1>{@link HistoryEntity}</h1>
 * An entity class that represents a history of changes to system settings.
 */
@Entity
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key;
    private String oldValue;
    private String newValue;
    private LocalDateTime timestamp;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}

    public String getOldValue() {return oldValue;}

    public void setOldValue(String oldValue) {this.oldValue = oldValue;}

    public String getNewValue() {return newValue;}

    public void setNewValue(String newValue) {this.newValue = newValue;}

    public LocalDateTime getTimestamp() {return timestamp;}

    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
}
