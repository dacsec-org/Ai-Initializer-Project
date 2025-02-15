import React, { useState } from 'react';
import './side-navigation.scss';
import { NavLink } from 'react-router-dom';
import Grid from '../pages/grid';

interface SideNavigationProps {
  position: 'left' | 'right';
  showGrid?: boolean;
  gridProps?: {
    columns?: number;
    gap?: string;
    className?: string;
  };
}

const SideNavigation: React.FC<SideNavigationProps> = ({ position, showGrid = false, gridProps }) => {
  const [isOpen, setIsOpen] = useState(false);

  const pages = [
    { name: 'Grid', component: '/grid' },
    { name: 'Clone Model', component: '/clone-model' },
    { name: 'Content Gallery', component: '/content-gallery' },
    { name: 'Hello World', component: '/hello-world' },
    { name: 'Models', component: '/models' }
  ];

  return (
    <div>
      <button onClick={() => setIsOpen(!isOpen)}>Toggle Navigation ({position})</button>
      <div
        className={`side-nav side-nav-${position} ${isOpen ? 'active' : ''}`}
        style={{ maxWidth: isOpen ? '300px' : '0' }}
      >
        <ul className="side-nav-list">
          {pages.map((page, index) => (
            <li key={index} className="side-nav-item">
              <NavLink to={page.component} onClick={() => setIsOpen(false)}>
                {page.name}
              </NavLink>
            </li>
          ))}
        </ul>
        {showGrid && (
          <div className="side-nav-grid">
            <Grid
              {...{
                ...gridProps,
                columns: gridProps?.columns ?? 1,
                gap: gridProps?.gap ?? '',
                className: gridProps?.className ?? '',
              }}
            />
          </div>
        )}
      </div>
    </div>
  );
};

export default SideNavigation;
