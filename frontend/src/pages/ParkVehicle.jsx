import React, { useState } from 'react';
import { parkVehicle } from '../services/api';
import './Form.css';

const ParkVehicle = () => {
    const [formData, setFormData] = useState({
        vehicleNumber: '',
        ownerName: '',
        vehicleType: 'CAR'
    });
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage('');
        setError('');
        try {
            const response = await parkVehicle(formData);
            setMessage(`Vehicle parked successfully! Ticket ID: ${response.data.ticketId}, Slot: ${response.data.slotNumber}`);
            setFormData({ vehicleNumber: '', ownerName: '', vehicleType: 'CAR' });
        } catch (err) {
            setError(err.response?.data?.message || 'Error parking vehicle');
        }
    };

    return (
        <div className="form-container">
            <h2>Park Vehicle</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Vehicle Number</label>
                    <input
                        type="text"
                        name="vehicleNumber"
                        value={formData.vehicleNumber}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Owner Name</label>
                    <input
                        type="text"
                        name="ownerName"
                        value={formData.ownerName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Vehicle Type</label>
                    <select name="vehicleType" value={formData.vehicleType} onChange={handleChange}>
                        <option value="CAR">Car</option>
                        <option value="BIKE">Bike</option>
                        <option value="TRUCK">Truck</option>
                    </select>
                </div>
                <button type="submit" className="btn-submit">Park Vehicle</button>
            </form>
            {message && <p className="success-msg">{message}</p>}
            {error && <p className="error-msg">{error}</p>}
        </div>
    );
};

export default ParkVehicle;
