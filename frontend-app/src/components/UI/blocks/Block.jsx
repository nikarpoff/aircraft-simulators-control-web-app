import classes from "./blocks.module.css";

// Компонент "Блок" - полноразмерный блок с границами
export default function Block({ text: children, ...props}) {
    return (
        <div {...props} className={classes.block}>
            <p>{children}</p>
        </div> 
    );
}