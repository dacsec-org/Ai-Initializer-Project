import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { createMenuItems, useViewConfig } from '@vaadin/hilla-file-router/runtime.js';
import { effect, signal } from '@vaadin/hilla-react-signals';
import { AppLayout, DrawerToggle, Icon, SideNav, SideNavItem } from '@vaadin/react-components';
import { Component, Suspense } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router';
import MainMenubar from './components/main-menubar';
import MainMessageInput from './components/main-message-input';

const documentTitleSignal = signal('');
effect(() => {
    document.title = documentTitleSignal.value;
});
window.Vaadin.documentTitleSignal = documentTitleSignal;

class MainLayout extends Component {
    constructor(props) {
        super(props);
        this.currentTitle = useViewConfig()?.title;
        this.navigate = useNavigate();
        this.location = useLocation();
    }

    componentDidMount() {
        if (this.currentTitle) {
            documentTitleSignal.value = this.currentTitle;
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        //fixme: asap! Handle the submit action here
    };

    render() {
        return (_jsxs(AppLayout, { primarySection: "drawer", children: [_jsxs("div", { slot: "drawer", className: "flex flex-col justify-between h-full p-m", children: [_jsxs("header", { className: "flex flex-col gap-m", children: [_jsx("span", { className: "font-semibold text-l", children: "The Ai Initializer Project" }), _jsx(SideNav, { onNavigate: ({ path }) => this.navigate(path), location: this.location, children: createMenuItems().map(({ to, title, icon }) => (_jsxs(SideNavItem, { path: to, children: [icon ? _jsx(Icon, { src: icon, slot: "prefix" }) : null, title] }, to))) })] })] }), _jsx(DrawerToggle, { slot: "navbar", "aria-label": "Menu toggle" }), _jsxs("div", { slot: "navbar", className: "flex justify-between items-center w-full", children: [_jsx("h1", { className: "text-l m-0", children: documentTitleSignal }), _jsx(MainMenubar, {})] }), _jsx(Suspense, { children: _jsx(Outlet, {}) }), _jsx("footer", { children: _jsx(MainMessageInput, { onSubmit: this.handleSubmit }) })] }));
    }
}

export default MainLayout;
//# sourceMappingURL=@layout.js.map
