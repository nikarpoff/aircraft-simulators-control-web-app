import Header from '../UI/blocks/Header';
import BlockButton from '../UI/button/BlockButton';
import UserInfo from './UserInfo';
import PeriodReportForm from './PeriodReportForm';

import { useNavigate } from 'react-router-dom';
import GreenButton from "../UI/button/GreenButton";
import {useEffect, useMemo, useState} from "react";

export default function MainPage() {

    const navigate = useNavigate();

    const [user, setUser] = useState( "user");

    useEffect(() => {
        getUser().then(res => {
            setUser(res);
        })
    }, []);

    const isAdmin = useMemo(() => {
        return user === "admin";
    }, [user])


    async function getUser() {
        return new Promise(async (resolve, reject) => {
            await fetch("https://localhost:8443/api/v1/user")
                .then((response) => {
                    return response.text();
                })
                .then(text => {
                    resolve(text);
                })
                .catch(reason => reject(reason))
        })
    }

    const formReportByPeriod = (report) => {
        // TODO сделать обращение к серверу на создание отчета за период
        console.log(`Здесь мы должны обращаться к серверу и переходить на страницу с отчетом, но мы пока можем только: ${report}`)
    }

    return (
        <div className="main-page">
            <Header title="Главное окно" />
            <UserInfo username={user}/>

            <BlockButton text="Отобразить последний опрос"
                         style={{marginTop:15}}
                         onClick={() =>
                             navigate(`simulator/status`, { replace: false })} />

            <PeriodReportForm formReport={formReportByPeriod}/>

            <BlockButton text="Рассчитать необходимость технического осмотра симуляторов"
                         style={{marginTop:15}}
                         onClick={() =>  navigate("simulator/tech")}
            />

            {isAdmin &&
                <GreenButton text={"Редактировать информацию"}
                             style={{marginTop:15, width: "100%"}}
                             onClick={() =>  navigate("simulator/")} />
            }

        </div>
    )
}