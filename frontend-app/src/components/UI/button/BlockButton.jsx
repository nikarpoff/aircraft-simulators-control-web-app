import classes from "./button.module.css";

// Компонент "Кнопка-блок" - необходима для кнопок общего назначения
export default function BlockButton({text: children, ...props}) {
    return (
        <button className={classes.buttonBlock} {...props}>
            {children}
        </button> 
    );
}