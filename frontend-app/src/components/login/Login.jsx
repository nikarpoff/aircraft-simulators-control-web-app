import GreenButton from "../UI/button/GreenButton";
import Header from "../UI/blocks/Header";

export default function Login() {
    return (
        <div className="login page-container">
            <Header title={"Вход в систему"} />
            <input className="block-input" type="text" style={{marginBottom:15}} placeholder="Введите имя пользователя"></input>
            <input className="block-input" type="password" placeholder="Введите пароль"></input>
            <GreenButton text={"Войти"} style={{marginTop:15, alignSelf:"flex-end", width: "35%"}}/>
        </div>
    );
}