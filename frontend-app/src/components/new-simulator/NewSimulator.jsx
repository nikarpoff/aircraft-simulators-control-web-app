import GreenButton from '../UI/button/GreenButton'
import Header from '../UI/blocks/Header'
import DateInput from '../UI/input/DateInput';
import CloseButton from "../UI/button/CloseButton";
import {useState} from "react";
import {useNavigate} from "react-router-dom";

export default function NewSimulator() {
    const navigate = useNavigate();

    const [simulatorData, setSimulatorData] = useState({
        model: '',
        type: 'Симулятор гражданской авиации',
        simulatorName: '',
        productionDate: '',
        commissioningDate: '',
        techCheckFrequency: '',
        components: ''
    });

    async function addSimulator(data) {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data), // Преобразование данных в формат JSON
        };

        try {
            const response = await fetch("https://localhost:8443/api/v1/simulator/add", options);
            return await response.json();
        } catch (error) {
            console.error('Error fetching simulators:', error);
            return [];
        }
    }

    // разворачиваем старый список в новый, но заменяем поле [name] значением value
    const handleChange = (e) => {
        const { name, value } = e.target;
        setSimulatorData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handlePositiveIntChange = (e) => {
        const inputValue = e.target.value;
        // Проверяем, является ли введенное значение положительным целым числом
        if (/^\d*$/.test(inputValue)) {
            setSimulatorData({...simulatorData, techCheckFrequency: inputValue});
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Проверка на пустые строки
        if (
            !simulatorData.model ||
            !simulatorData.type ||
            !simulatorData.simulatorName ||
            !simulatorData.productionDate ||
            !simulatorData.commissioningDate ||
            !simulatorData.techCheckFrequency ||
            !simulatorData.components
        ) {
            alert("Пожалуйста, заполните все поля");
            return; // Прекратить отправку данных на сервер, если есть незаполненные поля
        }

        // Проверка, чтобы productionDate была меньше commissioningDate
        if (new Date(simulatorData.productionDate) >= new Date(simulatorData.commissioningDate)) {
            alert("Дата производства должна быть раньше даты ввода в эксплуатацию");
            return; // Прекратить отправку данных на сервер, если дата производства больше или равна дате ввода в эксплуатацию
        }

        // Разделение строки components на массив строк
        const componentsArray = simulatorData.components.split(',').map(component => component.trim());
        // trim() используется для удаления лишних пробелов вокруг каждой компоненты

        // Обновляем simulatorData, чтобы components были массивом строк
        const updatedSimulatorData = { ...simulatorData, components: componentsArray };

        addSimulator(updatedSimulatorData).then(r => {
            navigate(-1);
            alert("Теперь новый симулятор отслеживается!");
        }).catch(reason => alert(reason));

    };

    return (
        <div className='closable-page page-container'>
            <CloseButton onClick={() => navigate(-1)} />
            <Header title={"Добавление нового симулятора"} />

            <input name="model" type='text' className='block-input' placeholder='Введите модель симулятора'
                   value={simulatorData.model} onChange={handleChange}></input>
            
            <select name="type" className='block-input' value={simulatorData.type} onChange={handleChange}>
                <option>Симулятор гражданской авиации</option>
                <option>Симулятор военной авиации</option>
                <option>Инженерный симулятор</option>
            </select>

            <input name="simulatorName" type='text' className='block-input' placeholder='Введите название симулятора'
                   value={simulatorData.simulatorName} onChange={handleChange}></input>
            
            <DateInput name="productionDate" hint={"Введите дату производства"} style={{flex:1}}
                       value={simulatorData.productionDate} onChange={handleChange}/>

            <DateInput name="commissioningDate" hint={"Введите дату ввода в эксплуатацию"} style={{flex:1}}
                       value={simulatorData.commissioningDate} onChange={handleChange}/>
            
            <input name="techCheckFrequency" placeholder="Введите заявленную производителем переодичность технического осмотра"
                                  value={simulatorData.techCheckFrequency} onChange={handlePositiveIntChange}/>

            <input type='text' name="components" className='block-input' placeholder='Введите компоненты симулятора (через запятую)'
                   value={simulatorData.components} onChange={handleChange}></input>
            
            <GreenButton onClick={handleSubmit} text={"Добавить новый симулятор"} style={{marginTop:15}} />
        </div>
    );
}