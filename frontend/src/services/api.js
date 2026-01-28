import axios from 'axios';

const api = axios.create({
    baseURL: '/api'
});

export const getAvailableSlots = () => api.get('/parking/available-slots');
export const parkVehicle = (data) => api.post('/parking/park', data);
export const unparkVehicle = (data) => api.post('/parking/unpark', data);
export const addSlot = (data) => api.post('/slots', data);
export const removeSlot = (id) => api.delete(`/slots/${id}`);
export const getAllSlots = () => api.get('/slots');
export const bookSlot = (data) => api.post('/parking/book', data);
export const getAllTickets = () => api.get('/parking/history');

export default api;
