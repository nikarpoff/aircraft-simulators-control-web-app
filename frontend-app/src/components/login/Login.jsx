import GreenButton from "../GreenButton";
import Header from "../Header";

function Login() {
    return (
        <div className="login">
            <Header title={"Вход в систему"} />
            <input className="block-input" type="text" placeholder="Введите имя пользователя"></input>
            <input className="block-input" type="password" placeholder="Введите пароль"></input>
            <GreenButton text={"Войти"} style="margin-top:15px"/>
        </div>
    );
}

export default Login