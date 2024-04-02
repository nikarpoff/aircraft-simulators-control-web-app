import Header from "../UI/blocks/Header";
import Block from '../UI/blocks/Block'
import TechSimulatorState from "./TechSimulatorState";
import CloseButton from "../UI/button/CloseButton";
import {useNavigate} from "react-router-dom";
import {useEffect, useMemo, useState} from "react";


export default function TechCheck() {

    const navigate = useNavigate();

    let dateReport = new Date().toLocaleDateString();

    const [simulatorsTechInfo, setTechInfo] = useState( {techCheck: []});

    const isNoSimulators = useMemo(() => {
        return simulatorsTechInfo.techCheck === [];
    }, [simulatorsTechInfo])

    useEffect(() => {
        getTechInfo().then(res => {
            setTechInfo(res);
        })
    }, []);


    async function getTechInfo() {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const response = await fetch("https://localhost:8443/api/v1/tech", options);
            return await response.json();
        } catch (error) {
            console.error('Error fetching simulators:', error);
            return [];
        }
    }

    function getSimulatorsTechInfo() {
        let simulatorsTechInfoResponse = [
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

        return simulatorsTechInfoResponse;
    }

    return (
        <div className="closable-page page-container">
            <CloseButton onClick={() => navigate(-1)}/>
            <Header title={`Отчет от ${dateReport}`} />

            {isNoSimulators && <Block text={`Нет отслеживаемых симуляторов`} />}

            {!isNoSimulators && simulatorsTechInfo.techCheck.map(
                techCheck => <TechSimulatorState key={techCheck.id} simulatorTechInfo={techCheck}/>
            )}
        </div>
    )
}