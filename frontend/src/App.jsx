import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainLayout from './layout/MainLayout';
import Dashboard from './pages/Dashboard';
import ParkVehicle from './pages/ParkVehicle';
import UnparkVehicle from './pages/UnparkVehicle';
import ManageSlots from './pages/ManageSlots';
import History from './pages/History';
import Booking from './pages/Booking';

import { RoleProvider } from './context/RoleContext';
import RoleSelection from './pages/RoleSelection';

function App() {
  return (
    <RoleProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<RoleSelection />} />
          <Route element={<MainLayout />}>
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="park" element={<ParkVehicle />} />
            <Route path="unpark" element={<UnparkVehicle />} />
            <Route path="slots" element={<ManageSlots />} />
            <Route path="history" element={<History />} />
            <Route path="book" element={<Booking />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </RoleProvider>
  );
}

export default App;
