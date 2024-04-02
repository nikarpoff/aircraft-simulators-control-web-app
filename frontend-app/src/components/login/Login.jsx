import GreenButton from "../UI/button/GreenButton";
import Header from "../UI/blocks/Header";
import {useNavigate} from "react-router-dom";
import {useState} from "react";

export default function Login() {
    const navigate = useNavigate();

    let [credentials, setCredentials] = useState({
        username: '',
        password: ''
    });

    // разворачиваем старый список в новый, но заменяем поле [name] значением value
    const handleChange = (e) => {
        const { name, value } = e.target;
        setCredentials(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Проверка на пустые строки
        if (
            !credentials.username ||
            !credentials.password
        ) {
            alert("Пожалуйста, заполните все поля");
            return; // Прекратить отправку данных на сервер, если есть незаполненные поля
        }

        // TODO Отправить simulatorData на сервер
        console.log('Отправка данных:', credentials);

        navigate("/");

        alert("Теперь новый симулятор отслеживается!")
    };

    return (
        <div className="login page-container">
            <Header title={"Вход в систему"} />
            <input className="block-input"
                   type="text" style={{marginBottom:15}}
                   placeholder="Введите имя пользователя"
                   value={credentials.username}
                   onChange={handleChange}></input>

            <input className="block-input" type="password"
                   placeholder="Введите пароль"
                   value={credentials.password}
                   onChange={handleChange}></input>

            <GreenButton text={"Войти"}
                         style={{marginTop:15, alignSelf:"flex-end", width: "35%"}}
                         onClick={handleSubmit}/>
        </div>
    );
}