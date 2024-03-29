import Block from '../UI/blocks/Block'

export default function InvalidComponent({ component }) {
    return (
        <div>
            <Block text={"Неисправный компонент " + component.id} style={{backgroundColor:"aqua"}} />
            <div style={{ display: "flex" }}>
                <Block text={`Статус ошибки: ${component.status}`} style={{backgroundColor:"lightcoral"}}/>
                <Block text={`Предполагаемая причина: ${component.errorDescription}`} />
            </div>
        </div>
    )

}