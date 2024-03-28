// Компонент зеленая кнопка - необходима для кнопок для создания, подтверждения
function GreenButton({ text }) {
    return (
        <button className="green-button">
            {text}
        </button>
    );
}

export default GreenButton