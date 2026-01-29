import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useRole } from '../context/RoleContext';
import './Form.css'; // Reusing form styles for buttons

const RoleSelection = () => {
    const { setRole } = useRole();
    const navigate = useNavigate();

    const handleSelectRole = (selectedRole) => {
        setRole(selectedRole);
        if (selectedRole === 'USER') {
            navigate('/book'); // Redirect User to Booking
        } else if (selectedRole === 'ADMIN') {
            navigate('/dashboard'); // Redirect Admin to Dashboard
        }
    };

    return (
        <div className="form-container" style={{ textAlign: 'center', marginTop: '100px' }}>
            <h2>Select Your Role</h2>
            <div style={{ display: 'flex', gap: '20px', justifyContent: 'center', marginTop: '30px' }}>
                <button
                    className="btn-submit"
                    onClick={() => handleSelectRole('USER')}
                    style={{ padding: '20px 40px', fontSize: '1.2rem' }}
                >
                    Enter as User
                </button>
                <button
                    className="btn-submit"
                    onClick={() => handleSelectRole('ADMIN')}
                    style={{ padding: '20px 40px', fontSize: '1.2rem', backgroundColor: '#555' }}
                >
                    Enter as Developer
                </button>
            </div>
        </div>
    );
};

export default RoleSelection;
