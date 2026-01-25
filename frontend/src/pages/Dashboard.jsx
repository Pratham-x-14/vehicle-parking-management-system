import React, { useEffect, useState } from 'react';
import { getAvailableSlots } from '../services/api';
import './Dashboard.css';

const Dashboard = () => {
    const [slots, setSlots] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchSlots();
    }, []);

    const fetchSlots = async () => {
        try {
            const response = await getAvailableSlots();
            setSlots(response.data);
        } catch (error) {
            console.error("Error fetching slots", error);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="dashboard-container">
            <h2>Available Parking Slots</h2>
            <div className="actions" style={{ marginBottom: '1rem' }}>
                <button className="btn-refresh" onClick={fetchSlots}>Refresh</button>
            </div>
            {loading ? <p>Loading slots...</p> : (
                <div className="slots-grid">
                    {slots.length === 0 ? <p>No slots available.</p> : slots.map(slot => (
                        <div key={slot.id} className={`slot-card ${slot.available ? 'available' : 'occupied'}`}>
                            <h3>{slot.type}</h3>
                            <p>Slot ID: {slot.id}</p>
                            <p className="status">{slot.available ? 'Available' : 'Occupied'}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Dashboard;
