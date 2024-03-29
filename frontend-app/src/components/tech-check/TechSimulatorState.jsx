import Block from "../UI/blocks/Block";
import InvalidComponent from "./InvalidComponent";


export default function TechSimulatorState({ simulatorTechInfo }) {
    let isComponentsBroken = false;

    if (typeof simulatorTechInfo.invalidComponents !== 'undefined') isComponentsBroken = true;

    return (
        <div className="simulator">
            <Block text={`Симулятор ${simulatorTechInfo.id}`} />

            {!simulatorTechInfo.isTechCheckRequired &&
                <div style={{ backgroundColor: "lightgreen" }}>
                    <Block text={"Технический осмотр не требуется"} />
                </div>
            }

            {simulatorTechInfo.isTechCheckRequired &&
                <div className="simulatorBrokenInfo">
                    <div style={{ backgroundColor:"orange" }}>
                        <Block text={"Требуется технический осмотр!"} />
                    </div>

                    <Block text={`Основание для технического осмотра: ${simulatorTechInfo.description}`} />
                    <Block text={`Дата последнего технического осмотра: ${simulatorTechInfo.lastTechCheckDate}`} />

                    {isComponentsBroken && simulatorTechInfo.invalidComponents.map(
                        component => (<InvalidComponent key={component.id} component={component} />)
                    )
                    }

                </div>
            }

        </div>
    );

}