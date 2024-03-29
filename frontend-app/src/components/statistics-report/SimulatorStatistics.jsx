import Block from "../UI/blocks/Block";
import StatisticsBlock from "./StatisticsBlock";


export default function SimulatorStatistics({ simulatorStatistics }) {
    let isComponentsAvailable = typeof simulatorStatistics.components !== "undefined";

    return (
        <div className="simulator">
            <Block text={`Симулятор ${simulatorStatistics.id}`}/>

            { isComponentsAvailable && <div>
                {simulatorStatistics.components.map(component => (
                    <div key={component.id}>
                        <Block text={`Компонент ${component.id}`} style={{backgroundColor: "aqua"}}/>

                        <div className="statisticsModule" style={{display: "flex"}}>
                            <StatisticsBlock title={"Время отклика, мс:"} valuesArray={component.responsesTime} />
                            <StatisticsBlock title={"Температура, °C:"} valuesArray={component.temperatures} />
                            <StatisticsBlock title={"Мощность потребления, Вт:"} valuesArray={component.powers} />
                            <StatisticsBlock title={"Входное напряжение, В:"} valuesArray={component.voltages} />
                        </div>

                    </div>
                ))}
            </div>
            }
        </div>
    );
}