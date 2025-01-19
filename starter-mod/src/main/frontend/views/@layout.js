import { jsx as _jsx, Fragment as _Fragment, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component, Suspense } from 'react';
import { createMenuItems, useViewConfig } from '@vaadin/hilla-file-router/runtime.js';
import { effect, signal } from '@vaadin/hilla-react-signals';
import { AppLayout, DrawerToggle, Icon, SideNav, SideNavItem } from '@vaadin/react-components';
import { Outlet, useLocation, useNavigate } from 'react-router';
import MainMenubar from './main-menubar';
import MainMessageInput from './main-message-input';
const documentTitleSignal = signal('');
effect(() => {
    document.title = documentTitleSignal.value;
});
// Publish for Vaadin to use
window.Vaadin.documentTitleSignal = documentTitleSignal;
/**
 * {@link MainLayout}
 * <p>
 *   This is the main layout component that renders the main layout of the application.
 * </p>
 */
class MainLayout extends Component {
    constructor() {
        super(...arguments);
        this.currentTitle = useViewConfig()?.title;
        this.navigate = useNavigate();
        this.location = useLocation();
    }
    /**
     * {@link componentDidMount}
     * <p>
     *   This method sets the document title to the current title when the component is mounted.
     * </p>
     */
    componentDidMount() {
        if (this.currentTitle) {
            documentTitleSignal.value = this.currentTitle;
        }
    }
    /**
     * {@link render}
     * <p>
     *   This method renders the main layout of the application.
     * </p>
     */
    render() {
        return (_jsxs(AppLayout, { primarySection: "drawer", children: [_jsx("div", { slot: "drawer", className: "flex flex-col justify-between h-full p-m", children: _jsxs("header", { className: "flex flex-col gap-m", children: [_jsx("span", { className: "font-semibold text-l", children: "The Ai Initializer Project" }), _jsx(SideNav, { onNavigate: ({ path }) => this.navigate(path), location: this.location, children: createMenuItems().map(({ to, title, icon }) => (_jsxs(SideNavItem, { path: to, children: [icon ? _jsx(Icon, { src: icon, slot: "prefix" }) : _jsx(_Fragment, {}), title] }, to))) })] }) }), _jsx(DrawerToggle, { slot: "navbar", "aria-label": "Menu toggle" }), _jsxs("div", { slot: "navbar", className: "flex justify-between items-center w-full", children: [_jsx("h1", { className: "text-l m-0", children: documentTitleSignal }), _jsx(MainMenubar, {})] }), _jsx(Suspense, { children: _jsx(Outlet, {}) }), _jsx("footer", { children: _jsx(MainMessageInput, {}) })] }));
    }
}
export default MainLayout;
//# sourceMappingURL=@layout.js.map