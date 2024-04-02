import MainPage from './components/main-page/MainPage';
import Login from './components/login/Login';
import NewSimulator from './components/new-simulator/NewSimulator';
import ShowSimulators from './components/show-simulators/ShowSimulators';
import TechCheck from './components/tech-check/TechCheck';
import StatusCheck from "./components/status-check/StatusCheck";
import StatisticsReport from "./components/statistics-report/StatisticsReport";
import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {

    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="*" element={<MainPage />} />
                    <Route path="login" element={<Login />} />
                    <Route path="simulator/new" element={<NewSimulator />} />
                    <Route path="simulator" element={<ShowSimulators />} />
                    <Route path="simulator/tech" element={<TechCheck />} />
                    <Route path="simulator/status" element={<StatusCheck />} />
                    <Route path="simulator/report" element={<StatisticsReport />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
