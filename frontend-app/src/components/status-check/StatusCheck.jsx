import Header from "../UI/blocks/Header";
import CloseButton from "../UI/button/CloseButton";
import SimulatorStatus from "./SimulatorStatus";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import moment from "moment";


export default function StatusCheck() {

    const [report, setReport] = useState( {
        reportDate: "loading...",
        simulators: []
    });

    useEffect(() => {
        getReport().then(res => {
            res.reportDate = moment(res.reportDate);
            res.reportDate = res.reportDate.format('MMMM Do YYYY, h:mm:ss a');
            setReport(res);
        })
    }, []);


    async function getReport() {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const response = await fetch("https://localhost:8443/api/v1/report/last", options);
            return await response.json();
        } catch (error) {
            console.error('Error fetching simulators:', error);
            return [];
        }
    }

    // function getSimulatorsStatuses() {
    //     let simulatorsStatusesResponse = [
    //         {
    //             id: "1235123",
    //             isActive: true,
    //             isOccupied: true,
    //             components: [
    //                 { id: "1234", responseTime: 5, temperature: 45, power: 1000, voltage: 220 },
    //                 { id: "4356", responseTime: 4, temperature: 23, power: 880, voltage: 198 },
    //             ]
    //         },
    //         {
    //             id: "2225345",
    //             isActive: true,
    //             isOccupied: false,
    //             components: [
    //                 { id: "1231", responseTime: 10, temperature: 67, power: 1003, voltage: 221 }
    //             ]
    //         },
    //         {
    //             id: "1324567",
    //             isActive: false,
    //             isOccupied: false
    //         }
    //     ]
    //
    //     return simulatorsStatusesResponse;
    // }

    const navigate = useNavigate();

    return (
        <div className="page-container">
            <CloseButton onClick={() => navigate(-1)} />
            <Header title={`Отчет от ${report.reportDate}`} />

            {report.simulators.map(simulatorStatus => <SimulatorStatus simulatorStatus={simulatorStatus}/>)}
        </div>
    );
}