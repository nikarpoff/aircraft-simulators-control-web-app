import BlockButton from '../BlockButton';
import DateInput from '../DateInput';

function PeriodReportForm() {
    return (
        <form className="period-form">
            <DateInput hint={"Начало периода"} />
            <DateInput hint={"Конец периода"} />
            <BlockButton text="Сформировать отчет за период" />
        </form>
    );
}

export default PeriodReportForm;