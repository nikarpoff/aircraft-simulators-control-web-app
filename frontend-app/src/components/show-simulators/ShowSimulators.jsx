import Block from "../UI/blocks/Block";
import GreenButton from "../UI/button/GreenButton";
import Simulator from "./Simulator";
import CloseButton from "../UI/button/CloseButton";
import {useNavigate} from "react-router-dom";
import {useEffect, useMemo, useState} from "react";


export default function ShowSimulators() {
    const navigate = useNavigate()

    const [simulators, setSimulators] = useState( []);

    useEffect(() => {
        getSimulators().then(res => {
            setSimulators(res);
        })
    }, []);

    const isNoSimulators = useMemo(() => {
        return typeof simulators === "undefined";
    }, [simulators])


    async function getSimulators() {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        };

        try {
            const response = await fetch("https://localhost:8443/api/v1/simulator/", options);
            return await response.json();
        } catch (error) {
            console.error('Error fetching simulators:', error);
            return [];
        }
    }


    async function deleteSimulator( id ) {
        const options = {
            method: 'DELETE'
        };

        const response = await fetch(
            `https://localhost:8443/api/v1/simulator/?id=${id}`,
            options);

        if (!response.ok) {
            throw new Error(`Ошибка удаления симуляторов: ${response.status}`);
        }
    }

    const handleDeleteSimulator = async (id) => {
        try {
            await deleteSimulator(id); // Удаление симулятора по его ID
            const updatedSimulators = simulators.filter((simulator) => simulator.id !== id);
            setSimulators(updatedSimulators);
        } catch (error) {
            console.error('Ошибка удаления симулятора:', error);
            alert(`Ошибка удаления симулятора: ${error}`);
        }
    };

    return (
        <div className="closable-page page-container">
            <CloseButton onClick={() => {navigate(-1)}}/>
            <GreenButton text={"Добавить новый симулятор"} onClick={() => {navigate("/simulator/new")}}/>

            {isNoSimulators && <Block text={"Нет отслеживаемых симуляторов"} />}

            {!isNoSimulators && simulators.map(simulator => <Simulator key={simulator.id} simulator={simulator} tryDelete={handleDeleteSimulator} />)}

        </div>
    )
}