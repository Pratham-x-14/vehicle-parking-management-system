import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import './MainLayout.css';

const MainLayout = () => {
    return (
        <div className="app-container">
            <header className="app-header">
                <div className="header-content">
                    <h1>Vehicle Parking System</h1>
                    <nav>
                        <Link to="/">Dashboard</Link>
                        <Link to="/park">Park Vehicle</Link>
                        <Link to="/unpark">Unpark Vehicle</Link>
                        <Link to="/slots">Manage Slots</Link>
                        <Link to="/history">History</Link>
                    </nav>
                </div>
            </header>
            <main className="app-main">
                <Outlet />
            </main>
        </div>
    );
};

export default MainLayout;
