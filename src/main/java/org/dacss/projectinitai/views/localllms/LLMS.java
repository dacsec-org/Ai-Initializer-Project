package org.dacss.projectinitai.views.localllms;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class LLMS {
    private String name;
    private String description;
    private String type;
    private String sizes;
    private String pulls;
    private String tags;
    private String updated;
    private boolean isInstalled;
    private String dateInstalled;
    private String availableSizes;

    public void setInstallationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dateInstalled = LocalDate.now().format(formatter);
    }
}
