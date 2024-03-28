import MainPage from './components/main-page/MainPage';
import Login from './components/login/Login';
import NewSimulator from './components/new-simulator/NewSimulator';
import ShowSimulators from './components/show-simulators/ShowSimulators';
import TechCheck from './components/tech-check/TechCheck';

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

    return (
        <div className="App">
            {/* <Login /> */}
            {/* <MainPage></MainPage> */}
            {/* <NewSimulator></NewSimulator> */}
            <ShowSimulators simulators={simulators}/>
            {/* <TechCheck simulatorsTechInfo={simulatorsTechInfo} dateReport={new Date().toLocaleDateString()}/> */}
        </div>
    );
}

export default App;
