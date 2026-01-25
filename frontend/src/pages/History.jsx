import React, { useEffect, useState } from 'react';
import { getAllTickets } from '../services/api';
import './History.css';

const History = () => {
    const [tickets, setTickets] = useState([]);

    useEffect(() => {
        fetchHistory();
    }, []);

    const fetchHistory = async () => {
        try {
            const response = await getAllTickets();
            setTickets(response.data);
        } catch (error) {
            console.error("Error fetching history", error);
        }
    };

    return (
        <div className="history-container">
            <h2>Parking History</h2>
            <table className="history-table">
                <thead>
                    <tr>
                        <th>Ticket ID</th>
                        <th>Vehicle No</th>
                        <th>Slot ID</th>
                        <th>Entry Time</th>
                        <th>Exit Time</th>
                        <th>Fee</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {tickets.map(ticket => (
                        <tr key={ticket.id}>
                            <td>{ticket.id}</td>
                            <td>{ticket.vehicleNumber}</td>
                            <td>{ticket.slotId}</td>
                            <td>{new Date(ticket.entryTime).toLocaleString()}</td>
                            <td>{ticket.exitTime ? new Date(ticket.exitTime).toLocaleString() : '-'}</td>
                            <td>{ticket.parkingFee !== null ? ticket.parkingFee : '-'}</td>
                            <td>{ticket.exitTime ? 'Completed' : 'Active'}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default History;
