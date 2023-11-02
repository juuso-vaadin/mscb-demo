package com.example.application.views.mscbdemo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.ArrayList;
import java.util.List;

@PageTitle("MSCB Demo")
@Route(value = "mscb-demo")
@RouteAlias(value = "")
@Uses(Icon.class)
public class MSCBDemoView extends Composite<VerticalLayout> {

    public MSCBDemoView() {
        MultiSelectComboBox multiSelectComboBox = new MultiSelectComboBox();
        MultiSelectComboBox multiSelectComboBox2 = new MultiSelectComboBox();
        MultiSelectComboBox multiSelectComboBox3 = new MultiSelectComboBox();
        getContent().setHeightFull();
        getContent().setWidthFull();
        multiSelectComboBox.setLabel("Default");
        setMultiSelectComboBoxSampleData(multiSelectComboBox);
        multiSelectComboBox2.setLabel("Show at least one");
        setMultiSelectComboBoxSampleData(multiSelectComboBox2);
        multiSelectComboBox3.setLabel("All items visible");
        setMultiSelectComboBoxSampleData(multiSelectComboBox3);
        getContent().add(multiSelectComboBox);
        getContent().add(multiSelectComboBox2);
        getContent().add(multiSelectComboBox3);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setMultiSelectComboBoxSampleData(MultiSelectComboBox multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
