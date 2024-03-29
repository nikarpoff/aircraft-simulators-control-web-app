import BlockButton from '../UI/button/BlockButton';
import DateInput from '../UI/input/DateInput';
import {useState} from "react";

export default function PeriodReportForm({ formReport }) {
    const [period, setPeriod] = useState({
        startDate: '',
        endDate: ''
    })

    // разворачиваем старый список в новый, но заменяем поле [name] значением value
    const handleChange = (e) => {
        const {name, value} = e.target;
        setPeriod(prevState => ({
            ...prevState, [name]: value
        }))
    }

    const handleFormDate = (e) => {
        e.preventDefault();

        // Проверка на пустые строки
        if (
            !period.startDate ||
            !period.endDate
        ) {
            alert("Пожалуйста, заполните поля для периода формирования отчета!");
            return;
        }

        // Проверка, чтобы productionDate была меньше commissioningDate
        if (new Date(period.startDate) >= new Date(period.endDate)) {
            alert("Дата старта периода должна быть раньше даты окончания периода!");
            return;
        }

        formReport(period);
    }

    return (
        <form className="period-form" style={{ marginTop: 15, display: "flex" }}>
            <DateInput name="startDate" hint={"Начало периода"} onChange={handleChange} />
            <DateInput name="endDate" hint={"Конец периода"} onChange={handleChange} />
            <BlockButton onClick={handleFormDate} text="Сформировать отчет за период" style={{flex: 1}}/>
        </form>
    );
}