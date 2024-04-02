import Header from "../UI/blocks/Header";
import Block from '../UI/blocks/Block'
import TechSimulatorState from "./TechSimulatorState";
import CloseButton from "../UI/button/CloseButton";
import {useNavigate} from "react-router-dom";


export default function TechCheck() {

    const navigate = useNavigate();

    let isNoSimulators = false;

    let dateReport = new Date().toLocaleDateString();
    let simulatorsTechInfo = getSimulatorsTechInfo();

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


    if (typeof simulatorsTechInfo === "undefined") isNoSimulators = true;

    return (
        <div className="closable-page page-container">
            <CloseButton onClick={() => navigate(-1)}/>
            <Header title={`Отчет от ${dateReport}`} />

            {isNoSimulators && <Block text={`Нет отслеживаемых симуляторов`} />}

            {!isNoSimulators && simulatorsTechInfo.map(
                simulatorTechInfo => <TechSimulatorState key={simulatorTechInfo.id} simulatorTechInfo={simulatorTechInfo}/>
            )}
        </div>
    )
}