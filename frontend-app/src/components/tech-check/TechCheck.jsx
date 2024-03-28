import Header from "../Header";
import RedButton from "../RedButton";
import Block from '../Block'
import TechSimulatorState from "./TechSimulatorState";


export default function TechCheck({ dateReport, simulatorsTechInfo }) {
    let isNoSimulators = false;

    if (typeof simulatorsTechInfo === "undefined") isNoSimulators = true;

    return (
        <div className="closable-page page-container">
            <RedButton text={"x"} />
            <Header title={`Отчет от ${dateReport}`} />

            {isNoSimulators && <Block text={`Нет отслеживаемых симуляторов`} />}

            {!isNoSimulators && simulatorsTechInfo.map(
                simulatorTechInfo => <TechSimulatorState key={simulatorTechInfo.id} simulatorTechInfo={simulatorTechInfo}/>
            )}
        </div>
    )
}