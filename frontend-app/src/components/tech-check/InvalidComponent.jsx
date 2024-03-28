import Block from '../Block'

export default function InvalidComponent({ component }) {
    return (
        <div className="component-container">
            <Block text={"Неисправный компонент " + component.id} />
            <div style={{ display: "flex" }}>
                <Block text={`Статус ошибки: ${component.status}`} />
                <Block text={`Предполагаемая причина: ${component.errorDescription}`} />
            </div>
        </div>
    )

}