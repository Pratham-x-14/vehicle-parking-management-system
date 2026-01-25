import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainLayout from './layout/MainLayout';
import Dashboard from './pages/Dashboard';
import ParkVehicle from './pages/ParkVehicle';
import UnparkVehicle from './pages/UnparkVehicle';
import ManageSlots from './pages/ManageSlots';
import History from './pages/History';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="park" element={<ParkVehicle />} />
          <Route path="unpark" element={<UnparkVehicle />} />
          <Route path="slots" element={<ManageSlots />} />
          <Route path="history" element={<History />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
