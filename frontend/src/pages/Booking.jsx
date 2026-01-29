import React, { useState } from 'react';
import { bookSlot } from '../services/api';
import './Form.css';

const Booking = () => {
    const [step, setStep] = useState(1); // 1: Details, 2: Payment
    const [formData, setFormData] = useState({
        vehicleNumber: '',
        vehicleType: 'CAR',
        amount: 50.0 // Default amount
    });
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value,
            amount: name === 'vehicleType' ? getRate(value) : prev.amount
        }));
    };

    const getRate = (type) => {
        switch (type) {
            case 'BIKE': return 20.0;
            case 'CAR': return 50.0;
            case 'TRUCK': return 100.0;
            default: return 50.0;
        }
    }

    const handleProceedToPay = (e) => {
        e.preventDefault();
        setStep(2);
    };

    const handlePayment = async () => {
        setIsLoading(true);
        // Simulate Payment Gateway Delay
        setTimeout(async () => {
            try {
                const bookingData = {
                    ...formData,
                    paymentStatus: 'PAID',
                    transactionId: 'TXN-' + Date.now()
                };

                const response = await bookSlot(bookingData);
                setMessage(`Payment Successful! Slot Booked. ID: ${response.data.bookingId}, Slot: ${response.data.slotNumber}`);
                setFormData({ vehicleNumber: '', vehicleType: 'CAR', amount: 50.0 });
                setStep(1);
            } catch (err) {
                setError(err.response?.data?.message || 'Error booking slot');
            } finally {
                setIsLoading(false);
            }
        }, 2000); // 2 second delay
    };

    return (
        <div className="form-container">
            <h2>{step === 1 ? 'Book Parking Slot' : 'Confirm & Pay'}</h2>

            {step === 1 && (
                <form onSubmit={handleProceedToPay}>
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
                            <option value="CAR">Car (₹50)</option>
                            <option value="BIKE">Bike (₹20)</option>
                            <option value="TRUCK">Truck (₹100)</option>
                        </select>
                    </div>
                    <button type="submit" className="btn-submit">Proceed to Pay ₹{formData.amount}</button>
                </form>
            )}

            {step === 2 && (
                <div style={{ textAlign: 'center' }}>
                    <p><strong>Vehicle:</strong> {formData.vehicleNumber}</p>
                    <p><strong>Type:</strong> {formData.vehicleType}</p>
                    <p style={{ fontSize: '1.2rem', margin: '20px 0' }}><strong>Amount to Pay: ₹{formData.amount}</strong></p>

                    {isLoading ? (
                        <p>Processing Payment...</p>
                    ) : (
                        <div style={{ display: 'flex', gap: '10px', justifyContent: 'center' }}>
                            <button onClick={() => setStep(1)} style={{ padding: '10px', cursor: 'pointer' }}>Cancel</button>
                            <button onClick={handlePayment} className="btn-submit" style={{ backgroundColor: '#28a745' }}>Pay Now</button>
                        </div>
                    )}
                </div>
            )}

            {message && <p className="success-msg">{message}</p>}
            {error && <p className="error-msg">{error}</p>}
        </div>
    );
};

export default Booking;
