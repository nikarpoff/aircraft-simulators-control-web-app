// Компонент "Кнопка-блок" - необходима для кнопок общего назначения
function BlockButton({text}) {
    return (
        <button className="button-block">
            {text}
        </button> 
    );
}

export default BlockButton