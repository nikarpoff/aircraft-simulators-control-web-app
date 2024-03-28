import Block from "../Block";
import RedButton from "../RedButton";


function Simulator({ simulator }) {
    return (
        <div className="simulator" style={{textAlign: "center"}}>
            <div className="horizontal-container first-child-full-size" style={{display: "flex"}}>
                <Block text={"Симулятор " + simulator.id}/>
                <RedButton text={"Удалить"} />
            </div>

            <Block text={"Модель: " + simulator.model} />
            <Block text={"Тип: " + simulator.type} />
            <Block text={"Название: " + simulator.simulatorName} />
            <Block text={"Дата производства: " + simulator.productionDate} />
            <Block text={"Дата ввода в эксплуатацию: " + simulator.commissioningDate} />
            <Block text={"Периодичность тех. осмотра: " + simulator.techFrequrency + " мес."} />

            <div>
                {simulator.components.map(component => (
                    <div key={component.id} className="component-container">
                        <Block text={"Компонент " + component.id} />
                        <Block text={"Название:" + component.name} />
                    </div>
                ))}
            </div>

        </div>
    )
}

export default Simulator;