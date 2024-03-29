import classes from "./input.module.css"

export default function DateInput({hint, ...props}) {
    return (
        <div className={classes.dateInputContainer}>
            <input className={classes.inputDate} type="date" {...props}></input>
            <label className={classes.inputLabel}>{hint}</label>
        </div>
    );
}