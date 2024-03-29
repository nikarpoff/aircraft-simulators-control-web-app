import Header from '../UI/blocks/Header';
import BlockButton from '../UI/button/BlockButton';
import UserInfo from './UserInfo';
import PeriodReportForm from './PeriodReportForm';

export default function MainPage() {
    return (
        <div className="main-page">
            <Header title="Главное окно" />
            <UserInfo username="TEST!"/>

            <BlockButton text="Отобразить последний опрос" style={{marginTop:15}} />
            <PeriodReportForm />
            <BlockButton text="Рассчитать необходимость технического осмотра симуляторов" style={{marginTop:15}} />
        </div>
    )
}