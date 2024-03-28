import Block from '../Block'
import RedButton from '../RedButton'

// Блок с информацией о пользователе
function UserInfo({username}) {
    return (
        <div className="user-info">
            <Block text={username} />
            <RedButton text="Выйти"/>
        </div>
    );
}

export default UserInfo;