package net.dacss.views;

import net.dacss.components.MainMenuBar;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.hilla.BrowserCallable;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.List;

import static com.vaadin.flow.server.menu.MenuConfiguration.getMenuEntries;

/**
 * The main view is a top-level placeholder for other views.
 */
@BrowserCallable
@Route(value = "")
@PageTitle("The Ai Initializer Project")
@RouteAlias(value = "home")
@Menu(order = 1, icon = LineAwesomeIconUrl.HOME_SOLID)
public class MainView extends AppLayout implements RouterLayout {

    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
        createFooter();
    }

    private void addHeaderContent() {
        Header header = new Header();
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");
        header.add(toggle, viewTitle);

        MainMenuBar mainMenuBar = new MainMenuBar();
        header.add(mainMenuBar);

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        addToNavbar(true, toggle, mainMenuBar);

    }

    private void addDrawerContent() {
        Span appName = new Span("My Chat AI");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller);
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        List<MenuEntry> menuEntries = getMenuEntries();
        menuEntries.forEach(entry -> {
            if (entry.icon() != null) {
                nav.addItem(
                        new SideNavItem(entry.title(), entry.path(),
                                new SvgIcon(entry.icon())));
            } else {
                nav.addItem(
                        new SideNavItem(entry.title(), entry.path()));
            }
        });

        return nav;
    }

    private void createFooter() {
        Footer footerLayout = new Footer();
        footerLayout.add("© 2024 MyChAi");
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        return MenuConfiguration.getPageHeader(getContent()).orElse("");
    }
}
