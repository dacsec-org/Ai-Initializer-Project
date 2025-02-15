import React, { useState, useEffect } from 'react';
import { Outlet, RouterProvider } from 'react-router-dom';
import './main-layout.scss';
import Header from './components/header';
import Footer from './components/footer';
import SideNavigation from './components/side-navigation';
import VerticalContainer from './components/vertical-container';
import Button from './components/button';
import { router } from './routes';

interface MainLayoutProps {
  children?: React.ReactNode;
}

const MainLayout: React.FC<MainLayoutProps> = ({ children }) => {
  const [isDarkTheme, setIsDarkTheme] = useState(false);

  useEffect(() => {
    const theme = isDarkTheme ? "dark" : "light";
    document.documentElement.setAttribute("theme", theme);
  }, [isDarkTheme]);

  const toggleTheme = () => {
    setIsDarkTheme((prevTheme) => !prevTheme);
  };

  return (
    <div className="main-layout">
      <Header title="My App Header">
        <div className="header-extra">
          Project-ai-initializer
          <Button onClick={toggleTheme} className="theme-toggle-button">
            <i className={`la ${isDarkTheme ? "la-sun" : "la-moon"}`}></i>
          </Button>
        </div>
      </Header>

      <div className="layout-body">
        <SideNavigation position="left" />
        <SideNavigation position="right" />
        <VerticalContainer className="content-container">
          {children}
          <Outlet />
        </VerticalContainer>
      </div>

      <Footer title="My App Footer">
        <div className="footer-extra">©</div>
      </Footer>
    </div>
  );
};

const App: React.FC = () => (
  <RouterProvider router={router}>
    <MainLayout />
  </RouterProvider>
);

export default App;
