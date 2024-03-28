import RedButton from '../RedButton'
import GreenButton from '../GreenButton'
import Header from '../Header'
import DateInput from '../DateInput';
import PositiveIntegerInput from '../PositiveIntegerInput';

function NewSimulator() {
    return (
        <div className='closable-page page-container'>
            <RedButton text={"x"} />
            <Header title={"Добавление нового симулятора"} />

            <input type='text' className='block-input' placeholder='Введите модель симулятора'></input>
            
            <select type='text' className='block-input'>
                <option>Симулятор гражданской авиации</option>
                <option>Симулятор военной авиации</option>
                <option>Инженерный симулятор</option>
            </select>

            <input type='text' className='block-input' placeholder='Введите название симулятора'></input>
            
            <DateInput hint={"Введите дату производства"} isFullSize={true} />
            <DateInput hint={"Введите дату ввода в эксплуатацию"} isFullSize={true} />
            
            <PositiveIntegerInput text={"Введите заявленную производителем переодичность технического осмотра"}/>

            <input type='text' className='block-input' placeholder='Введите компоненты симулятора (через запятую)'></input>
            
            <GreenButton text={"Добавить новый симулятор"}/>
        </div>
    );
}

export default NewSimulator;