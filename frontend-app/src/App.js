import MainPage from './components/main-page/MainPage';
import Login from './components/login/Login';
import NewSimulator from './components/new-simulator/NewSimulator';
import ShowSimulators from './components/show-simulators/ShowSimulators';
import TechCheck from './components/tech-check/TechCheck';
import StatusCheck from "./components/status-check/StatusCheck";
import StatisticsReport from "./components/statistics-report/StatisticsReport";

function App() {

    const simulators = [
        {
            id: "1235123",
            model: "SIM1",
            type: "Симулятор военной авиации",
            simulatorName: "V-76",
            productionDate: "27.12.2003",
            commissioningDate: "22.01.2004",
            techFrequrency: 12,
            components: [
                { id: "1234", name: "MUTH0" },
                { id: "4356", name: "MUTL2" }
            ]
        },
        {
            id: "2225345",
            model: "EMU43",
            type: "Симулятор гражданской авиации",
            simulatorName: "Boing-98",
            productionDate: "09.11.2001",
            commissioningDate: "21.04.2002",
            techFrequrency: 36,
            components: [
                { id: "1231", name: "YUA21" }
            ]
        },
        {
            id: "1324567",
            model: "ENG21",
            type: "Симулятор гражданской авиации",
            simulatorName: "Hork-22",
            productionDate: "01.12.2006",
            commissioningDate: "21.12.2006",
            techFrequrency: 24,
            components: [
                { id: "8790", name: "LMO00" },
                { id: "1456", name: "ORH12" },
                { id: "0978", name: "PLL01" }
            ]
        }
    ]

    const simulatorsTechInfo = [
        {
            id: "1235123",
            isTechCheckRequired: true,
            description: "неисправны некоторые компоненты!",
            lastTechCheckDate: "14.05.2020",
            invalidComponents: [
                { id: "1234", status: "Неисправен", errorDescription: "нарушена система охлаждения" },
                { id: "4356", status: "Сбой", errorDescription: "неизвестная внутренняя ошибка"  }
            ]
        },
        {
            id: "2225345",
            isTechCheckRequired: false
        },
        {
            id: "1324567",
            isTechCheckRequired: true,
            description: "технический осмотр проводился слишком давно!",
            lastTechCheckDate: "10.10.2017"
        }
    ]

    const simulatorsStatuses = [
        {
            id: "1235123",
            isActive: true,
            isOccupied: true,
            components: [
                { id: "1234", responseTime: 5, temperature: 45, power: 1000, voltage: 220 },
                { id: "4356", responseTime: 4, temperature: 23, power: 880, voltage: 198 },
            ]
        },
        {
            id: "2225345",
            isActive: true,
            isOccupied: false,
            components: [
                { id: "1231", responseTime: 10, temperature: 67, power: 1003, voltage: 221 }
            ]
        },
        {
            id: "1324567",
            isActive: false,
            isOccupied: false
        }
    ]

    const simulatorsStatistics = [
        {
            id: "1235123",
            components: [
                { id: "1234", responsesTime: [5, 4, 2, 4, 5, 3], temperatures: [45, 33, 45, 32, 65, 67], powers: [1000, 800, 900, 1000, 1200, 1200], voltages: [220, 220, 219, 207, 206, 204] },
                { id: "4356", responsesTime: [4, 2, 4, 4], temperatures: [23, 34, 23, 23], powers: [880, 700, 800, 670], voltages: [198, 200, 220, 200] },
            ]
        },
        {
            id: "2225345",
            components: [
                { id: "1231", responsesTime: [10, 12, 12], temperatures: [67, 66, 65], powers: [1003, 1000, 1001], voltages: [221, 220, 222] }
            ]
        },
        {
            id: "1324567",
            components: [
                { id: "8790" },
                { id: "1456" },
                { id: "0978" }
            ]
        }
    ]

    return (
        <div className="App">
             {/*<Login />*/}
             {/*<MainPage></MainPage>*/}
            {/* <NewSimulator></NewSimulator>*/}
            {/*<ShowSimulators simulators={simulators}/>*/}
            {/*<TechCheck simulatorsTechInfo={simulatorsTechInfo} dateReport={new Date().toLocaleDateString()}/>*/}
            {/*<StatusCheck dateReport={new Date().toLocaleDateString()} simulatorsStatuses={simulatorsStatuses}></StatusCheck>*/}
            <StatisticsReport startDate={"19.03.2024"} endDate={"29.03.2024"} simulatorStatistics={simulatorsStatistics} />
        </div>
    );
}

export default App;
