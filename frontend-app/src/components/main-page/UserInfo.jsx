import Block from '../UI/blocks/Block'
import RedButton from '../UI/button/RedButton'

// Блок с информацией о пользователе
export default function UserInfo({username}) {

    function logout() {
        window.location.href = 'https://localhost:8443/logout';
    }

    return (
        <div className="user-info" style={{ display: "flex",
                                            flexDirection: "row",
                                            justifyContent: "flex-end",
                                            marginTop: 15}}>
            <Block text={username} />
            <RedButton text="Выйти" onClick={logout}/>
        </div>
    );
}