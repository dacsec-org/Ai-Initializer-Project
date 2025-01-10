package org.dacss.projectinitai.views.localllms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.dacss.projectinitai.utilities.LLMLinkScraper;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Local Llms")
@Route("local-llms")
@Menu(order = 11, icon = LineAwesomeIconUrl.TH_SOLID)
public class LocalLlmsView extends Div {

    private Grid<LLMS> grid;
    private GridListDataView<LLMS> gridListDataView;
    private Grid.Column<LLMS> nameColumn;
    private Grid.Column<LLMS> dateColumn;
    private Grid.Column<LLMS> isInstalledColumn;
    private Grid.Column<LLMS> descriptionColumn;
    private Grid.Column<LLMS> typeColumn;
    private Grid.Column<LLMS> availableSizesColumn;
    private Grid.Column<LLMS> pullsColumn;
    private Grid.Column<LLMS> tagsColumn;
    private Grid.Column<LLMS> updatedColumn;

    public LocalLlmsView() {
        addClassName("local-llms-view");
        setSizeFull();
        createGrid();
        add(grid);
        addSourceButtons();
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        addFiltersToGrid();
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.setSelectionMode(SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        List<LLMS> LLMS = new ArrayList<>();
        gridListDataView = grid.setItems(LLMS);
    }

    private void addColumnsToGrid() {
        createNameColumn();
        createDateColumn();
        createIsInstalledColumn();
        createDescriptionColumn();
        createTypeColumn();
        createAvailableSizesColumn();
        createPullsColumn();
        createTagsColumn();
        createUpdatedColumn();
    }

    private void createNameColumn() {
        nameColumn = grid.addColumn(LLMS::getName).setHeader("Name").setAutoWidth(true);
    }

    private void createDateColumn() {
        dateColumn = grid.addColumn(new LocalDateRenderer<>(LLMS -> {
            if (LLMS.isInstalled()) {
                return LocalDate.parse(LLMS.getDateInstalled());
            }
            return null;
        }, () -> DateTimeFormatter.ofPattern("M/d/yyyy")))
                .setComparator(LLMS::getDateInstalled).setHeader("Date Installed").setWidth("180px").setFlexGrow(0).setAutoWidth(true);
    }

    private void createIsInstalledColumn() {
        isInstalledColumn = grid.addColumn(new ComponentRenderer<>(LLMS -> {
            Span span = new Span();
            span.setText(LLMS.isInstalled() ? "Installed" : "Not Installed");
            return span;
        })).setComparator(LLMS::isInstalled).setHeader("Installed").setAutoWidth(true);
    }

    private void createDescriptionColumn() {
        descriptionColumn = grid.addColumn(LLMS::getDescription).setHeader("Description").setAutoWidth(true);
    }

    private void createTypeColumn() {
        typeColumn = grid.addColumn(LLMS::getType).setHeader("Type").setAutoWidth(true);
    }

    private void createAvailableSizesColumn() {
        availableSizesColumn = grid.addColumn(LLMS::getAvailableSizes).setHeader("Available Sizes").setAutoWidth(true);
    }

    private void createPullsColumn() {
        pullsColumn = grid.addColumn(LLMS::getPulls).setHeader("Pulls").setAutoWidth(true);
    }

    private void createTagsColumn() {
        tagsColumn = grid.addColumn(LLMS::getTags).setHeader("Tags").setAutoWidth(true);
    }

    private void createUpdatedColumn() {
        updatedColumn = grid.addColumn(LLMS::getUpdated).setHeader("Updated").setAutoWidth(true);
    }

    private void addFiltersToGrid() {
        HeaderRow filterRow = grid.appendHeaderRow();

        TextField nameFilter = new TextField();
        nameFilter.setPlaceholder("Filter");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setWidth("100%");
        nameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        nameFilter.addValueChangeListener(event -> gridListDataView
                .addFilter(LLMS -> StringUtils.containsIgnoreCase(LLMS.getName(), nameFilter.getValue())));
        filterRow.getCell(nameColumn).setComponent(nameFilter);

        DatePicker dateFilter = new DatePicker();
        dateFilter.setPlaceholder("Filter");
        dateFilter.setClearButtonVisible(true);
        dateFilter.setWidth("100%");
        dateFilter.addValueChangeListener(
                event -> gridListDataView.addFilter(LLMS -> areDatesEqual(LLMS, dateFilter)));
        filterRow.getCell(dateColumn).setComponent(dateFilter);

        Button downloadButton = new Button("Download Selected");
        downloadButton.addClickListener(event -> {
            List<LLMS> selectedItems = grid.getSelectedItems().stream().toList();
            if (selectedItems.isEmpty()) {
                Notification.show("No items selected", 3000, Notification.Position.MIDDLE);
            } else {
                for (LLMS llms : selectedItems) {
                    System.out.println("Downloading: " + llms.getName());
                }
                Notification.show("Download started for selected items", 3000, Notification.Position.MIDDLE);
            }
        });
        filterRow.getCell(isInstalledColumn).setComponent(downloadButton);
    }

    private boolean areDatesEqual(LLMS LLMS, DatePicker dateFilter) {
        LocalDate dateFilterValue = dateFilter.getValue();
        if (dateFilterValue != null) {
            LocalDate clientDate = LocalDate.parse(LLMS.getDateInstalled());
            return dateFilterValue.equals(clientDate);
        }
        return true;
    }

    private List<LLMS> getLLMs(String url) {
        try {
            return LLMLinkScraper.scrapeLLMLinks(url);
        } catch (IOException e) {
            Notification.show("Failed to fetch LLM links", 3000, Notification.Position.MIDDLE);
            return new ArrayList<>();
        }
    }

    private void addSourceButtons() {
        Button ollamaButton = new Button("Ollama");
        ollamaButton.addClickListener(event -> {
            List<LLMS> llms = getLLMs("https://ollama.com/models");
            gridListDataView = grid.setItems(llms);
        });

        Button huggingFaceButton = new Button("Hugging Face");
        huggingFaceButton.addClickListener(event -> {
            List<LLMS> llms = getLLMs("https://huggingface.co/models");
            gridListDataView = grid.setItems(llms);
        });

        add(ollamaButton, huggingFaceButton);
    }
}
