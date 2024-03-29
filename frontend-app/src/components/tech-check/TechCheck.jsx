import Header from "../UI/blocks/Header";
import Block from '../UI/blocks/Block'
import TechSimulatorState from "./TechSimulatorState";
import CloseButton from "../UI/button/CloseButton";


export default function TechCheck({ dateReport, simulatorsTechInfo }) {
    let isNoSimulators = false;

    if (typeof simulatorsTechInfo === "undefined") isNoSimulators = true;

    return (
        <div className="closable-page page-container">
            <CloseButton />
            <Header title={`Отчет от ${dateReport}`} />

            {isNoSimulators && <Block text={`Нет отслеживаемых симуляторов`} />}

            {!isNoSimulators && simulatorsTechInfo.map(
                simulatorTechInfo => <TechSimulatorState key={simulatorTechInfo.id} simulatorTechInfo={simulatorTechInfo}/>
            )}
        </div>
    )
}