import Header from '../UI/blocks/Header';
import BlockButton from '../UI/button/BlockButton';
import UserInfo from './UserInfo';
import PeriodReportForm from './PeriodReportForm';

export default function MainPage() {

    const formReportByPeriod = (report) => {
        // TODO сделать обращение к серверу на создание отчета за период
        console.log(`Здесь мы должны обращаться к серверу и переходить на страницу с отчетом, но мы пока можем только: ${report}`)
    }

    return (
        <div className="main-page">
            <Header title="Главное окно" />
            <UserInfo username="TEST!"/>

            <BlockButton text="Отобразить последний опрос" style={{marginTop:15}} />
            <PeriodReportForm formReport={formReportByPeriod}/>
            <BlockButton text="Рассчитать необходимость технического осмотра симуляторов" style={{marginTop:15}} />
        </div>
    )
}