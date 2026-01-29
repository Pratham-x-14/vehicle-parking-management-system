import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import { useRole } from '../context/RoleContext';
import './MainLayout.css';

const MainLayout = () => {
    const { role } = useRole();

    return (
        <div className="app-container">
            <header className="app-header">
                <div className="header-content">
                    <h1>Vehicle Parking System {role === 'ADMIN' ? '(Developer)' : '(User)'}</h1>
                    <nav>
                        {role === 'ADMIN' && (
                            <>
                                <Link to="/dashboard">Dashboard</Link>
                                <Link to="/park">Park Vehicle</Link>
                                <Link to="/book">Book Slot</Link>
                                <Link to="/unpark">Unpark Vehicle</Link>
                                <Link to="/slots">Manage Slots</Link>
                                <Link to="/history">History</Link>
                            </>
                        )}
                        {role === 'USER' && (
                            <>
                                <Link to="/book">Book Slot</Link>
                                <Link to="/dashboard">Available Slots</Link>
                            </>
                        )}
                        <Link to="/" style={{ marginLeft: '20px', fontSize: '0.8em' }}>Switch Role</Link>
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
