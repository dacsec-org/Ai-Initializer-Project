import { createMenuItems, useViewConfig } from '@vaadin/hilla-file-router/runtime.js';
import { effect, signal } from '@vaadin/hilla-react-signals';
import { AppLayout, DrawerToggle, Icon, SideNav, SideNavItem } from '@vaadin/react-components';
import { Suspense, useEffect } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router';
import MainMenubar from './components/main-menubar';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

const documentTitleSignal = signal('');
effect(() => {
  document.title = documentTitleSignal.value;
});

// Publish for Vaadin to use
(window as any).Vaadin.documentTitleSignal = documentTitleSignal;

export const config: ViewConfig = {
  menu: { order: 0, icon: 'line-awesome/svg/home.svg' },
  title: 'Home',
};

const MainLayout: React.FC = () => {
  const currentTitle = useViewConfig()?.title;
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (currentTitle) {
      documentTitleSignal.value = currentTitle;
    }
  }, [currentTitle]);

  // Filter the menu items to include only the desired view components
  const menuItems = createMenuItems().filter(({ to }) =>
    ['/chat-client'
      , '/clone-model'
      , '/content-gallery'
      , '/directories-files'
      , '/embedding-settings'
      , '/load-unload'
      , '/hello-world'
      , '/main-message-list'
      , '/metrics'
      , '/model-destroy'
      , '/model-merge'
      , '/model-settings'
      , '/search-models'
      , '/servers'
      , '/snapshots'
      , '/system-settings'].includes(to)
  );

  return (
    <AppLayout primarySection="drawer">
      <div slot="drawer" className="flex flex-col justify-between h-full p-m">
        <header className="flex flex-col gap-m">
          <span className="font-semibold text-l">The Ai Initializer Project</span>
          <SideNav onNavigate={({ path }) => navigate(path!)} location={location}>
            {menuItems.map(({ to, title, icon }) => (
              <SideNavItem path={to} key={to}>
                {icon ? <Icon src={icon} slot="prefix"></Icon> : <></>}
                {title}
              </SideNavItem>
            ))}
          </SideNav>
        </header>
      </div>

      <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
      <div slot="navbar" className="flex justify-between items-center w-full">
        <h1 className="text-l m-0">{documentTitleSignal}</h1>
        <MainMenubar />
      </div>

      <Suspense>
        <Outlet />
      </Suspense>
    </AppLayout>
  );
};

export default MainLayout;
