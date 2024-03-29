import Block from '../UI/blocks/Block'
import RedButton from '../UI/button/RedButton'

// Блок с информацией о пользователе
export default function UserInfo({username}) {
    return (
        <div className="user-info" style={{ display: "flex",
                                            flexDirection: "row",
                                            justifyContent: "flex-end",
                                            marginTop: 15}}>
            <Block text={username} />
            <RedButton text="Выйти"/>
        </div>
    );
}