import Header from '../Header';
import BlockButton from '../BlockButton';
import UserInfo from './UserInfo';
import PeriodReportForm from './PeriodReportForm';

function MainPage() {
    return (
        <div className="main-page">
            <Header title="Главное окно" />
            <UserInfo username="TEST!"/>

            <BlockButton text="Отобразить последний опрос"/>
            <PeriodReportForm />
            <BlockButton text="Рассчитать необходимость технического осмотра симуляторов"/>
        </div>
    )
}

export default MainPage;