import classes from "./button.module.css";

export default function CloseButton({ text: children = "x", ...props}) {
    return (
        <button className={classes.closeButton} {...props}>
            {children}
        </button>
    );
}