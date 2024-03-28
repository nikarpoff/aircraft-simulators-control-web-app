// Компонент "Заголовок" - идентичен для всех страниц, но имеет разный текст
function Block({text}) {
    return (
        <div className="block">
            <p>{text}</p>
        </div> 
    );
}

export default Block;