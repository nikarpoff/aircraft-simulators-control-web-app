import GreenButton from '../UI/button/GreenButton'
import Header from '../UI/blocks/Header'
import DateInput from '../UI/input/DateInput';
import PositiveIntegerInput from '../UI/input/PositiveIntegerInput';
import CloseButton from "../UI/button/CloseButton";

export default function NewSimulator() {
    return (
        <div className='closable-page page-container'>
            <CloseButton />
            <Header title={"Добавление нового симулятора"} />

            <input type='text' className='block-input' placeholder='Введите модель симулятора'></input>
            
            <select className='block-input'>
                <option>Симулятор гражданской авиации</option>
                <option>Симулятор военной авиации</option>
                <option>Инженерный симулятор</option>
            </select>

            <input type='text' className='block-input' placeholder='Введите название симулятора'></input>
            
            <DateInput hint={"Введите дату производства"} style={{flex:1}} />
            <DateInput hint={"Введите дату ввода в эксплуатацию"} style={{flex:1}} />
            
            <PositiveIntegerInput text={"Введите заявленную производителем переодичность технического осмотра"}/>

            <input type='text' className='block-input' placeholder='Введите компоненты симулятора (через запятую)'></input>
            
            <GreenButton text={"Добавить новый симулятор"} style={{marginTop:15}} />
        </div>
    );
}