//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.html.Span;
//import com.vaadin.flow.theme.lumo.LumoUtility;
//
//public CardView(String text, String url) {
//    addClassNames(LumoUtility.Background.CONTRAST_5, LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.AlignItems.START, LumoUtility.Padding.MEDIUM,
//            LumoUtility.BorderRadius.LARGE);
//
//    Div div = new Div();
//    div.addClassNames(Background.CONTRAST, Display.FLEX, AlignItems.CENTER, JustifyContent.CENTER,
//            Margin.Bottom.MEDIUM, Overflow.HIDDEN, BorderRadius.MEDIUM, Width.FULL);
//    div.setHeight("160px");
//
//    Image image = new Image();
//    image.setWidth("100%");
//    image.setSrc(url);
//    image.setAlt(text);
//
//    div.add(image);
//
//    Span header = new Span();
//    header.addClassNames(FontSize.XLARGE, FontWeight.SEMIBOLD);
//    header.setText("Title");
//
//    Span subtitle = new Span();
//    subtitle.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
//    subtitle.setText("Card subtitle");
//
//    Paragraph description = new Paragraph(
//            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.");
//    description.addClassName(Margin.Vertical.MEDIUM);
//
//    Span badge = new Span();
//    badge.getElement().setAttribute("theme", "badge");
//    badge.setText("Label");
//
//    add(div, header, subtitle, description, badge);
//}
