import React, { useState } from 'react';
import { bookSlot } from '../services/api';
import './Form.css';

const Booking = () => {
    const [formData, setFormData] = useState({
        vehicleNumber: '',
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
            const response = await bookSlot(formData);
            setMessage(`Slot booked successfully! Booking ID: ${response.data.bookingId}, Slot: ${response.data.slotNumber}`);
            setFormData({ vehicleNumber: '', vehicleType: 'CAR' });
        } catch (err) {
            setError(err.response?.data?.message || 'Error booking slot');
        }
    };

    return (
        <div className="form-container">
            <h2>Book Parking Slot</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Vehicle Number</label>
                    <input
                        type="text"
                        name="vehicleNumber"
                        value={formData.vehicleNumber}
                        onChange={handleChange}
                        required
                        placeholder="Enter Vehicle Number"
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
                <button type="submit" className="btn-submit">Book Slot</button>
            </form>
            {message && <p className="success-msg">{message}</p>}
            {error && <p className="error-msg">{error}</p>}
        </div>
    );
};

export default Booking;
