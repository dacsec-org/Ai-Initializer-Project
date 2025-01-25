import { createMenuItems, useViewConfig } from '@vaadin/hilla-file-router/runtime.js';
import { effect, signal } from '@vaadin/hilla-react-signals';
import { AppLayout, DrawerToggle, Icon, SideNav, SideNavItem } from '@vaadin/react-components';
import { Component, Suspense, useEffect } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router';
import MainMenubar from './components/main-menubar';
import MainMessageInput from './components/main-message-input';
import { MessageInputSubmitEvent } from '@vaadin/react-components/MessageInput.js';

const documentTitleSignal = signal('');
effect(() => {
  document.title = documentTitleSignal.value;
});

// Publish for Vaadin to use
(window as any).Vaadin.documentTitleSignal = documentTitleSignal;

/**
 * {@link MainLayout}
 * <p>
 *   This is the main layout component that renders the main layout of the application.
 * </p>
 */
class MainLayout extends Component {
  currentTitle = useViewConfig()?.title;
  navigate = useNavigate();
  location = useLocation();

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

  handleSubmit = (event: MessageInputSubmitEvent) => {
    event.preventDefault();
    //fixme: asap! Handle the submit action here
  };

  render() {
    return (
      <AppLayout primarySection="drawer">
        <div slot="drawer" className="flex flex-col justify-between h-full p-m">
          <header className="flex flex-col gap-m">
            <span className="font-semibold text-l">The Ai Initializer Project</span>
            <SideNav onNavigate={({ path }) => this.navigate(path!)} location={this.location}>
              {createMenuItems().map(({ to, title, icon }) => (
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

        <footer>
          <MainMessageInput onSubmit={this.handleSubmit} />
        </footer>
      </AppLayout>
    );
  }
}

export default MainLayout;
