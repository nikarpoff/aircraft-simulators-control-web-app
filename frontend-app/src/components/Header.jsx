// Компонент "Заголовок" - идентичен для всех страниц, но имеет разный текст
function Header({title}) {
    return (
        <header className="header">
            <h3>{title}</h3>
        </header> 
    );
}

export default Header;