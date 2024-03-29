import Block from "../UI/blocks/Block";


export default function SimulatorStatus({ simulatorStatus }) {
    let activeStatus = simulatorStatus.isActive === true ? "Активен" : "Не активен";
    let activeColor = simulatorStatus.isActive === true ? "lightgreen" : "lightcoral";
    let occupiedStatus = simulatorStatus.isOccupied === true ? "На симуляторе проводится занятие" : "Свободен";
    let occupiedColor = simulatorStatus.isOccupied === true ? "lightcoral" : "lightgreen";

    let isComponentsAvailable = typeof simulatorStatus.components !== "undefined";

    return (
        <div className="simulator">
            <Block text={`Симулятор ${simulatorStatus.id}`}/>

            <div style={{display: "flex"}}>
                <Block text={activeStatus} style={{backgroundColor: activeColor, flex: 1}}/>
                { simulatorStatus.isActive && <Block text={occupiedStatus} style={{backgroundColor: occupiedColor, flex: 1}}/> }
            </div>

            { isComponentsAvailable && <div>
                {simulatorStatus.components.map(component => (
                    <div key={component.id}>
                        <Block text={`Компонент ${component.id}`} style={{backgroundColor: "aqua"}}/>

                        <div style={{display: "flex"}}>
                            <Block text={`Время отклика ${component.responseTime} мс`}/>
                            <Block text={`Внутренняя температура ${component.temperature} °C`}/>
                            <Block text={`Мощность потребления ${component.power} Вт`}/>
                            <Block text={`Входное напряжение ${component.voltage} В`}/>
                        </div>
                    </div>
                ))}
            </div>
            }

            { !isComponentsAvailable && <Block text={"Ответ с компонент не получен!"} style={{backgroundColor: "lightcoral"}}/> }

        </div>
    );
}