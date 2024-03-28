function DateInput({hint, isFullSize}) {
    let className = "date-input-container";

    if (isFullSize) {
        className += " full-width-block";
    }

    return (
        <div className={className}>
            <input className="input-date" type="date"></input>
            <label className="input-label">{hint}</label>
        </div>
    );
}

export default DateInput;