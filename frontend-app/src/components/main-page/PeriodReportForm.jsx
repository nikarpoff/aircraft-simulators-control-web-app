import BlockButton from '../UI/button/BlockButton';
import DateInput from '../UI/input/DateInput';

export default function PeriodReportForm() {
    return (
        <form className="period-form" style={{ marginTop: 15, display: "flex" }}>
            <DateInput hint={"Начало периода"} />
            <DateInput hint={"Конец периода"} />
            <BlockButton text="Сформировать отчет за период" style={{flex: 1}}/>
        </form>
    );
}