# API
Формат дат - `"dd.MM.yyyy": string`

## FRONT + BACK
request mapping: */api/v1*

---------------
### reports
request mapping: */report*

#### getLastReport
*Получить результаты последнего опроса*

type: GET

url: */last*

schema request: *empty*

schema response:
```json
lastReport: [
    simulators: {
        id: int,
        isActive: boolean,
        isOccupied: boolean, // Занят ли (проводится ли занятие)
        components: [{   
            id: int,
            responseTime: int,
            temperature: int,
            power: int,
            voltage: int 
        }]
    }
]

```

#### getReportByPeriod
*Получить результаты опросов за период*

type: GET

url: */period*

schema request: 
```js
startDate: string,
endDate: string
```

schema response:
```js
simulatorsStatistics: {
    id: int, // id симулятора
    components: [
        {   
            id: int,
            responseTime: [int],
            temperature: [int],
            power: [int],
            voltage: [int] 
        }
    ]
}
```

#### getTechCheckReport
*Получить выводы о необходимости технического осмотра*

type: GET

url: */tech*

schema request: *empty*

schema response:
```js
simulatorsTechInfo: {
    id: int, // id симулятора
    isTechCheckRequired: boolean,
    description: string, // например "неисправны компоненты!" или "последний ТО проводился давно"
    lastTechCheckDate: string,
    invalidComponents: [ // необязателен (если сломанных нет)
        { 
            id: int, // id неисправного компонента
            status: string, //  строка по типу: "Не отвечает", "Не исправен", "Сбой"
            errorDescription: string // необязателен, описание ошибки, например "Неисправна система охлаждения"
        }
    ]
}
```

-----------

### simulators
request mapping: */simulator*

#### getSimulators
*Получить все симуляторы*

type: GET

url: */simulator*

schema request: *empty*

schema response:
```js
simulators: [{
    id: int,
    model: string,
    type: string, // Симулятор военной авиации/Симулятор гражданской авиации/Инженерный симулятор
    simulatorName: string,
    productionDate: string,
    commissioningDate: string, // Дата ввода в эксплуатацию
    techFrequrency: int, // переодичность технического осмотра (от производителя/заказчика) в месяцах
    components: [
        { id: int, name: string }
    ]
}]
```

#### addSimulator
*Осуществить попытку добавления нового симулятора в отслеживаемые приложением*

type: POST

url: */simulator*

schema request: 
```js
simulator: {
    model: string,
    type: string, // Симулятор военной авиации/Симулятор гражданской авиации/Инженерный симулятор
    simulatorName: string,
    productionDate: string,
    commissioningDate: string, // Дата ввода в эксплуатацию
    techFrequrency: int, // переодичность технического осмотра (от производителя/заказчика) в месяцах
    components: [
        { name: string }
    ]
}
```

schema response:
```js
status: string // Такой симулятор уже отслеживается (ALREADY EXISTS)/Симулятор не найден (NOT FOUND)/Симулятор будет отслеживаться (OK)/Симулятор не будет отслеживаться по причине не найденных компонент (COMPONENTS NOT FOUND) / Отслеживаться не будет ввиду внутренней ошибки сервер (INTERNAL ERROR)

simulator: {
    id: int,
    model: string,
    type: string,
    simulatorName: string,
    productionDate: string,
    commissioningDate: string,
    techFrequrency: int,
    components: [
        { id: int, name: string }
    ]
}
```

#### removeSimulator
*Перестать отслеживать симулятор*

type: DELETE

url: */simulator*

schema request: 
```js
id: int // id удаляемого симулятора
```

schema response: status: HTMLResponse
