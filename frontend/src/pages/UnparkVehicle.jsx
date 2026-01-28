import React, { useState } from 'react';
import { unparkVehicle } from '../services/api';
import './Form.css';

const UnparkVehicle = () => {
    const [vehicleNumber, setVehicleNumber] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage('');
        setError('');
        try {
            const response = await unparkVehicle({ vehicleNumber });
            setMessage(`Vehicle unparked! Fee: ${response.data.totalAmount}`);
            setVehicleNumber('');
        } catch (err) {
            setError(err.response?.data?.message || 'Error unparking vehicle');
        }
    };

    return (
        <div className="form-container">
            <h2>Unpark Vehicle</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Vehicle Number</label>
                    <input
                        type="text"
                        value={vehicleNumber}
                        onChange={(e) => setVehicleNumber(e.target.value)}
                        required
                        placeholder="Enter Vehicle Number"
                    />
                </div>
                <button type="submit" className="btn-submit">Unpark Vehicle</button>
            </form>
            {message && <p className="success-msg">{message}</p>}
            {error && <p className="error-msg">{error}</p>}
        </div>
    );
};

export default UnparkVehicle;
