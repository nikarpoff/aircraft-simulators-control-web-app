import classes from "./button.module.css";

// Компонент зеленая кнопка - необходима для кнопок для создания, подтверждения
export default function GreenButton({ text: children , ...props}) {
    return (
        <button className={classes.greenButton} {...props}>
            {children}
        </button>
    );
}