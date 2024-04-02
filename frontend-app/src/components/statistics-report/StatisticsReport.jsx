import CloseButton from "../UI/button/CloseButton";
import Header from "../UI/blocks/Header";
import SimulatorStatistics from "./SimulatorStatistics";
import {useLocation, useNavigate} from "react-router-dom";
import {useEffect, useMemo, useState} from "react";


export default function StatisticsReport() {
    const location = useLocation();
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(location.search);

    const startDate = queryParams.get('startDate');
    const endDate = queryParams.get('endDate');

    if (startDate === "" || startDate === "") {
        alert("Дата была передана некорректно!");
        navigate(-1);
    }

    const [simulatorsStatistics, setStatistics] = useState( {simulators: []});

    useEffect(() => {
        getStatistics().then(res => {
            setStatistics(res);
        })
    }, []);


    async function getStatistics() {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const response = await fetch("https://localhost:8443/api/v1/simulator/statistics", options);
            return await response.json();
        } catch (error) {
            console.error('Error fetching simulators:', error);
            return [];
        }
    }

    // function getSimulatorStatistics() {
    //     let simulatorStatisticsResponse = [
    //         {
    //             id: "1235123",
    //             components: [
    //                 { id: "1234", responsesTime: [5, 4, 2, 4, 5, 3], temperatures: [45, 33, 45, 32, 65, 67], powers: [1000, 800, 900, 1000, 1200, 1200], voltages: [220, 220, 219, 207, 206, 204] },
    //                 { id: "4356", responsesTime: [4, 2, 4, 4], temperatures: [23, 34, 23, 23], powers: [880, 700, 800, 670], voltages: [198, 200, 220, 200] },
    //             ]
    //         },
    //         {
    //             id: "2225345",
    //             components: [
    //                 { id: "1231", responsesTime: [10, 12, 12], temperatures: [67, 66, 65], powers: [1003, 1000, 1001], voltages: [221, 220, 222] }
    //             ]
    //         },
    //         {
    //             id: "1324567",
    //             components: [
    //                 { id: "8790" },
    //                 { id: "1456" },
    //                 { id: "0978" }
    //             ]
    //         }
    //     ]
    //
    //     return simulatorStatisticsResponse;
    // }


    return (
        <div className="page-container">
            <CloseButton onClick={() => navigate(-1)}/>
            <Header title={`Период с ${startDate} по ${endDate}`} />

            {simulatorsStatistics.simulators.map(simulatorStatistics => <SimulatorStatistics key={simulatorStatistics.id} simulatorStatistics={simulatorStatistics}/>)}
        </div>
    );
}