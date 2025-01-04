//package net.dacss.views.galleries;
//
//import com.vaadin.flow.component.HasComponents;
//import com.vaadin.flow.component.HasStyle;
//import com.vaadin.flow.component.html.H2;
//import com.vaadin.flow.component.html.Main;
//import com.vaadin.flow.component.html.OrderedList;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.select.Select;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.theme.lumo.LumoUtility;
//import net.dacss.views.MainView;
//
//@PageTitle("Image Gallery")
//@Route(value = "image-gallery", layout = MainView.class)
//public class ImageView extends Main implements HasComponents, HasStyle {
//
//    private OrderedList imageContainer;
//
//    public ImageView() {
//        constructUI();
//
//        imageContainer.add(new CardView("Snow mountains under stars",
//                "https://images.unsplash.com/photo-1519681393784-d120267933ba?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
//        imageContainer.add(new CardView("Snow covered mountain",
//                "https://images.unsplash.com/photo-1512273222628-4daea6e55abb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
//        imageContainer.add(new CardView("River between mountains",
//                "https://images.unsplash.com/photo-1536048810607-3dc7f86981cb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=375&q=80"));
//        imageContainer.add(new CardView("Milky way on mountains",
//                "https://images.unsplash.com/photo-1515705576963-95cad62945b6?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=750&q=80"));
//        imageContainer.add(new CardView("Mountain with fog",
//                "https://images.unsplash.com/photo-1513147122760-ad1d5bf68cdb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80"));
//        imageContainer.add(new CardView("Mountain at night",
//                "https://images.unsplash.com/photo-1562832135-14a35d25edef?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=815&q=80"));
//
//    }
//
//    private void constructUI() {
//        addClassNames("image-gallery-view");
//        addClassNames(LumoUtility.MaxWidth.SCREEN_LARGE
//                , LumoUtility.Margin.Horizontal.AUTO
//                , LumoUtility.Padding.Bottom.LARGE
//                , LumoUtility.Padding.Horizontal.LARGE);
//
//        HorizontalLayout container = new HorizontalLayout();
//        container.addClassNames(LumoUtility.AlignItems.CENTER
//                , LumoUtility.JustifyContent.BETWEEN);
//
//        VerticalLayout headerContainer = new VerticalLayout();
//        H2 header = new H2("Beautiful photos");
//        header.addClassNames(LumoUtility.Margin.Bottom.NONE
//                , LumoUtility.Margin.Top.XLARGE
//                , LumoUtility.FontSize.XXXLARGE);
//
//        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
//        description.addClassNames(LumoUtility.Margin.Bottom.XLARGE
//                , LumoUtility.Margin.Top.NONE
//                , LumoUtility.TextColor.SECONDARY);
//
//        headerContainer.add(header, description);
//
//        Select<String> sortBy = new Select<>();
//        sortBy.setLabel("Sort by");
//        sortBy.setItems("Popularity"
//                , "Newest first"
//                , "Oldest first");
//        sortBy.setValue("Popularity");
//
//        imageContainer = new OrderedList();
//        imageContainer.addClassNames(LumoUtility.Gap.MEDIUM
//                , LumoUtility.Display.GRID
//                , LumoUtility.ListStyleType.NONE
//                , LumoUtility.Margin.NONE
//                , LumoUtility.Padding.NONE);
//
//        container.add(headerContainer, sortBy);
//        add(container, imageContainer);
//
//    }
//}
