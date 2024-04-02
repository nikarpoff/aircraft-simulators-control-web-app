import Block from "../UI/blocks/Block";
import GreenButton from "../UI/button/GreenButton";
import Simulator from "./Simulator";
import CloseButton from "../UI/button/CloseButton";
import {useNavigate} from "react-router-dom";
import {useEffect, useMemo, useState} from "react";


export default function ShowSimulators() {
    const navigate = useNavigate()

    const [simulators, setSimulators] = useState( []);

    useEffect(() => {
        getSimulators().then(res => {
            setSimulators(res);
        })
    }, []);

    const isNoSimulators = useMemo(() => {
        return typeof simulators === "undefined";
    }, [simulators])


    async function getSimulators() {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const response = await fetch("https://localhost:8443/api/v1/simulator/", options);
            return await response.json();
        } catch (error) {
            console.error('Error fetching simulators:', error);
            return [];
        }

        // let simulatorsResponse = [
        //     {
        //         id: "1235123",
        //         model: "SIM1",
        //         type: "Симулятор военной авиации",
        //         simulatorName: "V-76",
        //         productionDate: "27.12.2003",
        //         commissioningDate: "22.01.2004",
        //         techFrequrency: 12,
        //         components: [
        //             { id: "1234", name: "MUTH0" },
        //             { id: "4356", name: "MUTL2" }
        //         ]
        //     },
        //     {
        //         id: "2225345",
        //         model: "EMU43",
        //         type: "Симулятор гражданской авиации",
        //         simulatorName: "Boing-98",
        //         productionDate: "09.11.2001",
        //         commissioningDate: "21.04.2002",
        //         techFrequrency: 36,
        //         components: [
        //             { id: "1231", name: "YUA21" }
        //         ]
        //     },
        //     {
        //         id: "1324567",
        //         model: "ENG21",
        //         type: "Симулятор гражданской авиации",
        //         simulatorName: "Hork-22",
        //         productionDate: "01.12.2006",
        //         commissioningDate: "21.12.2006",
        //         techFrequrency: 24,
        //         components: [
        //             { id: "8790", name: "LMO00" },
        //             { id: "1456", name: "ORH12" },
        //             { id: "0978", name: "PLL01" }
        //         ]
        //     }
        // ]
        //
        // return simulatorsResponse;
    }

    return (
        <div className="closable-page page-container">
            <CloseButton onClick={() => {navigate(-1)}}/>
            <GreenButton text={"Добавить новый симулятор"} onClick={() => {navigate("/simulator/new")}}/>

            {isNoSimulators && <Block text={"Нет отслеживаемых симуляторов"} />}

            {!isNoSimulators && simulators.map(simulator => <Simulator key={simulator.id} simulator={simulator} />)}

        </div>
    )
}