import Block from "../Block";
import GreenButton from "../GreenButton";
import RedButton from "../RedButton";
import Simulator from "./Simulator";


function ShowSimulators({ simulators }) {
    let isNoSimulators = false;

    if (typeof simulators === "undefined") isNoSimulators = true;

    return (
        <div className="closable-page page-container">
            <RedButton text={"x"} />
            <GreenButton text={"Добавить новый симулятор"} />

            {isNoSimulators && <Block text={"Нет отслеживаемых симуляторов"} />}

            {!isNoSimulators && simulators.map(simulator => <Simulator key={simulator.id} simulator={simulator} />)}

        </div>
    )
}

export default ShowSimulators;