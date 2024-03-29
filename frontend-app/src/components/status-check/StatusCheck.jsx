import Header from "../UI/blocks/Header";
import CloseButton from "../UI/button/CloseButton";
import SimulatorStatus from "./SimulatorStatus";


export default function StatusCheck({ dateReport, simulatorsStatuses }) {
    return (
        <div className="page-container">
            <CloseButton />
            <Header title={`Отчет от ${dateReport}`} />

            {simulatorsStatuses.map(simulatorStatus => <SimulatorStatus simulatorStatus={simulatorStatus}/>)}
        </div>
    );
}