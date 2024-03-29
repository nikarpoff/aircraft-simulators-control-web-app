import Block from "../UI/blocks/Block";
import RedButton from "../UI/button/RedButton";


export default function Simulator({ simulator }) {
    return (
        <div className="simulator" style={{textAlign: "center"}}>
            <div style={{display: "flex"}}>
                <Block text={"Симулятор " + simulator.id} style={{flex: 1}}/>
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
                    <div key={component.id}>
                        <Block text={"Компонент " + component.id} style={{backgroundColor:"aqua"}}/>
                        <Block text={"Название:" + component.name} />
                    </div>
                ))}
            </div>

        </div>
    )
}