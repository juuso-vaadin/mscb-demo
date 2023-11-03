package com.example.application.views.mscbdemo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@PageTitle("MSCB Demo")
@Route(value = "mscb-demo")
@RouteAlias(value = "")
@Uses(Icon.class)
public class MSCBDemoView extends Composite<VerticalLayout> {

    private List<String> items = new ArrayList<>(Arrays.asList(
        "255 - Computer software: Prepackaged software",
        "256 - Clothing/Shoe/Accessory Stores",
        "257 - Department/Specialty Retail Stores",
        "258 - Automotive aftermarket",
        "259 - EDP Services",
        "260 - Marjor Pharmaceuticals",
        "261 - Biotechnology: Commercial Physical & Biological Research",
        "262 - Investment Bankers/Brokers/Service"));
    public MSCBDemoView() {
        getContent().setPadding(false);
        getContent().setSpacing(false);

        MultiSelectComboBox<String> multiSelectComboBox = new MultiSelectComboBox();
        multiSelectComboBox.setLabel("Default");
        multiSelectComboBox.getStyle().set("--vaadin-multi-select-combo-box-overlay-width", "500px");
        multiSelectComboBox.setGroupSelectedItems(true);
        multiSelectComboBox.setAllowCustomValue(true);
        multiSelectComboBox.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            items.add(customValue);
            multiSelectComboBox.setItems(items);
            multiSelectComboBox.setValue(customValue);
        });
        multiSelectComboBox.setItems(items);

        CheckboxGroup<String> mode = new CheckboxGroup<>();
        mode.setLabel("Enable MSCB feature");
        mode.setItems("All chips visible", "Group selected at top");
        mode.addValueChangeListener(e -> {
            Set<String> selectedOptions = e.getValue();
            if (selectedOptions.contains("All chips visible")) {
                multiSelectComboBox.setAllChipsVisible(true);
            } else {
                multiSelectComboBox.setAllChipsVisible(false);
            }

            if (selectedOptions.contains("Group selected at top")) {
                multiSelectComboBox.setGroupSelectedItems(true);
            } else {
                multiSelectComboBox.setGroupSelectedItems(false);
            }
        });

        TextField fieldWidth = new TextField("Field width");
        fieldWidth.setPlaceholder("auto");
        fieldWidth.addValueChangeListener(e -> {
            multiSelectComboBox.setWidth(e.getValue());
        });

        TextField overlayWidth = new TextField("Overlay width");
        overlayWidth.setValue("500px");
        overlayWidth.addValueChangeListener(e -> {
            multiSelectComboBox.getStyle().set("--vaadin-multi-select-combo-box-overlay-width", e.getValue());
        });

        TextField inputMinWidth = new TextField("Input min width");
        inputMinWidth.setHelperText("Width of input within the ComboBox");
        inputMinWidth.setPlaceholder("4em");
        inputMinWidth.addValueChangeListener(e -> {
            multiSelectComboBox.getStyle().set("--vaadin-multi-select-combo-box-input-min-width", e.getValue());
        });

        TextField chipTargetWidth = new TextField("Chip target width");
        chipTargetWidth.setHelperText("Used in calculating max width of chip");
        chipTargetWidth.setPlaceholder("50px");
        chipTargetWidth.addValueChangeListener(e -> {
            multiSelectComboBox.getStyle().set("--vaadin-multi-select-combo-box-chip-min-width", e.getValue());
        });

        HorizontalLayout settings = new HorizontalLayout(mode, fieldWidth, overlayWidth, inputMinWidth, chipTargetWidth);
        settings.setWidthFull();
        settings.setPadding(true);
        settings.addClassName(LumoUtility.Background.CONTRAST_5);

        Div content = new Div(multiSelectComboBox);
        content.addClassName(LumoUtility.Padding.MEDIUM);

        getContent().add(settings, content);
    }
}
