// Компонент "Красная кнопка" - отвечает за функции закрытия/выхода/удаления
function RedButton({ text }) {
    return (
        <button className="red-button">
            {text}
        </button>
    );
}

export default RedButton;