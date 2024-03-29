import CloseButton from "../UI/button/CloseButton";
import Header from "../UI/blocks/Header";
import SimulatorStatistics from "./SimulatorStatistics";


export default function StatisticsReport({ startDate, endDate, simulatorStatistics }) {
    return (
        <div className="page-container">
            <CloseButton />
            <Header title={`Период с ${startDate} по ${endDate}`} />

            {simulatorStatistics.map(simulatorStatistics => <SimulatorStatistics key={simulatorStatistics.id} simulatorStatistics={simulatorStatistics}/>)}
        </div>
    );
}