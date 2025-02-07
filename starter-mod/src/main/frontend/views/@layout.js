import { jsx as _jsx, Fragment as _Fragment, jsxs as _jsxs } from "react/jsx-runtime";
import { createMenuItems, useViewConfig } from '@vaadin/hilla-file-router/runtime.js';
import { effect, signal } from '@vaadin/hilla-react-signals';
import { AppLayout, DrawerToggle, Icon, ProgressBar, SideNav, SideNavItem } from '@vaadin/react-components';
import { Suspense, useEffect } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router';
import MainMenubar from './components/main-menubar';
// export const config: ViewConfig = { menu: { order: 1, icon: 'line-awesome/svg/home-solid.svg' , title: '' } };
// const documentTitleSignal = signal('');
// effect(() => {
//   document.title = documentTitleSignal.value;
// });
// // Publish for Vaadin to use
// (window as any).Vaadin.documentTitleSignal = documentTitleSignal;
const initializeConfigAndTitleSignal = () => {
    // Configuration for the view
    const config = { menu: { order: 1, icon: 'line-awesome/svg/home-solid.svg', title: '' } };
    // Signal for document title
    const documentTitleSignal = signal('');
    effect(() => {
        document.title = documentTitleSignal.value;
    });
    // Publish for Vaadin to use
    window.Vaadin.documentTitleSignal = documentTitleSignal;
    { /**
     * Return the configuration and the signal for document title to the router(routes.tsx) to use.
     */
    }
    return { config, documentTitleSignal };
};
// Call the function to initialize
export const { config, documentTitleSignal } = initializeConfigAndTitleSignal();
const MainLayout = () => {
    const currentTitle = useViewConfig()?.title;
    const navigate = useNavigate();
    const location = useLocation();
    useEffect(() => {
        if (currentTitle) {
            documentTitleSignal.value = currentTitle;
        }
    }, [currentTitle]);
    // Filter the menu items to include only the desired view components
    const menuItems = createMenuItems().filter(({ to }) => ['/chat-client',
        '/clone-model',
        '/content-gallery',
        '/directories-files',
        '/embedding-settings',
        '/load-unload',
        '/hello-world',
        '/main-message-list',
        '/metrics',
        '/model-destroy',
        '/model-merge',
        '/model-settings',
        '/search-models',
        '/servers',
        '/snapshots',
        '/system-settings'].includes(to));
    return (_jsxs(AppLayout, { primarySection: "drawer", children: [_jsx("div", { slot: "drawer", className: "flex flex-col justify-between h-full p-m", children: _jsxs("header", { className: "flex flex-col gap-m", children: [_jsx("span", { className: "font-semibold text-l", children: "Tools Nav" }), _jsx(SideNav, { onNavigate: ({ path }) => navigate(path), location: location, children: menuItems.map(({ to, title, icon }) => (_jsxs(SideNavItem, { path: to, children: [icon ? _jsx(Icon, { src: icon, slot: "prefix" }) : _jsx(_Fragment, {}), title] }, to))) })] }) }), _jsx(DrawerToggle, { slot: "navbar", "aria-label": "Menu toggle" }), _jsxs("div", { slot: "navbar", className: "flex justify-between items-center w-full", children: [_jsx("h1", { className: "text-l m-0", children: documentTitleSignal }), _jsx(MainMenubar, {})] }), _jsx(Suspense, { fallback: _jsx(ProgressBar, { indeterminate: true, className: "m-0" }), children: _jsx("section", { className: "view", children: _jsx(Outlet, {}) }) })] }));
};
export default MainLayout;
//# sourceMappingURL=@layout.js.map