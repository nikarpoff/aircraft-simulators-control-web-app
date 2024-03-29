import Block from "../UI/blocks/Block";
import GreenButton from "../UI/button/GreenButton";
import Simulator from "./Simulator";
import CloseButton from "../UI/button/CloseButton";


export default function ShowSimulators({ simulators }) {
    let isNoSimulators = false;

    if (typeof simulators === "undefined") isNoSimulators = true;

    return (
        <div className="closable-page page-container">
            <CloseButton />
            <GreenButton text={"Добавить новый симулятор"} />

            {isNoSimulators && <Block text={"Нет отслеживаемых симуляторов"} />}

            {!isNoSimulators && simulators.map(simulator => <Simulator key={simulator.id} simulator={simulator} />)}

        </div>
    )
}