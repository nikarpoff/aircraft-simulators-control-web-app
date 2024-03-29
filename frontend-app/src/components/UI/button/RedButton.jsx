import classes from "./button.module.css";

// Компонент "Красная кнопка" - отвечает за функции закрытия/выхода/удаления
export default function RedButton({ text: children, ...props}) {
    return (
        <button className={classes.redButton} {...props}>
            {children}
        </button>
    );
}