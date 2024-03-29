import classes from "./blocks.module.css";

// Компонент "Заголовок" - идентичен для всех страниц, но имеет разный текст
export default function Header({title}) {
    return (
        <header className={classes.header}>
            <h3>{title}</h3>
        </header> 
    );
}