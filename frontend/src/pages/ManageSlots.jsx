import React, { useEffect, useState } from 'react';
import { addSlot, removeSlot, getAllSlots } from '../services/api';
import './Dashboard.css';
import './Form.css';

const ManageSlots = () => {
    const [slots, setSlots] = useState([]);
    const [newSlotNumber, setNewSlotNumber] = useState('');
    const [newSlotType, setNewSlotType] = useState('CAR');
    const [message, setMessage] = useState('');

    useEffect(() => {
        fetchSlots();
    }, []);

    const fetchSlots = async () => {
        try {
            const response = await getAllSlots();
            setSlots(response.data);
        } catch (error) {
            console.error("Error fetching slots", error);
        }
    };

    const handleAddSlot = async (e) => {
        e.preventDefault();
        try {
            await addSlot({ slotNumber: newSlotNumber, type: newSlotType });
            setMessage('Slot added successfully');
            setNewSlotNumber('');
            fetchSlots();
        } catch (error) {
            console.error("Error adding slot", error);
            setMessage('Failed to add slot');
        }
    };

    const handleRemoveSlot = async (id) => {
        if (!window.confirm("Are you sure you want to remove this slot?")) return;
        try {
            await removeSlot(id);
            setMessage('Slot removed successfully');
            fetchSlots();
        } catch (error) {
            console.error("Error removing slot", error);
            setMessage('Failed to remove slot');
        }
    };

    return (
        <div className="dashboard-container">
            <h2>Manage Parking Slots</h2>

            <div className="form-container" style={{ maxWidth: '100%', marginBottom: '20px' }}>
                <h3>Add New Slot</h3>
                <form onSubmit={handleAddSlot} style={{ display: 'flex', gap: '10px', alignItems: 'flex-end' }}>
                    <div className="form-group" style={{ marginBottom: 0, flexGrow: 1 }}>
                        <label>Slot Number</label>
                        <input
                            type="text"
                            value={newSlotNumber}
                            onChange={(e) => setNewSlotNumber(e.target.value)}
                            required
                            placeholder="e.g. A-1"
                        />
                    </div>
                    <div className="form-group" style={{ marginBottom: 0, flexGrow: 1 }}>
                        <label>Slot Type</label>
                        <select value={newSlotType} onChange={(e) => setNewSlotType(e.target.value)}>
                            <option value="CAR">Car</option>
                            <option value="BIKE">Bike</option>
                            <option value="TRUCK">Truck</option>
                        </select>
                    </div>
                    <button type="submit" className="btn-submit" style={{ width: 'auto' }}>Add Slot</button>
                </form>
                {message && <p className="success-msg" style={{ marginTop: '10px' }}>{message}</p>}
            </div>

            <div className="slots-grid">
                {slots.map(slot => (
                    <div key={slot.id} className={`slot-card ${slot.available ? 'available' : 'occupied'}`}>
                        <h3>{slot.type}</h3>
                        <p>Slot Number: {slot.slotNumber}</p>
                        <p>{slot.available ? 'Available' : 'Occupied'}</p>
                        <button
                            className="btn-delete"
                            onClick={() => handleRemoveSlot(slot.id)}
                            style={{ marginTop: '10px', backgroundColor: '#f44336', color: 'white', border: 'none', padding: '5px 10px', borderRadius: '4px', cursor: 'pointer' }}
                        >
                            Remove
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ManageSlots;
